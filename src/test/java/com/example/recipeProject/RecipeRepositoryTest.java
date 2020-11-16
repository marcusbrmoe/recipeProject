package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recipeProject.domain.CategoryRepository;
import com.example.recipeProject.domain.CookingStep;
import com.example.recipeProject.domain.Ingredient;
import com.example.recipeProject.domain.Recipe;
import com.example.recipeProject.domain.RecipeRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	@Order(1)
	public void createNewRecipe() {
		Recipe recipe = new Recipe("Sausage", 1, "The best sausage you have ever tasted!", crepository.findByName("Dinner").get(0), new ArrayList<Ingredient>(), new ArrayList<CookingStep>());
		repository.save(recipe);
		assertThat(recipe.getRecipeId()).isNotNull();
	}
	
	@Test
	@Order(2)
	public void findRecipe() {
		List<Recipe> recipe = repository.findByName("Sausage");
		
		assertThat(recipe).hasSize(1);
		assertThat(recipe.get(0).getName()).isEqualTo("Sausage");
	}
	
	@Test
	@Order(3)
	public void deleteRecipe() {
		List<Recipe> recipe = repository.findByName("Sausage");
		
		assertThat(recipe).hasSize(1);
		
		repository.delete(recipe.get(0));
		
		assertThat(repository.findByName("Sausage")).isNullOrEmpty();
	}

}
