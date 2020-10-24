package com.example.recipeProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="ingredient")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ingredient_id;
	private String name; 
	private double amount; 
	
	@ManyToOne
	@JoinColumn(name = "mu_id")
	@JsonManagedReference
	private MeasuringUnit mu; 
	 
	@ManyToOne
    @JoinColumn(name="recipe_id")
	@JsonManagedReference
	private Recipe recipe;

	public Ingredient() {
		super();
	}

	public Ingredient(String name, double amount, MeasuringUnit mu) {
		super();
		this.name = name;
		this.amount = amount;
		this.mu = mu;
	}

	public Ingredient(String name, double amount, MeasuringUnit mu, Recipe recipe) {
		super();
		this.name = name;
		this.amount = amount;
		this.mu = mu;
		this.recipe = recipe;
	}

	public long getIngredientId() {
		return ingredient_id;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredient_id = ingredientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public MeasuringUnit getMu() {
		return mu;
	}

	public void setMu(MeasuringUnit mu) {
		this.mu = mu;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredient_id + ", name=" + name + ", amount=" + amount + ", mu=" + mu
				+ ", recipe=" + recipe + "]";
	}
	
}
