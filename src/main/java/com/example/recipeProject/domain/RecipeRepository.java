package com.example.recipeProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecipeRepository extends CrudRepository<Recipe, Long>{
	List<Recipe> findByName(String name);
	List<Recipe> findByCategory(Category category);
}
