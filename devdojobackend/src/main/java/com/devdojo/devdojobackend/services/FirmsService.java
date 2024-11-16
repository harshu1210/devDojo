package com.devdojo.devdojobackend.services;

import org.springframework.stereotype.Service;

import com.devdojo.devdojobackend.repository.FirmsRepo;
import com.devdojo.devdojobackend.models.Firms;
import java.util.List;
import java.util.Optional;

@Service
public class FirmsService {
    private final FirmsRepo firmsRepo;

    public FirmsService(FirmsRepo firmsRepo) {
        this.firmsRepo = firmsRepo;
    }

    public List<Firms> getAllFirms() {
        return this.firmsRepo.findAll();
    }

    public Optional<Firms> getFirmsByCid(Long id) {
        return this.firmsRepo.findByCid(id);
    }

    public Firms saveFirm(Firms firm) {
        return this.firmsRepo.save(firm);
    }

    public void deleteFirmByCid(Long id) {
        this.firmsRepo.deleteByCid(id);
    }
}
