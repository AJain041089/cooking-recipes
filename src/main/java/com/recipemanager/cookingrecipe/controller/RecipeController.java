package com.recipemanager.cookingrecipe.controller;

import java.util.List;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.recipemanager.cookingrecipe.model.*;
import com.recipemanager.cookingrecipe.service.RecipeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class RecipeController.
 */
@RestController
public class RecipeController {

	/** The recipe service. */
	@Autowired
	RecipeService recipeService;

	/** The mapper. */
	@Autowired
	ModelMapper mapper;

	/**
	 * Gets the all recipe.
	 *
	 * @param recipeDTO the recipe DTO
	 * @return the all recipe
	 */
	@ApiOperation(value = "Retrive all the recipes from database",response=List.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully retrieved list"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@GetMapping("/api/recipes")
	public ResponseEntity<List<Recipe>> getAllRecipe(RecipeDTO recipeDTO) {
		return new ResponseEntity<>(recipeService.allRecipe(mapper.map(recipeDTO, RecipeSearchCriteria.class)),
				HttpStatus.OK);
	}

	/**
	 * Gets the recipe by id.
	 *
	 * @param recipeId the recipe id
	 * @return the recipe by id
	 */
	@ApiOperation(value="Retrive recipe specific to id from database", response=List.class)
	@ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successfully retrieved data"),
    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
	@GetMapping("/api/recipes/{id}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable(value = "id") Long recipeId) {
		return new ResponseEntity<>(recipeService.getById(recipeId), HttpStatus.OK);
	}

	/**
	 * Creates the recipe.
	 *
	 * @param recipeDTO the recipe DTO
	 * @return the response entity
	 */
	@ApiOperation(value="Add new recipe to database",response=List.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully added"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@ApiParam(value = "RecipeBean object from which recipe object will be added", required = true)
	@PostMapping(value = "/api/recipes", consumes = "application/json")
	public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDTO recipeDTO) {
		return new ResponseEntity<>(recipeService.saveRecipe(mapper.map(recipeDTO, Recipe.class)), HttpStatus.OK);
	}

	/**
	 * Update recipe.
	 *
	 * @param recipeId  the recipe id
	 * @param recipeDTO the recipe DTO
	 * @return the response entity
	 */
	@ApiOperation(value="Update recipe to database based on id",response=List.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully updated"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@ApiParam(value = "Recipe id from which recipe object will retrieve and recipeBean from which object will updated", required = true)
	@PutMapping("/api/recipes/{id}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable(value = "id") Long recipeId,
			@Valid @RequestBody RecipeDTO recipeDTO) {
		return new ResponseEntity<>(recipeService.updateRecipe(recipeId, mapper.map(recipeDTO, Recipe.class)),
				HttpStatus.OK);
	}

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 * @return the response entity
	 */
	@ApiOperation(value="Delete recipe from database", response=List.class)
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Successfully deleted"),
		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
	@ApiParam(value = "Recipe id from which recipe object will delete", required = true)
	@DeleteMapping("/api/recipes/{id}")
	public ResponseEntity<String> deleteRecipe(@PathVariable(value = "id") Long recipeId) {
		recipeService.deleteRecipe(recipeId);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
