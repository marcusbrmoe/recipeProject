package com.example.recipeProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CookingStepRepository extends CrudRepository<CookingStep, Long> {
	List<CookingStep> findByDesc(String desc);
}
