package com.ganatan.starter.modules.chatgpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ChatGptService {

	private static final String BASE_URL = "https://api.openai.com/v1";
	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String CONTENT_TYPE_JSON = "application/json";
	private static final String MODEL = "gpt-4-turbo";
	private static final String ROLE_USER = "user";
	private static final String ENDPOINT = "/chat/completions";

	private final WebClient webClient;
	private final boolean useMock;
	private final ObjectMapper mapper = new ObjectMapper();

	public ChatGptService(@Value("${openai.api.key}") String apiKey, @Value("${use.mock:false}") boolean useMock,
			WebClient.Builder builder) {
		String authorizationHeader = "Bearer " + apiKey;

		this.webClient = builder.baseUrl(BASE_URL).defaultHeader(HEADER_AUTHORIZATION, authorizationHeader)
				.defaultHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON).build();

		this.useMock = useMock;
	}

	public String reply(String prompt) {
		System.out.println("00000000001:" + prompt);
		if (useMock) {
			return "Réponse simulée (mock) pour : " + prompt;
		}
		System.out.println("00000000002:" + prompt);

		ChatGptRequest request = new ChatGptRequest(MODEL, List.of(new ChatGptRequest.Message(ROLE_USER, prompt)));

		System.out.println("00000000003:" + prompt);

		ChatGptResponse response = webClient.post().uri(ENDPOINT).bodyValue(request).retrieve()
				.bodyToMono(ChatGptResponse.class).block();
		System.out.println("00000000004:" + response);
		logJson("00000000005:response", response);

		return response.getChoices().get(0).getMessage().getContent().trim();
	}

	private void logJson(String label, Object obj) {
		try {
			String json = mapper.writeValueAsString(obj);
			System.out.println(label + " -> " + json);
		} catch (Exception e) {
			System.err.println("Erreur lors de la sérialisation JSON: " + e.getMessage());
		}
	}
}
