package fr.gtm.demo;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Compteur implements Serializable {
	private int value;
	public static final Logger tchikita = Logger.getLogger("Demo");

	@PostConstruct
	public void postconstruct() {
		tchikita.info("Compteur - @PostConstruct");
	}

	@PreDestroy
	public void destroy() {
		tchikita.info("Compteur - @PreDestroy");
	}
	
	@Remove
	public void remove() {
		
	}

	public int getValue() {
		return value;
	}

	public void incrementer() {
		value++;
	}

}
