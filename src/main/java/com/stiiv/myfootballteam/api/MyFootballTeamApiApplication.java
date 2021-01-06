package com.stiiv.myfootballteam.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFootballTeamApiApplication {

	public static void main(String[] args) {
		// System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "{}");
		SpringApplication.run(MyFootballTeamApiApplication.class, args);
	}

}
