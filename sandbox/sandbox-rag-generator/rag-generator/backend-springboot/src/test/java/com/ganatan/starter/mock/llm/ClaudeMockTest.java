package com.ganatan.starter.mock.llm;

import org.junit.jupiter.api.Test;

import com.ganatan.starter.modules.llm.ClaudeMock;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ClaudeMockTest {

    private final ClaudeMock claudeMock = new ClaudeMock();

    @Test
    void reply_shouldReturnBiographyWithAllParameters() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Albert-Einstein");
        input.put("style", "formal");
        input.put("length", "long");
        // Act
        String result = claudeMock.reply("biography", input);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("biography"));
        assertTrue(result.contains("Albert Einstein"));
        assertTrue(result.contains("formal"));
        assertTrue(result.contains("long"));
        assertTrue(result.contains("claude"));
    }

    @Test
    void reply_shouldReturnDefaultValuesWhenInputIsEmpty() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        // Act
        String result = claudeMock.reply("summary", input);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Inconnu"));
        assertTrue(result.contains("neutral"));
        assertTrue(result.contains("medium"));
    }

    @Test
    void reply_shouldReturnContenuWhenTypeIsInvalid() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Test");
        // Act
        String result = claudeMock.reply("invalid-type", input);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("contenu"));
    }

    @Test
    void reply_shouldNormalizeNameByReplacingHyphens() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Jean-Paul-Sartre");
        // Act
        String result = claudeMock.reply("biography", input);
        // Assert
        assertTrue(result.contains("Jean Paul Sartre"));
        assertFalse(result.contains("Jean-Paul-Sartre"));
    }

    @Test
    void reply_shouldHandleNonStringValuesWithDefaults() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", 12345);
        input.put("style", null);
        input.put("length", new Object());
        // Act
        String result = claudeMock.reply("filmography", input);
        // Assert
        assertTrue(result.contains("Inconnu"));
        assertTrue(result.contains("neutral"));
        assertTrue(result.contains("medium"));
    }
}
