package com.ganatan.starter.mock.llm;

import org.junit.jupiter.api.Test;

import com.ganatan.starter.modules.llm.ChatGptMock;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ChatGptMockTest {

    private final ChatGptMock chatGptMock = new ChatGptMock();

    @Test
    void reply_shouldReturnFormattedResponse_forBiography() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Albert-Einstein");
        input.put("style", "formal");
        input.put("length", "long");
        // Act
        String result = chatGptMock.reply("biography", input);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("biography"));
        assertTrue(result.contains("Albert Einstein"));
        assertTrue(result.contains("formal"));
        assertTrue(result.contains("long"));
        assertTrue(result.contains("chatgpt"));
    }

    @Test
    void reply_shouldUseDefaultValues_whenInputIsEmpty() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        // Act
        String result = chatGptMock.reply("summary", input);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Inconnu"));
        assertTrue(result.contains("neutral"));
        assertTrue(result.contains("medium"));
    }

    @Test
    void reply_shouldDefaultToContenu_whenTypeIsInvalid() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Test");
        // Act
        String result = chatGptMock.reply("invalid-type", input);
        // Assert
        assertNotNull(result);
        assertTrue(result.contains("contenu"));
    }

    @Test
    void reply_shouldNormalizeName_whenContainsHyphens() {
        // Arrange
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Jean-Paul-Sartre");
        // Act
        String result = chatGptMock.reply("biography", input);
        // Assert
        assertTrue(result.contains("Jean Paul Sartre"));
        assertFalse(result.contains("Jean-Paul-Sartre"));
    }
}
