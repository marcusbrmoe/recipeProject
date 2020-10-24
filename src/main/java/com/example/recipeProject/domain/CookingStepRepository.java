package com.example.recipeProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CookingStepRepository extends CrudRepository<CookingStep, Long> {
	List<CookingStep> findByDesc(String desc);
}
