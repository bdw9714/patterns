package com.github.benjaminwunder.patterns.responsibilitychain.model;

public class Email {
	private String from;
	private String to;
	private String message;
	private String subject;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void move(String folderName) {
		System.out.printf("Moving Email [Subject: %s] [From: %s] [To: %s] to folder <%s>.%n", subject, from, to, folderName);
	}

	public void discard() {
		move("Trash");
	}
}
