package com.example.recipeProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.recipeProject.domain.Category;
import com.example.recipeProject.domain.CategoryRepository;
import com.example.recipeProject.domain.CookingStep;
import com.example.recipeProject.domain.CookingStepRepository;
import com.example.recipeProject.domain.Ingredient;
import com.example.recipeProject.domain.IngredientRepository;
import com.example.recipeProject.domain.MeasuringUnitRepository;
import com.example.recipeProject.domain.Recipe;
import com.example.recipeProject.domain.RecipeRepository;

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeRepository repository;
	@Autowired
	private CategoryRepository crepository;
	@Autowired
	private MeasuringUnitRepository murepository;
	@Autowired
	private IngredientRepository irepository;
	@Autowired
	private CookingStepRepository csrepository;
	
	@RequestMapping(value={"/recipelist", "/"}, method=RequestMethod.GET)
	public String recipeList(Model model) {
		model.addAttribute("recipes", repository.findAll());
		model.addAttribute("categories", crepository.findAll());
		return "recipelist";
	}
	
	@RequestMapping(value="/recipelist/{category}", method=RequestMethod.GET)
	public String recipeByCatList(@PathVariable("category") Category category, Model model) {
		model.addAttribute("recipes", repository.findByCategory(category));
		model.addAttribute("categories", crepository.findAll());
		return "redirect:../recipelist";
	}
	
	@RequestMapping(value="/recipe/{id}", method=RequestMethod.GET)
	public String showRecipe(@PathVariable("id") long id, Model model) {
		model.addAttribute("recipe", repository.findById(id).get());
		return "recipe";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addRecipe(Model model) {
		Recipe one = new Recipe();
		
		for (int i = 0; i < 10; i++) {
			one.addIngredient(new Ingredient());
		}
		
		for (int i = 0; i < 10; i++) {
			one.addStep(new CookingStep());
		}
		model.addAttribute("recipe", one);
		model.addAttribute("categories", crepository.findAll());
		model.addAttribute("measuringunits", murepository.findAll());
		return "addrecipe";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveRecipe(Recipe recipe) {
		
		repository.save(recipe);
		
		//Adding recipe reference to Ingredients and deleting redundant objects.
		for (Ingredient ingredient : recipe.getIngredients()) {
			ingredient.setRecipe(recipe);
			if (ingredient.getName().isEmpty() || ingredient.getName() == null) {
				irepository.delete(ingredient);
			} else {
				irepository.save(ingredient);
			}
			
		}
		
		//Adding recipe reference and "stepNum" to CookingSteps, and deleting redundant objects.
		for (int i = 0 ; i < recipe.getSteps().size(); i++) {
			CookingStep x = recipe.getSteps().get(i);
			if (x.getDesc().isEmpty() || x.getDesc() == null) {
				csrepository.delete(x);
			} else if (x.getRecipe() == null) {
				x.setRecipe(recipe);
				x.setStepNum(i + 1);
				csrepository.save(x);
			} else {
				csrepository.save(x);
			}
		}
		
		return "redirect:recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET) 
	public String deleteRecipe(@PathVariable("id") long id, Model model) {
		repository.deleteById(id);
		return "redirect:../recipelist";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String editRecipe(@PathVariable("id") long id, Model model) {
		Recipe one = repository.findById(id).get();
		
		for (int i = 10; i > one.getIngredients().size();) {
			one.addIngredient(new Ingredient());
		}
		
		for (int i = 10; i > one.getSteps().size();) {
			one.addStep(new CookingStep());
		}
		
		model.addAttribute("recipe", one);
		model.addAttribute("categories", crepository.findAll());
		model.addAttribute("measuringunits", murepository.findAll());
		return "editrecipe";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
}
