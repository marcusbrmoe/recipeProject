package com.example.recipeProject.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long categoryId;
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
	private List<Recipe> recipes;

	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return categoryId;
	}

	public void setId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", recipes=" + recipes + "]";
	}
	
}
