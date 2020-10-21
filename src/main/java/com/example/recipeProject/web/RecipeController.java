package com.example.recipeProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.recipeProject.domain.CategoryRepository;
import com.example.recipeProject.domain.RecipeRepository;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value={"/recipelist", "/"}, method=RequestMethod.GET)
	public String recipeList(Model model) {
		model.addAttribute("recipes", repository.findAll());
		return "recipelist";
	}
	
	@RequestMapping(value="/recipe/{id}", method=RequestMethod.GET)
	public String showRecipe(@PathVariable("id") long id, Model model) {
		model.addAttribute("recipe", repository.findById(id).get());
		return "recipe";
	}
}
