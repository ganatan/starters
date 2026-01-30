package com.ganatan.starter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ganatan.starter.modules.llm.ChatGptMock;
import com.ganatan.starter.modules.llm.ChatGptService;
import com.ganatan.starter.modules.llm.ClaudeMock;
import com.ganatan.starter.modules.llm.ClaudeService;
import com.ganatan.starter.modules.llm.LlmController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LlmController.class)
@Import(LlmControllerTest.MockConfig.class)
class LlmControllerTest {

    @TestConfiguration
    static class MockConfig {
        @Bean ChatGptService chatGptService() { return Mockito.mock(ChatGptService.class); }
        @Bean ClaudeService claudeService() { return Mockito.mock(ClaudeService.class); }
        @Bean ChatGptMock chatGptMock() { return Mockito.mock(ChatGptMock.class); }
        @Bean ClaudeMock claudeMock() { return Mockito.mock(ClaudeMock.class); }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChatGptService chatGptService;

    @Autowired
    private ClaudeService claudeService;

    @Autowired
    private ChatGptMock chatGptMock;

    @Autowired
    private ClaudeMock claudeMock;

    private final String baseUrl = "/api/llm";

    @BeforeEach
    void setup() {
        Mockito.reset(chatGptService, claudeService, chatGptMock, claudeMock);
    }

    @Test
    void handleRequest_shouldReturnErrorWhenMissingQuestion() throws Exception {
        var request = post(baseUrl + "/rag/chatgpt")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}");
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.data").value("missing question in request body"));
    }

    @Test
    void handleRequest_shouldUseChatGptMockWhenUseMockTrue() throws Exception {
        when(chatGptMock.reply(eq("rag"), any())).thenReturn("mock response");
        var request = post(baseUrl + "/rag/chatgpt")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Test question\"}");
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.model").value("chatgpt"))
            .andExpect(jsonPath("$.data").value("mock response"));
    }

    @Test
    void handleRequest_shouldReturnErrorForUnknownProvider() throws Exception {
        var request = post(baseUrl + "/rag/unknown")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Test question\"}");
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(false))
            .andExpect(jsonPath("$.data").value("unknown-provider: unknown"));
    }
}
