package com.devdojo.devdojobackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devdojo.devdojobackend.models.problemContent;
import com.devdojo.devdojobackend.services.problemContentService;

@RestController
@RequestMapping("/api/problemContent")
public class problemContentController {

    private final problemContentService problemContentService;

    public problemContentController(problemContentService problemContentService) {
        this.problemContentService = problemContentService;
    }

    // GET API: Fetch all problemsContent
    @GetMapping
    public ResponseEntity<List<problemContent>> getAllProblemsContents() {
        List<problemContent> problems = problemContentService.getAllProblemContents();
        return new ResponseEntity<>(problems, HttpStatus.OK);
    }

    // GET API: Fetch a problemContetn by ID
    @GetMapping("/{id}")
    public ResponseEntity<problemContent> getProblemContentByPcid(@PathVariable Long pcid) {
        return problemContentService.getProblemContentByPcid(pcid)
                .map(problemContent -> new ResponseEntity<>(problemContent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST API: Create a new problemContetn
    @PostMapping
    public ResponseEntity<problemContent> createProblemContent(@RequestBody problemContent problemContetn) {
        problemContent savedDevDojoProblem = problemContentService.createProblemContent(problemContetn);
        return new ResponseEntity<>(savedDevDojoProblem, HttpStatus.CREATED);
    }

    @PostMapping("/array")
    public ResponseEntity<List<problemContent>> createProblemContents(@RequestBody problemContent[] problems) {
        if (problems == null || problems.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle empty input
        }
        List<problemContent> savedProblemList = new ArrayList<>();
        for (problemContent problemContetn : problems) {
            problemContent savedProblem = problemContentService.createProblemContent(problemContetn);
            savedProblemList.add(savedProblem);
        }
        return new ResponseEntity<>(savedProblemList, HttpStatus.CREATED);
    }

    // PUT API: Update a problemContetn
    @PutMapping("/{id}")
    public ResponseEntity<problemContent> updateProblemContent(@PathVariable Long id,
            @RequestBody problemContent problemContetn) {
        return problemContentService.getProblemContentByPcid(id)
                .map(existingUser -> {
                    problemContetn.setPcid(id); // Ensure the ID remains the same
                    problemContent updateDevDojoProblem = problemContentService.createProblemContent(problemContetn);
                    return new ResponseEntity<>(updateDevDojoProblem, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE API: Delete a problemContetn by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        if (problemContentService.getProblemContentByPcid(id).isPresent()) {
            problemContentService.deleteProblemContentByPcid(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
