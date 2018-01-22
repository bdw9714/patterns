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
		for (String spamDomain : spamDomains) {
			if (email.getFrom().toLowerCase().contains(spamDomain.toLowerCase())) {
				System.out.println("Spam Rule Applies");
				email.discard();
				if (this.isStopProcessing())
					return;
			}
		}

		System.out.println("Spam Rule Does Not Apply");

		callNextRule(email);
	}

}
