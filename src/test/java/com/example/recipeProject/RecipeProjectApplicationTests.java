package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.recipeProject.web.RecipeController;

//@RunWith(SpringRunner.class)
//@SpringBootTest
class RecipeProjectApplicationTests {

	@Autowired
	private RecipeController controller;
	
	@Test 
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
