package com.jonathan.computer.service.dto;

import com.jonathan.computer.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComputerDto {

    private Long id;
    private String brand;
    private String model;
    private Type type;
    private String memory;
    private String processor;
    private String disk;
    private Double price;
    private Integer year;
}
