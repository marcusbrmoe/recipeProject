package com.example.recipeProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class CookingStep {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int stepNum;
	private String desc;
	
	@ManyToOne
	@JsonManagedReference
	private Recipe recipe;

	public CookingStep() {
		super();
	}

	public CookingStep(int stepNum, String desc) {
		super();
		this.stepNum = stepNum;
		this.desc = desc;
	}

	public CookingStep(int stepNum, String desc, Recipe recipe) {
		super();
		this.stepNum = stepNum;
		this.desc = desc;
		this.recipe = recipe;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStepNum() {
		return stepNum;
	}

	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "CookingSteps [id=" + id + ", stepNum=" + stepNum + ", desc=" + desc + ", recipe=" + recipe + "]";
	}
	
}
