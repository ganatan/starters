package com.ganatan.starter.config;

import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class AiServicesConfig {

	public AiServicesConfig() {
	}

	public static Map<String, List<Map<String, String>>> getAllServices() {
		return Map.of("llm",
				List.of(create("chatgpt", "OpenAI", "Text generation, summarization, Q&A, code completion"),
						create("claude", "Claude", "Structured reasoning, content writing, safe dialogue"),
						create("gemini", "Gemini", "Multimodal LLM for text and image understanding"),
						create("mistral", "Mistral", "Open-source LLM for high-performance text/code tasks"),
						create("perplexity", "Perplexity AI", "Web-augmented search engine powered by LLM"),
						create("deepseek", "DeepSeek", "Code generation, explanation and debugging assistant")),
				"tts",
				List.of(create("elevenlabs", "ElevenLabs", "High-quality voice synthesis from text, multilingual")),
				"avatar",
				List.of(create("did", "D-ID", "Animate a still photo with audio or text"),
						create("heygen", "Heygen", "Generate talking avatar videos from script"),
						create("jogg", "Jogg AI", "Create realistic talking avatars from custom photos")),
				"image",
				List.of(create("leonardo", "Leonardo AI", "Create illustrations, concept art and product visuals"),
						create("midjourney", "MidJourney", "Stylized artistic image generation from prompt"),
						create("kling", "Kling AI", "Future video generation from text (Sora-level quality)")),
				"agent",
				List.of(create("langchain", "LangChain", "Chain tools, memory, and LLMs into intelligent agents"),
						create("llamaindex", "LlamaIndex", "Connect LLMs to data sources, documents, and files")),
				"music", List
						.of(create("suno", "Suno AI", "Generate full songs with lyrics, melody, and vocals"),
								create("udio", "Udio AI", "Generate high-quality vocal music tracks from prompt")),
				"rag",
				List.of(create("qdrant", "Qdrant",
						"Open-source vector database optimized for semantic search and RAG pipelines (v1.11+)"),
						create("openai-embeddings", "OpenAI Embeddings",
								"text-embedding-3-small model for efficient vector generation and retrieval"),
						create("langchain", "LangChain",
								"Framework for building RAG pipelines: retrieval, context building, and LLM orchestration (v0.3+)"),
						create("llm", "GPT-4o / Claude 3 Opus",
								"Large Language Models used for contextual generation: GPT-4o for speed, Claude 3 for precision"))

		);
	}

	private static Map<String, String> create(String type, String label, String purpose) {
		return Map.of("type", type, "label", label, "purpose", purpose);
	}
}
