package teymi15.kassistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import teymi15.model.Ingredient;
import teymi15.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class extends the Spring Boot Servlet requires to run the project
 * for the URL given by the client.
 */
@SpringBootApplication
public class KassistantApplication extends SpringBootServletInitializer{
	private static final Logger log = LoggerFactory.getLogger(KassistantApplication.class);
	/**
	 * This is the protected funcion which configurate the Spring Application
	 * @param applicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
		return applicationBuilder.sources(KassistantApplication.class);
	}
	/**
	 * This is the main method which makes use of the Spring Application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(KassistantApplication.class, args);
	}


}
