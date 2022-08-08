package com.jonathan.computer.controller;

import com.jonathan.computer.service.IComputerService;
import com.jonathan.computer.service.dto.ComputerDto;
import com.jonathan.computer.service.dto.ComputerRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private final IComputerService computerService;

    public ComputerController(IComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerDto> getComputerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(computerService.getComputerById(id));
    }

    @GetMapping
    public ResponseEntity<List<ComputerDto>> getAllComputers() {
        return ResponseEntity.ok().body(computerService.getComputerList());
    }

    @PostMapping
    public ResponseEntity<ComputerDto> createComputer(@RequestBody ComputerRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(computerService.createComputer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerDto> updateComputer(@PathVariable Long id, @RequestBody ComputerRequestDto request) {
        return ResponseEntity.ok().body(computerService.updateComputer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllComputer() {
        computerService.deleteAllComputers();
        return ResponseEntity.noContent().build();
    }
}
