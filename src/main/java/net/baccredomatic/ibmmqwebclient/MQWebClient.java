package net.baccredomatic.ibmmqwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MQWebClient extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// Updating javax.xml.bind.JAXBContextFactory to make use of com.sun.xml.bind.v2.ContextFactory from our packaged WAR
		System.setProperty("javax.xml.bind.JAXBContextFactory", "com.sun.xml.bind.v2.ContextFactory");
		return application.sources(MQWebClient.class);
	}

	public static void main(String[] args) {
		//ApplicationContext ctx  = SpringApplication.run(MQWebClient.class, args);
		SpringApplication.run(MQWebClient.class, args);

		// String[] beanNames = ctx.getBeanDefinitionNames();
		// for (String name : beanNames) {
		// 	System.out.println(name);
		// }

		//Not debug code
		System.out.println("http://localhost:8080/mq/");

	}

}
