package com.endava.bugHunting.bug_hunting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BugHuntingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugHuntingApplication.class, args);
	}

}
