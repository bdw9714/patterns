package com.github.benjaminwunder.patterns.responsibilitychain;

import com.github.benjaminwunder.patterns.responsibilitychain.model.Email;
import com.github.benjaminwunder.patterns.responsibilitychain.rules.ClientRule;
import com.github.benjaminwunder.patterns.responsibilitychain.rules.InternalRule;
import com.github.benjaminwunder.patterns.responsibilitychain.rules.Rule;
import com.github.benjaminwunder.patterns.responsibilitychain.rules.SpamRule;

public class RulesProcessor {

	private Rule firstRule;

	public RulesProcessor() {
		firstRule = setupRules();
	}

	private SpamRule setupSpamRule() {
		SpamRule spamRule = new SpamRule();

		// Sourced from http://www.joewein.de/
		spamRule.addSpamDomain("usawarriorwatchher.com");
		spamRule.addSpamDomain("eextrabingoseurodice.pl");
		spamRule.addSpamDomain("rl-mail.com");
		spamRule.addSpamDomain("busiic.com");
		spamRule.addSpamDomain("propertysnap.net");
		spamRule.addSpamDomain("kelly-pomerleau.us.org");
		spamRule.addSpamDomain("mycoachoutletstore-us.com");
		spamRule.addSpamDomain("learntorunexistencetoday.biz");
		spamRule.addSpamDomain("all4yoynow.info");
		spamRule.addSpamDomain("cassie-bury.us");

		spamRule.setStopProcessing(true);
		return spamRule;
	}

	private InternalRule setupInternalRule() {
		InternalRule internalRule = new InternalRule();

		internalRule.setInternalDomain("mycompany.com");
		internalRule.setStopProcessing(true);
		return internalRule;
	}

	private ClientRule setupClientRule() {
		ClientRule clientRule = new ClientRule();
		clientRule.addClient("microsoft.com");
		clientRule.addClient("dell.com");
		clientRule.addClient("amazon.com");
		clientRule.addClient("ebay.com");

		clientRule.setStopProcessing(true);
		return clientRule;
	}

	private Rule setupRules() {
		ClientRule clientRule = setupClientRule();
		SpamRule spamRule = setupSpamRule();
		InternalRule internalRule = setupInternalRule();

		clientRule.setNextRule(spamRule);
		spamRule.setNextRule(internalRule);
		return clientRule;
	}

	public void processRules(Email email) {
		firstRule.applyRule(email);
	}

	public static void main(String[] args) {
		RulesProcessor rulesProcessor = new RulesProcessor();

		System.out.println("Testing Client Functionality...");
		Email clientEmail = new Email();
		clientEmail.setFrom("support@dell.com");
		clientEmail.setSubject("Requesting Support");
		clientEmail.setMessage("Can you help us with issue TX-123?");
		clientEmail.setTo("benjaminwunder@gmail.com");
		rulesProcessor.processRules(clientEmail);

		System.out.println("\nTesting Spam Functionality...");
		// Test Spam Functionality
		Email spamEmail = new Email();
		spamEmail.setFrom("spammer@all4yoynow.info");
		spamEmail.setSubject("Please Your Have Attention");
		spamEmail.setMessage("I am in desparate need of wiring $300,000 to your country!  Please reply back!");
		spamEmail.setTo("benjaminwunder@gmail.com");
		rulesProcessor.processRules(spamEmail);

		System.out.println("\nTesting Internal Functionality...");
		Email internalEmail = new Email();
		internalEmail.setFrom("joeblow@mycompany.com");
		internalEmail.setSubject("Lunch?");
		internalEmail.setMessage("Chipotle 12-ish?");
		internalEmail.setTo("benjaminwunder@gmail.com");
		rulesProcessor.processRules(internalEmail);
	}
}
