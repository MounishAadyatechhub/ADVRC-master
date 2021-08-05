package com.pyro.advance.recharge;

import com.pyro.advance.recharge.dbo.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class AdvanceRechargeApplication {

	@Autowired
	DatabaseOperation databaseOperation;

	public static void main(String[] args) {
		SpringApplication.run(AdvanceRechargeApplication.class, args);
	}


	@PostConstruct
	public void init() {
		databaseOperation.getAllTrustedUsers();
	}
}
/*
Trusted user load in init method
Common method Hash map create and add
 */