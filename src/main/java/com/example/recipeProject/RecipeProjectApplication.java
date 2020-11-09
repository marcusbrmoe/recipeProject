package com.example.recipeProject;

import java.util.ArrayList;

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
import com.example.recipeProject.domain.CookingStepRepository;
import com.example.recipeProject.domain.Ingredient;
import com.example.recipeProject.domain.IngredientRepository;
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
			LoginRepository lrepository, MeasuringUnitRepository murepository, IngredientRepository irepository,
			CookingStepRepository csrepository) {
		
		return (args) -> {
			/*
			log.info("Delete all users");
			lrepository.deleteAll();
			log.info("Create users");
			lrepository.save(new Login("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@hh.fi", "USER"));
			lrepository.save(new Login("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@hh.fi", "ADMIN"));
			
			log.info("Delete all categories");
			crepository.deleteAll();
			log.info("save a couple of categories");
			crepository.save(new Category("Dessert"));
			crepository.save(new Category("Dinner"));
			crepository.save(new Category("Italian"));
			crepository.save(new Category("Cake"));
			crepository.save(new Category("Indian"));
			
			
			log.info("Delete all measuring units");
			murepository.deleteAll();
			log.info("save a couple of measuringunits");
			murepository.save(new MeasuringUnit("Teaspoon(s)"));
			murepository.save(new MeasuringUnit("Tablespoon(s)"));
			murepository.save(new MeasuringUnit("kg"));
			murepository.save(new MeasuringUnit("g"));
			murepository.save(new MeasuringUnit("l"));
			murepository.save(new MeasuringUnit("dl"));
			murepository.save(new MeasuringUnit("piece(s)"));
			
			log.info("create a couple of recipes");
			Recipe pizza = new Recipe("Pizza", 4, "A delicious Italian pizza!", crepository.findByName("Italian").get(0));
			Recipe soup = new Recipe("Soup", 10, "A boring soup...", crepository.findByName("Dinner").get(0));
			
			log.info("save a couple of recipes");
			repository.save(pizza);
			repository.save(soup);
			repository.save(new Recipe("Cookies", 4, "The best chocolate chip cookie you have ever tasted!", crepository.findByName("Italian").get(0), new ArrayList<Ingredient>(), new ArrayList<CookingStep>()));
			
			log.info("create a couple of ingredients");
			Ingredient pizzaFlour = new Ingredient("Flour", 1, murepository.findByName("kg"), pizza);
			Ingredient pizzaWater = new Ingredient("Water", 2.5, murepository.findByName("dl"), pizza);
			Ingredient soupWater = new Ingredient("Water", 4, murepository.findByName("l"), soup);
			Ingredient soupSalt = new Ingredient("Salt", 10, murepository.findByName("Teaspoon(s)"), soup);
			
			irepository.save(pizzaFlour);
			irepository.save(pizzaWater);
			irepository.save(soupWater);
			irepository.save(soupSalt);
			
			log.info("create a couple of cooking steps");
			CookingStep pizzaOne = new CookingStep(1, "Mix all dry ingredients.", pizza);
			CookingStep pizzaTwo = new CookingStep(2, "Add water.", pizza);
			CookingStep soupOne = new CookingStep(1, "Boil water.", soup);
			CookingStep soupTwo = new CookingStep(2, "Add salt.", soup);
			
			csrepository.save(pizzaOne);
			csrepository.save(pizzaTwo);
			csrepository.save(soupOne);
			csrepository.save(soupTwo);
			*/
		};
	}

}