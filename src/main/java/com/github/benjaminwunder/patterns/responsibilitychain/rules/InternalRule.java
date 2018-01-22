package com.github.benjaminwunder.patterns.responsibilitychain.rules;

import com.github.benjaminwunder.patterns.responsibilitychain.model.Email;

public class InternalRule extends BasicRule {
	private String internalDomain;

	@Override
	public void applyRule(Email email) {
		if (email.getFrom().toLowerCase().contains(internalDomain.toLowerCase())) {
			System.out.println("Internal Rule Applies");
			email.move("Internal");
			if (this.isStopProcessing())
				return;
		} else
			System.out.println("Internal Rule Does Not Apply");

		callNextRule(email);
	}

	public String getInternalDomain() {
		return internalDomain;
	}

	public void setInternalDomain(String internalDomain) {
		this.internalDomain = internalDomain;
	}

}
