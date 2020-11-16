package com.example.recipeProject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

//import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.recipeProject.domain.Category;
import com.example.recipeProject.domain.CategoryRepository;

@DataJpaTest
//@TestMethodOrder(OrderAnnotation.class)
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;
	
	
	//@Order(1)
	@Test
	public void createNewCategory() {
		Category category = new Category("Tasty");
		repository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
	
	//@Order(2)
	@Test
	public void findCategory() {
		List<Category> category = repository.findByName("Tasty");
		
		assertThat(category).hasSize(1);
		assertThat(category.get(0).getName()).isEqualTo("Tasty");
	}
	
	
	//@Order(3)
	@Test
	public void deleteCategory() {
		List<Category> category = repository.findByName("Tasty");
		
		assertThat(category).hasSize(1);
		
		repository.delete(category.get(0));
		
		assertThat(repository.findByName("Tasty")).isNullOrEmpty();
	}
}
