package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.recipeProject.domain.Category;
import com.example.recipeProject.domain.CategoryRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	
	@Test
	@Order(1)
	public void createNewCategory() {
		Category category = new Category("Tasty");
		repository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
	@Test
	@Order(2)
	public void findCategory() {
		List<Category> category = repository.findByName("Tasty");
		
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getName()).isEqualTo("Tasty");
	}
	
	@Test
	@Order(3)
	public void deleteCategory() {
		List<Category> category = repository.findByName("Tasty");
		
		assertThat(category).hasSize(1);
		
		repository.delete(category.get(0));
		
		assertThat(repository.findByName("Tasty")).isNullOrEmpty();
	}
}
