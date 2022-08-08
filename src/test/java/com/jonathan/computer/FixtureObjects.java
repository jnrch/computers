package com.jonathan.computer;

import com.jonathan.computer.entity.enums.Type;
import com.jonathan.computer.service.dto.ComputerDto;
import com.jonathan.computer.service.dto.ComputerRequestDto;

public class FixtureObjects {

    public ComputerDto getComputerDto() {
        return ComputerDto.builder()
                .id(1L)
                .brand("Mac")
                .model("2022")
                .type(Type.LAPTOP)
                .memory("16 GB")
                .processor("M1")
                .disk("512 GB")
                .price(2000.0)
                .year(2022)
                .build();
    }

    public ComputerRequestDto getComputerRequestDto() {
            return ComputerRequestDto.builder()
                    .brand("Mac")
                    .model("2022")
                    .type(Type.LAPTOP)
                    .memory("16 GB")
                    .processor("M1")
                    .disk("512 GB")
                    .price(2000.0)
                    .year(2022)
                    .build();
    }
}
