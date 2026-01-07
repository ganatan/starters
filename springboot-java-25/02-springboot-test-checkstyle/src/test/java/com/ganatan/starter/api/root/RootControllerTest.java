package com.ganatan.starter.api.root;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RootControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void root_shouldReturnApplicationStatusAndJavaVersion() throws Exception {
    mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.application").value("springboot-starter"))
      .andExpect(jsonPath("$.status").value("running"))
      .andExpect(jsonPath("$.java").isNotEmpty());
  }
}
