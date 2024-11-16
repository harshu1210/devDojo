package com.devdojo.devdojobackend.services;

import org.springframework.stereotype.Service;

import com.devdojo.devdojobackend.models.Problem;
import com.devdojo.devdojobackend.repository.ProblemRepo;

import java.util.List;
import java.util.Optional;

@Service

public class ProblemService {
    private final ProblemRepo problemRepo;

    public ProblemService(ProblemRepo problemRepo) {
        this.problemRepo = problemRepo;
    }

    // Fetch all users
    public List<Problem> getAllProblems() {
        return this.problemRepo.findAll();
    }

    // Fetch a single user by ID
    public Optional<Problem> getProblemByPId(Long id) {
        return this.problemRepo.findByPID(id);
    }

    // Create or update a user
    public Problem saveProblem(Problem leetCodeProblem) {
        return this.problemRepo.save(leetCodeProblem);
    }

    // Delete a user by ID
    public void deleteProblem(Long id) {
        this.problemRepo.deleteByPid(id);
    }
}
