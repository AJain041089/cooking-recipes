package com.recipemanager.cookingrecipe.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipemanager.cookingrecipe.exception.ResourceNotFoundException;
import com.recipemanager.cookingrecipe.model.Recipe;
import com.recipemanager.cookingrecipe.model.RecipeSearchCriteria;
import com.recipemanager.cookingrecipe.repository.RecipeCriteriaRepository;
import com.recipemanager.cookingrecipe.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class RecipeServiceImpl.
 */
@Service
@Transactional
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	/** The Constant RECIPE. */
	private static final String RECIPE = "Recipe";

	/** The recipe repository. */
	@Autowired
	RecipeRepository recipeRepository;

	/** The recipe criteria repository. */
	@Autowired
	RecipeCriteriaRepository recipeCriteriaRepository;

	/**
	 * All recipe.
	 *
	 * @param recipeSearchCriteria the recipe search criteria
	 * @return the list
	 */
	@Override
	public List<Recipe> allRecipe(RecipeSearchCriteria recipeSearchCriteria) {
		log.info("Get list of Recipes with or without filter");
		return recipeCriteriaRepository.findAllWithFilters(recipeSearchCriteria);
	}

	/**
	 * Save recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	@Override
	public Recipe saveRecipe(Recipe recipe) {
		log.info("Save Recipes");
		return recipeRepository.save(recipe);
	}

	/**
	 * Gets the by id.
	 *
	 * @param recipeId the recipe id
	 * @return the by id
	 */
	@Override
	public Recipe getById(Long recipeId) {
		log.info("Get Recipes by its id");
		return recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException(RECIPE, "id", recipeId));
	}

	/**
	 * Update recipe.
	 *
	 * @param recipeId      the recipe id
	 * @param recipeDetails the recipe details
	 * @return the recipe
	 */
	@Override
	public Recipe updateRecipe(Long recipeId, Recipe recipeDetails) {
		log.info("Update Recipes by its id");
		Recipe recipe = recipeRepository.findById(recipeId)
				.orElseThrow(() -> new ResourceNotFoundException(RECIPE, "id", recipeId));
		recipe.setTitle(recipeDetails.getTitle());
		recipe.setServingSize(recipeDetails.getServingSize());
		recipe.setIsVeg(recipeDetails.getIsVeg());
		recipe.setCookingIns(recipeDetails.getCookingIns());
		recipe.setIngredients(recipeDetails.getIngredients());
		recipe.setUrl(recipeDetails.getUrl());
		return recipeRepository.save(recipe);
	}

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 */
	@Override
	public void deleteRecipe(Long recipeId) {
		try {
			log.info("Delete Recipes by its id");
			recipeRepository.deleteById(recipeId);
		} catch (Exception e) {
			log.error("error while deleting the recipe" + e.getMessage());
			throw new ResourceNotFoundException(RECIPE, "id", recipeId);
		}
	}

}
