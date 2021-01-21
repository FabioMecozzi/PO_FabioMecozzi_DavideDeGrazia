package com.example2.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Classe che contiene il main per avviare l'applicazione Springboot
 * @author meefa
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

}
