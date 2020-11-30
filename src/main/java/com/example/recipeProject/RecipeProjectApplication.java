package com.example.recipeProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.recipeProject.RecipeProjectApplication;
import com.example.recipeProject.domain.CategoryRepository;
import com.example.recipeProject.domain.CookingStepRepository;
import com.example.recipeProject.domain.IngredientRepository;
import com.example.recipeProject.domain.LoginRepository;
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
	
			log.info("Show a couple of Ingredients");
			for (Recipe recipe : repository.findAll()) {
				System.out.println(recipe.getName());
			}
			
		};
	}

}