package net.baccredomatic.ibmmqwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MQWebClient {

	public static void main(String[] args) {
		//ApplicationContext ctx  = SpringApplication.run(MQWebClient.class, args);
		SpringApplication.run(MQWebClient.class, args);

		// String[] beanNames = ctx.getBeanDefinitionNames();
		// for (String name : beanNames) {
		// 	System.out.println(name);
		// }

		//Not debug code
		System.out.println("http://localhost:8080/");

	}

}
