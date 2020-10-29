package com.example.recipeProject.domain;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="cooking_step")
public class CookingStep {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long cooking_step_id;
	private int step_num;
	private String description;
	
	@ManyToOne
    @JoinColumn(name="recipe_id")
	@JsonManagedReference
	private Recipe recipe;

	public CookingStep() {
		super();
	}
	
	public CookingStep(int stepNum, String desc) {
		super();
		this.step_num = stepNum;
		this.description = desc;
	}

	public CookingStep(int stepNum, String desc, Recipe recipe) {
		super();
		this.step_num = stepNum;
		this.description = desc;
		this.recipe = recipe;
	}

	public long getCookingStepId() {
		return cooking_step_id;
	}

	public void setCookingStepId(long cookingStepId) {
		this.cooking_step_id = cookingStepId;
	}

	public int getStepNum() {
		return step_num;
	}

	public void setStepNum(int stepNum) {
		this.step_num = stepNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	//Sorting by ascending order.
	public static Comparator<CookingStep> csStep = new Comparator<CookingStep>() {

		public int compare(CookingStep s1, CookingStep s2) {

		   int step1 = s1.getStepNum();
		   int step2 = s2.getStepNum();

		   /*For ascending order*/
		   return step1-step2;

		   /*For descending order*/
		   //rollno2-rollno1;
	   }};
}
