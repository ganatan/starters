package com.ganatan.starter.modules.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService service;

    @InjectMocks
    private PersonController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnAllPersons() throws Exception {
        when(service.getAll())
                .thenReturn(Arrays.asList(new Person(1L, "Robert Downey Jr."), new Person(2L, "Jeremy Renner")));

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Robert Downey Jr."));
    }

    @Test
    void shouldReturnPersonById() throws Exception {
        when(service.getById(1L)).thenReturn(Optional.of(new Person(1L, "Robert Downey Jr.")));

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Robert Downey Jr."));
    }

    @Test
    void shouldReturnNotFoundWhenPersonMissing() throws Exception {
        when(service.getById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/persons/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreatePerson() throws Exception {
        Person saved = new Person(3L, "Chris Evans");
        when(service.create(any(Person.class))).thenReturn(saved);

        mockMvc.perform(post("/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Chris Evans\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Chris Evans"));

        verify(service).create(any(Person.class));
    }

    @Test
    void shouldUpdateExistingPerson() throws Exception {
        Person updated = new Person(1L, "Chris Hemsworth");
        when(service.update(eq(1L), any(Person.class))).thenReturn(Optional.of(updated));

        mockMvc.perform(put("/persons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Chris Hemsworth\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Chris Hemsworth"));
    }

    @Test
    void shouldReturnNotFoundOnUpdateMissingPerson() throws Exception {
        when(service.update(eq(99L), any(Person.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/persons/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Unknown\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteExistingPerson() throws Exception {
        when(service.delete(1L)).thenReturn(true);

        mockMvc.perform(delete("/persons/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundOnDeleteMissingPerson() throws Exception {
        when(service.delete(99L)).thenReturn(false);

        mockMvc.perform(delete("/persons/99"))
                .andExpect(status().isNotFound());
    }
}
