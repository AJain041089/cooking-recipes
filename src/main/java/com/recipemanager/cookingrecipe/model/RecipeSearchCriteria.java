package com.recipemanager.cookingrecipe.model;

/**
 * The Class RecipeSearchCriteria.
 */
public class RecipeSearchCriteria {

	/** The servingSize. */
	private String servingSize; // count of people

	/** The is veg. */
	private String isVeg; // veg or nonveg

	/** The cookingIns. */
	private String cookingIns; // cooking instruction

	/** The ingredients. */
	private String ingredients; // list of ingredients

	

	/**
	 * Gets the servingSize.
	 *
	 * @return the servingSize
	 */
	public String getServingsize() {
		return servingSize;
	}

	/**
	 * Sets the servingSize.
	 *
	 * @param servingSize the new servingSize
	 */
	public void setServingsize(String servingsize) {
		this.servingSize = servingsize;
	}

	/**
	 * Gets the checks if is veg.
	 *
	 * @return the checks if is veg
	 */
	public String getIsVeg() {
		return isVeg;
	}

	/**
	 * Sets the checks if is veg.
	 *
	 * @param isVeg the new checks if is veg
	 */
	public void setIsVeg(String isVeg) {
		this.isVeg = isVeg;
	}

	/**
	 * Gets the cookingIns.
	 *
	 * @return the cookingIns
	 */
	public String getCookingins() {
		return cookingIns;
	}

	/**
	 * Sets the cookingIns.
	 *
	 * @param cookingIns the new cookingIns
	 */
	public void setCookingins(String cookingins) {
		this.cookingIns = cookingins;
	}

	/**
	 * Gets the ingredients.
	 *
	 * @return the ingredients
	 */
	public String getIngredients() {
		return ingredients;
	}

	/**
	 * Sets the ingredients.
	 *
	 * @param ingredients the new ingredients
	 */
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

}
