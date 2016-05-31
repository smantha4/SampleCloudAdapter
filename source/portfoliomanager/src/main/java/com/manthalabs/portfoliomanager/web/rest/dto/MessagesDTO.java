package com.manthalabs.portfoliomanager.web.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class MessagesDTO {

	private List<MessageDTO> messages = new ArrayList<>();

	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}

	public static class MessageDTO {
		private String message;

		public MessageDTO() {
		}

		public MessageDTO(String m) {
			this.message = m;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

	}

}
