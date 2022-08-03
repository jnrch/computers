package com.jonathan.computer.service;

import com.jonathan.computer.service.dto.ComputerDto;
import com.jonathan.computer.service.dto.ComputerRequest;

import java.util.List;

public interface IComputerService {

    ComputerDto createComputer(ComputerRequest request);

    ComputerDto getComputerById(Long id);

    List<ComputerDto> getComputerList();
}
