package com.example.recipeProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.recipeProject.domain.Category;
import com.example.recipeProject.domain.Login;
import com.example.recipeProject.RecipeProjectApplication;
import com.example.recipeProject.domain.CategoryRepository;
import com.example.recipeProject.domain.CookingStep;
import com.example.recipeProject.domain.Ingredient;
import com.example.recipeProject.domain.LoginRepository;
import com.example.recipeProject.domain.MeasuringUnit;
import com.example.recipeProject.domain.MeasuringUnitRepository;
import com.example.recipeProject.domain.Recipe;
import com.example.recipeProject.domain.RecipeRepository;

@SpringBootApplication
public class RecipeProjectApplication {

	private static final Logger log = LoggerFactory.getLogger(RecipeProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RecipeProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner Recipes(RecipeRepository repository, CategoryRepository crepository, 
			LoginRepository lrepository, MeasuringUnitRepository murepository) {
		
		return (args) -> {
		
			log.info("Create users");
			lrepository.save(new Login("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@hh.fi", "USER"));
			lrepository.save(new Login("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@hh.fi", "ADMIN"));
			
			log.info("save a couple of categories");
			crepository.save(new Category("Baking"));
			crepository.save(new Category("Dinner"));
			crepository.save(new Category("Italian"));
			
			log.info("save a couple of measuringunits");
			murepository.save(new MeasuringUnit("Teaspoon(s)"));
			murepository.save(new MeasuringUnit("Eatingspoon(s)"));
			murepository.save(new MeasuringUnit("kg"));
			murepository.save(new MeasuringUnit("g"));
			murepository.save(new MeasuringUnit("l"));
			murepository.save(new MeasuringUnit("dl"));
			
			log.info("save a couple of recipes");
			Recipe pizza = new Recipe("Pizza", 4, "A delicious Italian pizza!", crepository.findByName("Italian").get(0));
			Recipe soup = new Recipe("Soup", 10, "A boring soup...", crepository.findByName("Dinner").get(0));
			
			log.info("save a couple of ingredients");
			Ingredient pizzaFlour = new Ingredient("Flour", 1, murepository.findByName("kg"));
			Ingredient pizzaWater = new Ingredient("Water", 2.5, murepository.findByName("dl"));
			Ingredient soupWater = new Ingredient("Water", 4, murepository.findByName("l"));
			Ingredient soupSalt = new Ingredient("Salt", 4, murepository.findByName("Teaspoon(s)"));
			
			log.info("save a couple of cooking steps");
			CookingStep pizzaOne = new CookingStep(1, "Mix all dry ingredients.");
			CookingStep pizzaTwo = new CookingStep(2, "Add water.");
			CookingStep soupOne = new CookingStep(1, "Boil water.");
			CookingStep soupTwo = new CookingStep(2, "Add salt.");
			
			pizza.addIngredient(pizzaWater);
			pizza.addIngredient(pizzaFlour);
			
			pizza.addStep(pizzaOne);
			pizza.addStep(pizzaTwo);
			
			soup.addIngredient(soupWater);
			soup.addIngredient(soupSalt);
			
			soup.addStep(soupOne);
			soup.addStep(soupTwo);
			
			repository.save(pizza);
			repository.save(soup);
			
		};
	}

}
