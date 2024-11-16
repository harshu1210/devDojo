package com.devdojo.devdojobackend.controller;

import com.devdojo.devdojobackend.services.ProblemService;
import com.devdojo.devdojobackend.models.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // GET API: Fetch all problems
    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        List<Problem> problems = problemService.getAllProblems();
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    // GET API: Fetch a problem by ID
    @GetMapping("/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable Long id) {
        return problemService.getProblemByPId(id)
                .map(problem -> new ResponseEntity<>(problem, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST API: Create a new problem
    @PostMapping
    public ResponseEntity<Problem> createProblem(@RequestBody Problem problem) {
        Problem savedDevDojoProblem = problemService.saveProblem(problem);
        return new ResponseEntity<>(savedDevDojoProblem, HttpStatus.CREATED);
    }

    @PostMapping("/array")
    public ResponseEntity<List<Problem>> createFirms(@RequestBody Problem[] problems) {
        if (problems == null || problems.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle empty input
        }
        List<Problem> savedProblemList = new ArrayList<>();
        for (Problem problem : problems) {
            Problem savedProblem = problemService.saveProblem(problem);
            savedProblemList.add(savedProblem);
        }
        return new ResponseEntity<>(savedProblemList, HttpStatus.CREATED);
    }

    // PUT API: Update a problem
    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(@PathVariable Long id, @RequestBody Problem problem) {
        return problemService.getProblemByPId(id)
                .map(existingUser -> {
                    problem.setPID(id); // Ensure the ID remains the same
                    Problem updateDevDojoProblem = problemService.saveProblem(problem);
                    return new ResponseEntity<>(updateDevDojoProblem, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE API: Delete a problem by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        if (problemService.getProblemByPId(id).isPresent()) {
            problemService.deleteProblem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
