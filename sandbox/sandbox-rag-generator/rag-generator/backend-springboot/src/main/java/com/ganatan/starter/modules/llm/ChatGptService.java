package com.ganatan.starter.modules.llm;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

	private static final String BASE_URL = "https://api.openai.com/v1";
	private static final String ENDPOINT = "/chat/completions";
	private static final String MODEL = "gpt-4-turbo";
	private static final String ROLE_USER = "user";

	private final WebClient webClient;

	private static final Map<String, String> styleMap = Map.ofEntries(
			Map.entry("neutral", "neutre, objectif, informatif sans émotion"),
			Map.entry("casual", "décontracté, langage simple et familier"),
			Map.entry("technical", "axé sur les faits techniques et professionnels"),
			Map.entry("narrative", "raconté comme une histoire avec du rythme"),
			Map.entry("press", "journalistique, structuré comme un article de presse"),
			Map.entry("humorous", "humoristique, ton léger et amusant"),
			Map.entry("poetic", "poétique, style littéraire et imagé"),
			Map.entry("dramatic", "dramatique, avec tension et intensité émotionnelle"),
			Map.entry("emotional", "émotionnel, centré sur les sentiments et l’empathie"),
			Map.entry("cinematic", "cinématographique, ambiance visuelle et descriptive comme un film"),
			Map.entry("historical", "historique, avec mise en contexte chronologique"),
			Map.entry("marketing", "marketing, valorisant avec un ton accrocheur"),
			Map.entry("scientific", "scientifique, ton analytique et factuel"),
			Map.entry("satirical", "satirique, critique subtile et ironique"),
			Map.entry("inspirational", "inspirant, motivant avec des citations et une mise en valeur"),
			Map.entry("minimal", "très court, phrases simples et dépouillées"),
			Map.entry("dialog", "rédigé sous forme de dialogue entre deux personnes"),
			Map.entry("interview", "présenté comme une interview fictive, questions-réponses"));

	private static final Map<String, String> lengthMap = Map.of("short", "environ 30 mots, réponse très concise",
			"medium", "environ 60 mots, réponse équilibrée", "long",
			"environ 100 mots, réponse développée mais synthétique");

	public ChatGptService(@Value("${openai.api.key}") String apiKey, WebClient.Builder builder) {
		this.webClient = builder.baseUrl(BASE_URL).defaultHeader("Authorization", "Bearer " + apiKey)
				.defaultHeader("Content-Type", "application/json").build();
	}

	public String reply(String mode, Map<String, Object> input) {
		try {
			String question = input.getOrDefault("name", "inconnue").toString();
			String rawStyle = input.getOrDefault("style", "neutral").toString();
			String rawLength = input.getOrDefault("length", "medium").toString();

			String style = styleMap.getOrDefault(rawStyle, styleMap.get("neutral"));
			String length = lengthMap.getOrDefault(rawLength, lengthMap.get("medium"));

			String prompt = "rag".equals(mode)
					? String.format("Réponds à la question avec récupération de contexte : %s. Style %s, %s.", question,
							style, length)
					: String.format("Réponds directement à la question : %s. Style %s, %s.", question, style, length);

			ChatGptRequest request = new ChatGptRequest(MODEL, List.of(new ChatGptRequest.Message(ROLE_USER, prompt)));

			ChatGptResponse response = webClient.post().uri(ENDPOINT).bodyValue(request).retrieve()
					.bodyToMono(ChatGptResponse.class).onErrorResume(e -> {
						System.err.println("❌ ChatGptService error: " + e.getMessage());
						return Mono.empty();
					}).block();

			if (response == null || response.getChoices().isEmpty()) {
				return "Erreur : pas de réponse reçue.";
			}

			return response.getChoices().get(0).getMessage().getContent().trim();
		} catch (Exception e) {
			System.err.println("❌ ChatGptService exception: " + e.getMessage());
			return "Erreur lors de l’appel à l’API OpenAI.";
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ChatGptRequest {
		private String model;
		private List<Message> messages;

		public ChatGptRequest(String model, List<Message> messages) {
			this.model = model;
			this.messages = messages;
		}

		public String getModel() {
			return model;
		}

		public List<Message> getMessages() {
			return messages;
		}

		public static class Message {
			private String role;
			private String content;

			public Message(String role, String content) {
				this.role = role;
				this.content = content;
			}

			public String getRole() {
				return role;
			}

			public String getContent() {
				return content;
			}
		}
	}

	public static class ChatGptResponse {
		private List<Choice> choices;

		public List<Choice> getChoices() {
			return choices;
		}

		public static class Choice {
			private Message message;

			public Message getMessage() {
				return message;
			}
		}

		public static class Message {
			private String content;

			public String getContent() {
				return content;
			}
		}
	}
}
