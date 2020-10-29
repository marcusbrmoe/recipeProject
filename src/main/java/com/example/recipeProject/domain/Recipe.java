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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="recipe")
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long recipe_id; 
	private String name;
	private int servings;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonManagedReference
	private Category category;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
	private List<Ingredient> ingredients;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
	private List<CookingStep> steps;
	
	public Recipe() {
		super();
		this.ingredients = new ArrayList<Ingredient>();
		this.steps = new ArrayList<CookingStep>();
	}
	
	public Recipe(String name, int servings, String desc, Category category) {
		super();
		this.name = name;
		this.servings = servings;
		this.description = desc;
		this.category = category;
		this.ingredients = new ArrayList<Ingredient>();
		this.steps = new ArrayList<CookingStep>();
	}

	public Recipe(String name, int servings, String desc, Category category, List<Ingredient> ingredients,
			List<CookingStep> steps) {
		super();
		this.name = name;
		this.servings = servings;
		this.description = desc;
		this.category = category;
		this.ingredients = ingredients;
		this.steps = steps;
	}
	
	public long getRecipeId() {
		return recipe_id;
	}

	public void setRecipeId(long id) {
		this.recipe_id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
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

	@Override
	public String toString() {
		return "Recipe [recipe_id=" + recipe_id + ", name=" + name + ", servings=" + servings + ", description="
				+ description + ", category=" + category + ", ingredients=" + ingredients + ", steps=" + steps + "]";
	}	
	
}
