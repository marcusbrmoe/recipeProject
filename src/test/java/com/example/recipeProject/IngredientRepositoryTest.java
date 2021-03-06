package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.recipeProject.domain.Ingredient;
import com.example.recipeProject.domain.IngredientRepository;
import com.example.recipeProject.domain.MeasuringUnitRepository;

@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class IngredientRepositoryTest {

	@Autowired
	private IngredientRepository repository;
	@Autowired
	private MeasuringUnitRepository murepository;
	
	@Order(1)
	@Test
	public void createNewIngredient() {
		Ingredient ingredient = new Ingredient("Soap", 1.2, murepository.findByName("g"));
		repository.save(ingredient);
		assertThat(ingredient.getIngredientId()).isNotNull();
	}
	
	@Order(2)
	@Test
	public void findIngredient() {
		List<Ingredient> ingredient = repository.findByName("Soap");
		
		assertThat(ingredient).hasSize(1);
		assertThat(ingredient.get(0).getName()).isEqualTo("Soap");
	}
	
	@Order(3)
	@Test
	public void deleteIngredient() {
		List<Ingredient> ingredient = repository.findByName("Soap");
		
		assertThat(ingredient).hasSize(1);
		
		repository.delete(ingredient.get(0));
		
		assertThat(repository.findByName("Soap")).isNullOrEmpty();
	}

}
