package com.jonathan.computer.service;

import com.jonathan.computer.service.dto.ComputerDto;
import com.jonathan.computer.service.dto.ComputerRequestDto;

import java.util.List;

public interface IComputerService {

    ComputerDto createComputer(ComputerRequestDto request);

    ComputerDto getComputerById(Long id);

    List<ComputerDto> getComputerList();

    ComputerDto updateComputer(Long id, ComputerRequestDto request);

    void deleteComputer(Long id);

    void deleteAllComputers();
}
