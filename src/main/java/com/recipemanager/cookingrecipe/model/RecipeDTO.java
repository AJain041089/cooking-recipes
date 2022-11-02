package com.recipemanager.cookingrecipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeDTO {

	private Long id;
	private String title;
	private String servingSize;
	private String isVeg;
	private String cookingIns;
	private String ingredients;
	private String url;

}
