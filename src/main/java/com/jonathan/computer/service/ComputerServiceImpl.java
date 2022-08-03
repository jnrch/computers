package com.jonathan.computer.service;

import com.jonathan.computer.entity.Computer;
import com.jonathan.computer.repository.ComputerRepository;
import com.jonathan.computer.service.dto.ComputerDto;
import com.jonathan.computer.service.dto.ComputerRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerServiceImpl implements IComputerService {

    private static final Logger logger = LoggerFactory.getLogger(ComputerServiceImpl.class);
    private final ComputerRepository computerRepository;
    private final ModelMapper mapper;


    public ComputerServiceImpl(ComputerRepository computerRepository, ModelMapper mapper) {
        this.computerRepository = computerRepository;
        this.mapper = mapper;
    }

    @Override
    public ComputerDto createComputer(ComputerRequest request) {
        logger.info("[COMPUTER-SERVICE] creating computer type {}", request.getType());
        Computer computer = mapper.map(request, Computer.class);
        return mapper.map(computerRepository.save(computer), ComputerDto.class);
    }

    @Override
    public ComputerDto getComputerById(Long id) {
        logger.info("[COMPUTER-SERVICE] getting computer by id {}", id);
        Optional<Computer> computer = computerRepository.findById(id);

        if (computer.isEmpty()) {
            throw new RuntimeException("Computer not found");
        }

        return mapper.map(computer.get(), ComputerDto.class);
    }

    @Override
    public List<ComputerDto> getComputerList() {
        logger.info("[COMPUTER-SERVICE] getting all computers");
        List<Computer> computerList = computerRepository.findAll();
        return Arrays.asList(mapper.map(computerList, ComputerDto[].class));
    }
}
