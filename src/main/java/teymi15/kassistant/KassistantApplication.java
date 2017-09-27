package teymi15.kassistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import teymi15.kassistant.control.SearchController;

/**
 * The main class extends the Spring Boot Servlet requires to run the project
 * for the URL given by the client.
 */
@SpringBootApplication
public class KassistantApplication extends SpringBootServletInitializer{
	private static final Logger log = LoggerFactory.getLogger(KassistantApplication.class);
	/**
	 * This is the protected function which configurate the Spring Application
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
		SearchController search = new SearchController();

		System.out.println(search.searchRecipeByName("hummus"));
		//launch the Spring Application
		SpringApplication.run(KassistantApplication.class, args);
	}


}
