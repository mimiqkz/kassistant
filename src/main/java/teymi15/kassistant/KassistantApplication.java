package teymi15.kassistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import teymi15.kassistant.control.SearchController;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.repository.IngredientRepository;

import java.util.List;

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
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		IngredientRepository ingredientRepository = context.getBean(IngredientRepository.class);

		Ingredient ingredient = new Ingredient();
		ingredient.setName("Pankaj"); ingredient.setLocation("India");
		ingredient.setLocation("121"); ingredient.setID(123);


		ingredientRepository.save(ingredient);

		System.out.println("Person::"+ingredient);

		List<Ingredient> list = ingredientRepository.findAll();

		for(Ingredient p : list){
			System.out.println("Person List::"+p);
		}
		//close resources
		context.close();*/
		SpringApplication.run(KassistantApplication.class, args);
	}


}
