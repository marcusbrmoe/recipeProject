package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.recipeProject.domain.Category;
import com.example.recipeProject.domain.CookingStep;
import com.example.recipeProject.domain.Ingredient;
import com.example.recipeProject.domain.Recipe;
import com.example.recipeProject.domain.RecipeRepository;

@DataJpaTest
class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository repository;
	
	@Test
	public void createNewRecipe() {
		Recipe recipe = new Recipe("Sausage", 1, "The best sausage you have ever tasted!", new Category("Tasty"), new ArrayList<Ingredient>(), new ArrayList<CookingStep>());
		repository.save(recipe);
		assertThat(recipe.getRecipeId()).isNotNull();
	}
	
	@Test
	public void findRecipe() {
		List<Recipe> recipe = repository.findByName("Pizza");
		
		assertThat(recipe).hasSize(1);
		assertThat(recipe.get(0).getName()).isEqualTo("Pizza");
	}
	
	@Test
	public void deleteRecipe() {
		List<Recipe> recipe = repository.findByName("Pizza");
		
		assertThat(recipe).hasSize(1);
		
		repository.delete(recipe.get(0));
		
		assertThat(repository.findByName("Pizza")).isNullOrEmpty();
	}

}
