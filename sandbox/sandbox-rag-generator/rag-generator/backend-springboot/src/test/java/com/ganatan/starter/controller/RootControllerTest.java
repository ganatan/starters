package com.ganatan.starter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RootController.class)
class RootControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRootInfo_shouldReturnAllServices_successTrue() throws Exception {
        // Arrange
        var request = get("/").contentType(MediaType.APPLICATION_JSON);
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data.llm", not(empty())))
            .andExpect(jsonPath("$.data.tts[0].label", is("ElevenLabs")))
            .andExpect(jsonPath("$.data.avatar[*].type", hasItem("jogg")))
            .andExpect(jsonPath("$.data.music[*].label", hasItems("Suno AI", "Udio AI")));
    }

    @Test
    void testGet_shouldReturnExpectedResponse() throws Exception {
        // Arrange
        var request = get("/api/test").contentType(MediaType.APPLICATION_JSON);
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("GET"))
            .andExpect(jsonPath("$.message").value("GET request successful"));
    }

    @Test
    void testPost_shouldReturnBodyWhenProvided() throws Exception {
        // Arrange
        var body = "{\"name\":\"John\"}";
        var request = post("/api/test").contentType(MediaType.APPLICATION_JSON).content(body);
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("POST"))
            .andExpect(jsonPath("$.received.name").value("John"));
    }

    @Test
    void testPost_shouldReturnNoBodyProvidedWhenBodyMissing() throws Exception {
        // Arrange
        var request = post("/api/test").contentType(MediaType.APPLICATION_JSON);
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("POST"))
            .andExpect(jsonPath("$.received.info").value("no body provided"));
    }

    @Test
    void testPut_shouldReturnUpdatedBodyWhenProvided() throws Exception {
        // Arrange
        var body = "{\"status\":\"updated\"}";
        var request = put("/api/test").contentType(MediaType.APPLICATION_JSON).content(body);
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("PUT"))
            .andExpect(jsonPath("$.updated.status").value("updated"));
    }

    @Test
    void testPut_shouldReturnNoBodyProvidedWhenMissing() throws Exception {
        // Arrange
        var request = put("/api/test").contentType(MediaType.APPLICATION_JSON);
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("PUT"))
            .andExpect(jsonPath("$.updated.info").value("no body provided"));
    }

    @Test
    void testDelete_shouldReturnDeletedIdWhenProvided() throws Exception {
        // Arrange
        var request = delete("/api/test").param("id", "123");
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("DELETE"))
            .andExpect(jsonPath("$.deletedId").value("123"));
    }

    @Test
    void testDelete_shouldReturnUndefinedWhenIdMissing() throws Exception {
        // Arrange
        var request = delete("/api/test");
        // Act & Assert
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.method").value("DELETE"))
            .andExpect(jsonPath("$.deletedId").value("undefined"));
    }
}
