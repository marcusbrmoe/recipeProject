package com.example.recipeProject.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="measuring_unit")
public class MeasuringUnit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "mu_id")
	private long mu_id;
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "mu")
	private List<Ingredient> ingredients;
	
	public MeasuringUnit() {
		super();
	}

	public MeasuringUnit(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return mu_id;
	}

	public void setId(long muId) {
		this.mu_id = muId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "MeasuringUnit [id=" + mu_id + ", name=" + name + "]";
	}
	
}
