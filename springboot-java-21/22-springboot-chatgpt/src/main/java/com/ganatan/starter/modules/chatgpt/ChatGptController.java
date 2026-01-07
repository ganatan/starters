package com.ganatan.starter.modules.chatgpt;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatgpt")
public class ChatGptController {

	private final ChatGptService chatGptService;

	public ChatGptController(ChatGptService chatGptService) {
		this.chatGptService = chatGptService;
	}

	@GetMapping
	public String ask(@RequestParam String prompt) {
		return chatGptService.reply(prompt);
	}

	@PostMapping
	public String askPost(@RequestBody String prompt) {
		return chatGptService.reply(prompt);
	}
}
