package com.jonathan.computer.service;

import com.jonathan.computer.entity.Computer;
import com.jonathan.computer.repository.ComputerRepository;
import com.jonathan.computer.service.dto.ComputerDto;
import com.jonathan.computer.service.dto.ComputerRequestDto;
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
    public ComputerDto createComputer(ComputerRequestDto request) {
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

    @Override
    public ComputerDto updateComputer(Long id, ComputerRequestDto request) {
        logger.info("[COMPUTER-SERVICE] updating computer with id {}", id);
        return computerRepository.findById(id)
                .map(computer -> {
                    computer.setBrand(request.getBrand());
                    computer.setModel(request.getModel());
                    computer.setType(request.getType());
                    computer.setMemory(request.getMemory());
                    computer.setProcessor(request.getProcessor());
                    computer.setDisk(request.getDisk());
                    computer.setPrice(request.getPrice());
                    computer.setYear(request.getYear());
                    return mapper.map(computerRepository.save(computer), ComputerDto.class);
                }).orElseThrow(() -> new RuntimeException("Computer not found"));
    }

    @Override
    public void deleteComputer(Long id) {
        logger.info("[COMPUTER-SERVICE] deleting computer with id {}", id);
        computerRepository.deleteById(id);
    }

    @Override
    public void deleteAllComputers() {
        logger.info("[COMPUTER-SERVICE] deleting all computers");
        computerRepository.deleteAll();
    }
}
