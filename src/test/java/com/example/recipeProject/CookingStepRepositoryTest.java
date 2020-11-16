package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recipeProject.domain.CookingStep;
import com.example.recipeProject.domain.CookingStepRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CookingStepRepositoryTest {

	@Autowired
	private CookingStepRepository repository;
	
	@Test
	@Order(1)
	public void createNewCookingStep() {
		CookingStep step = new CookingStep(1, "Cook 1 hour");
		repository.save(step);
		assertThat(step.getCookingStepId()).isNotNull();
	}
	
	@Test
	@Order(2)
	public void findCookingStep() {
		List<CookingStep> step = repository.findByDescription("Cook 1 hour");
		
		assertThat(step).hasSize(1);
		assertThat(step.get(0).getDescription()).isEqualTo("Cook 1 hour");
	}
	
	@Test
	@Order(3)
	public void deleteCookingStep() {
		List<CookingStep> step = repository.findByDescription("Cook 1 hour");
		
		assertThat(step).hasSize(1);
		
		repository.delete(step.get(0));
		
		assertThat(repository.findByDescription("Cook 1 hour")).isNullOrEmpty();
	}
}
