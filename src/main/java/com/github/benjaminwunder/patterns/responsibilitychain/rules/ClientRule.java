package com.github.benjaminwunder.patterns.responsibilitychain.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.benjaminwunder.patterns.responsibilitychain.model.Email;

public class ClientRule extends BasicRule {
	private List<String> clientDomains = new ArrayList<>();

	public void addClient(String clientEmail) {
		if (!clientDomains.contains(clientEmail))
			clientDomains.add(clientEmail);
	}

	@Override
	public void applyRule(Email email) {
		this.setStopProcessing(false);
		Boolean moved = false;
		for (String domain : clientDomains) {
			if (email.getFrom().toLowerCase().contains(domain.toLowerCase())) {
				email.move("Clients");
				moved = true;
				this.setStopProcessing(true);
			}
		}

		if (!moved)
			System.out.println("ClientRule Does Not Apply");

		callNextRule(email);
	}

}
