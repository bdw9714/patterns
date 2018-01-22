package com.github.benjaminwunder.patterns.responsibilitychain.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.benjaminwunder.patterns.responsibilitychain.model.Email;

public class SpamRule extends BasicRule {
	private List<String> spamDomains = new ArrayList<>();

	public void addSpamDomain(String spamDomain) {
		if (!spamDomains.contains(spamDomain)) {
			spamDomains.add(spamDomain);
		}
	}

	@Override
	public void applyRule(Email email) {
		this.setStopProcessing(false); // If it has been previously set to true it needs to be cleared
		Boolean moved = false;
		for (String spamDomain : spamDomains) {
			if (email.getFrom().toLowerCase().contains(spamDomain.toLowerCase())) {
				email.discard();
				moved = true;
				this.setStopProcessing(true);
			}
		}

		if (!moved)
			System.out.println("SpamRule Does Not Apply");

		callNextRule(email);
	}

}
