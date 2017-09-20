package teymi15.kassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import teymi15.kassistant.control.SearchController;
import teymi15.kassistant.model.Ingredient;
import teymi15.kassistant.model.Recipe;
import teymi15.kassistant.repository.RecipeRepositoryImp;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class extends the Spring Boot Servlet requires to run the project
 * for the URL given by the client.
 */
@SpringBootApplication
public class KassistantApplication extends SpringBootServletInitializer{
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
		//some dummy data to the repository
		RecipeRepositoryImp res = new RecipeRepositoryImp();
		List<Ingredient> in = new ArrayList<>();
		in.add(new Ingredient(1,65, "chickpeas", "Grandi", "Bónus"));
		in.add(new Ingredient(2,65, "Olive oil", "Grandi", "Bónus"));
		res.add(new Recipe(1,"hummus","take chickpeas and cruz them and add oliv oil",in));
		in.clear();
		in.add(new Ingredient(1,65, "chickpeas", "Grandi", "Bónus"));
		in.add(new Ingredient(3,65, "Pasta", "Grandi", "Bónus"));
		res.add(new Recipe(1,"pasta with bean","just pasta bro",in));
		in.clear();
		in.add(new Ingredient(4,65, "vegitables", "Grandi", "Bónus"));
		in.add(new Ingredient(5,65, "tofu", "Grandi", "Bónus"));
		res.add(new Recipe(1,"stirfry","just stir fry",in));

		SearchController search = new SearchController();
		search.searchRecipeByName("hummus");
		//launch the Spring Application
		SpringApplication.run(KassistantApplication.class, args);
	}


}
