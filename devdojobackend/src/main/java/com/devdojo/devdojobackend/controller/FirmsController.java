package com.devdojo.devdojobackend.controller;

import com.devdojo.devdojobackend.services.FirmsService;
import com.devdojo.devdojobackend.models.Firms;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/firms")
public class FirmsController {

    private final FirmsService firmsService;

    public FirmsController(FirmsService firmsService) {
        this.firmsService = firmsService;
    }

    // GET API: Fetch all firms
    @GetMapping
    public ResponseEntity<List<Firms>> getAllFirms() {
        List<Firms> firms = firmsService.getAllFirms();
        return new ResponseEntity<>(firms, HttpStatus.OK);
    }

    // GET API: Fetch a firms by ID
    @GetMapping("/{id}")
    public ResponseEntity<Firms> getFirmsById(@PathVariable Long id) {
        return firmsService.getFirmsByCid(id)
                .map(firms -> new ResponseEntity<>(firms, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST API: Create a new firms
    @PostMapping
    public ResponseEntity<Firms> createFirm(@RequestBody Firms firms) {
        Firms savedFirms = firmsService.saveFirm(firms);
        return new ResponseEntity<>(savedFirms, HttpStatus.CREATED);
    }

    @PostMapping("/array")
    public ResponseEntity<List<Firms>> createFirms(@RequestBody Firms[] firms) {
        if (firms == null || firms.length == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle empty input
        }
        List<Firms> savedFirmsList = new ArrayList<>();
        for (Firms firm : firms) {
            Firms savedFirm = firmsService.saveFirm(firm);
            savedFirmsList.add(savedFirm);
        }
        return new ResponseEntity<>(savedFirmsList, HttpStatus.CREATED);
    }

    // PUT API: Update a firms
    @PutMapping("/{id}")
    public ResponseEntity<Firms> updateFirms(@PathVariable Long id, @RequestBody Firms firms) {
        return firmsService.getFirmsByCid(id)
                .map(existingUser -> {
                    firms.setCid(id); // Ensure the ID remains the same
                    Firms updateDevDojoProblem = firmsService.saveFirm(firms);
                    return new ResponseEntity<>(updateDevDojoProblem, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE API: Delete a firms by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFirms(@PathVariable Long id) {
        if (firmsService.getFirmsByCid(id).isPresent()) {
            firmsService.deleteFirmByCid(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
