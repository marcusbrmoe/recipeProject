package com.example.recipeProject.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MeasuringUnitRepository extends CrudRepository<MeasuringUnit, Long> {
	MeasuringUnit findByName(String name);
}
