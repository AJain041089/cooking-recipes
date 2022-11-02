package com.recipemanager.cookingrecipe.service;

import java.util.List;

import com.recipemanager.cookingrecipe.model.Recipe;
import com.recipemanager.cookingrecipe.model.RecipeSearchCriteria;

public interface RecipeService {

	/**
	 * All recipe.
	 *
	 * @param recipeSearchCriteria the recipe search criteria
	 * @return the list
	 */
	List<Recipe> allRecipe(RecipeSearchCriteria recipeSearchCriteria);

	/**
	 * Save recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	Recipe saveRecipe(Recipe recipe);

	/**
	 * Gets the by id.
	 *
	 * @param recipeId the recipe id
	 * @return the by id
	 */
	Recipe getById(Long recipeId);

	/**
	 * Update recipe.
	 *
	 * @param recipeId      the recipe id
	 * @param recipeDetails the recipe details
	 * @return the recipe
	 */
	Recipe updateRecipe(Long recipeId, Recipe recipeDetails);

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 */
	void deleteRecipe(Long recipeId);

}