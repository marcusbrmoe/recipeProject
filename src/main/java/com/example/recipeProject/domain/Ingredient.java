package com.example.recipeProject.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name; 
	private double amount; 
	
	@OneToOne(fetch = FetchType.EAGER)
	private MeasuringUnit mu; 
	
	@ManyToOne
    @JoinColumn(name="recipeId")
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return "Ingredient [id=" + id + ", name=" + name + ", amount=" + amount + ", mu=" + mu + ", recipe=" + recipe
				+ "]";
	}
	
}
