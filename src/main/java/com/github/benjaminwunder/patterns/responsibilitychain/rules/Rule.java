package com.github.benjaminwunder.patterns.responsibilitychain.rules;

import com.github.benjaminwunder.patterns.responsibilitychain.model.Email;

public interface Rule {
	public void applyRule(Email email);
}
