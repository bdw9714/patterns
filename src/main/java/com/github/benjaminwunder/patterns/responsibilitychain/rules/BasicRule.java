package com.github.benjaminwunder.patterns.responsibilitychain.rules;

import com.github.benjaminwunder.patterns.responsibilitychain.model.Email;

public abstract class BasicRule implements Rule {
	private Boolean stopProcessing;
	private Rule nextRule;

	public BasicRule() {
		stopProcessing = false;
	}

	public void callNextRule(Email email) {
		if (nextRule != null)
			nextRule.applyRule(email);
	}

	// getters and setters
	public Boolean isStopProcessing() {
		return stopProcessing;
	}

	public void setStopProcessing(Boolean stopProcessing) {
		this.stopProcessing = stopProcessing;
	}

	public Rule getNextRule() {
		return nextRule;
	}

	public void setNextRule(Rule nextRule) {
		this.nextRule = nextRule;
	}

}
