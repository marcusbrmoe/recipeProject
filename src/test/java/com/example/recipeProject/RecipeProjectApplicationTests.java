package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.recipeProject.web.RecipeController;

@DataJpaTest
class RecipeProjectApplicationTests {

	@Autowired
	private RecipeController controller;
	
	@Test 
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
