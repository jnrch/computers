package com.jonathan.computer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathan.computer.FixtureObjects;
import com.jonathan.computer.service.ComputerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ComputerControllerTest {

    @InjectMocks private ComputerController computerController;
    @Mock private ComputerServiceImpl computerService;

    private MockMvc mockMvc;
    private FixtureObjects fixture;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(computerController).build();
        fixture = new FixtureObjects();
        mapper = new ObjectMapper();
    }

    @Test
    void when_get_computer_by_id_then_ok() throws Exception {
        Mockito.when(computerService.getComputerById(1L)).thenReturn(fixture.getComputerDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/computers/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(computerService, Mockito.times(1)).getComputerById(1L);
    }

    @Test
    void when_create_computer_then_ok() throws Exception {
        Mockito.when(computerService.createComputer(fixture.getComputerRequestDto())).thenReturn(fixture.getComputerDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/computers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(fixture.getComputerRequestDto()))
        )
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(computerService, Mockito.times(1)).createComputer(fixture.getComputerRequestDto());
    }

    @Test
    void when_delete_computer_then_ok() throws Exception {
        Mockito.doNothing().when(computerService).deleteComputer(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/computers/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(computerService, Mockito.times(1)).deleteComputer(1L);
    }

    @Test
    void when_delete_all_computer_then_ok() throws Exception {
        Mockito.doNothing().when(computerService).deleteAllComputers();

        mockMvc.perform(MockMvcRequestBuilders.delete("/computers")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(computerService, Mockito.times(1)).deleteAllComputers();
    }

    @Test
    void when_update_computer_then_ok() throws Exception {
        Mockito.when(computerService.updateComputer(1L, fixture.getComputerRequestDto())).thenReturn(fixture.getComputerDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/computers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(fixture.getComputerRequestDto()))
        )
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(computerService, Mockito.times(1)).updateComputer(1L, fixture.getComputerRequestDto());
    }
}