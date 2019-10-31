package fr.gtm.demo;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

@Stateful
@StatefulTimeout(unit = TimeUnit.MINUTES, value = 10)
public class Compteur {
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
