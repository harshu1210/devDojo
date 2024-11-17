package com.devdojo.devdojobackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devdojo.devdojobackend.models.problemContent;
import com.devdojo.devdojobackend.repository.problemContentRepo;

@Service
public class problemContentService {
    private final problemContentRepo problemContentRepo;

    public problemContentService(problemContentRepo problemContentRepo) {
        this.problemContentRepo = problemContentRepo;
    }

    public List<problemContent> getAllProblemContents() {
        return this.problemContentRepo.findAll();
    }

    public problemContent createProblemContent(problemContent problemContent) {
        return this.problemContentRepo.save(problemContent);
    }

    public Optional<problemContent> getProblemContentByPcid(Long pcid) {
        return this.problemContentRepo.findByPcid(pcid);
    }

    public void deleteProblemContentByPcid(Long pcid) {
        this.problemContentRepo.deleteByPcid(pcid);
    }
}
