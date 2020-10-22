package com.example.recipeProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
	List<Ingredient> findByName(String name);
}
