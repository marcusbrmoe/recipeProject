package com.example.recipeProject.domain;

import org.springframework.data.repository.CrudRepository;

public interface MeasuringUnitRepository extends CrudRepository<MeasuringUnit, Long> {
	MeasuringUnit findByName(String name);
}
