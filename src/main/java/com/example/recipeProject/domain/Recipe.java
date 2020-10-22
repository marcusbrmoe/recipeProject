package com.example.recipeProject.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long recipeId; 
	private String name;
	private int servings;
	private String desc;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonManagedReference
	private Category category;
	
	@JsonBackReference
	@OneToMany(mappedBy = "recipe", orphanRemoval = true)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "recipe", orphanRemoval = true)
	private List<CookingStep> steps = new ArrayList<CookingStep>();
	
	public Recipe() {
		super();
	}

	
	public Recipe(String name, int servings, String desc, Category category) {
		super();
		this.name = name;
		this.servings = servings;
		this.desc = desc;
		this.category = category;
	}


	public Recipe(String name, int servings, String desc, Category category, List<Ingredient> ingredients,
			List<CookingStep> steps) {
		super();
		this.name = name;
		this.servings = servings;
		this.desc = desc;
		this.category = category;
		this.ingredients = ingredients;
		this.steps = steps;
	}
	
	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long id) {
		this.recipeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }
 
    public void removeIngredient(Ingredient ingredient) {
    	ingredient.setRecipe(null);
        this.ingredients.remove(ingredient);
    }

	public List<CookingStep> getSteps() {
		return steps;
	}

	public void setSteps(List<CookingStep> steps) {
		this.steps = steps;
	}

	public void addStep(CookingStep step) {
		step.setRecipe(this);
		steps.add(step);
    }
 
    public void removeStep(CookingStep step) {
    	step.setRecipe(null);
        this.steps.remove(step);
    }	


	
}
