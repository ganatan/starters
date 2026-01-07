package com.ganatan.starter.modules.chatgpt;

import java.util.List;

public class ChatGptRequest {
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
