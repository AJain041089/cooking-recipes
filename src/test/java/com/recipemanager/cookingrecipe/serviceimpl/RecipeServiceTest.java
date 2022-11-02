/**
 * 
 */
package com.recipemanager.cookingrecipe.serviceimpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipemanager.cookingrecipe.exception.ResourceNotFoundException;
import com.recipemanager.cookingrecipe.model.Recipe;
import com.recipemanager.cookingrecipe.model.RecipeSearchCriteria;
import com.recipemanager.cookingrecipe.repository.RecipeCriteriaRepository;
import com.recipemanager.cookingrecipe.repository.RecipeRepository;
import com.recipemanager.cookingrecipe.service.RecipeServiceImpl;

/**
 * The Class RecipeServiceTest.
 *
 * @author Akanksha
 */

@SpringBootTest
public class RecipeServiceTest {

	/** The recipe repository. */
	@Mock
	RecipeRepository recipeRepository;

	/** The criteria repository. */
	@Mock
	RecipeCriteriaRepository criteriaRepository;

	/** The recipe service. */
	@InjectMocks
	RecipeServiceImpl recipeService;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Save recipe test.
	 */
	@Test
	public void saveRecipeTest() {
		Recipe recipe = new Recipe("Recipe", "2", "veg", "Cook it well", "cook", "url");
		when(recipeRepository.save(recipe)).thenReturn(recipe);
		Recipe searchRecipe = recipeService.saveRecipe(recipe);
		assertEquals(recipe, searchRecipe);
	}

	/**
	 * All recipe test.
	 */
	@Test
	public void allRecipeTest() {
		RecipeSearchCriteria criteria = new RecipeSearchCriteria();
		List<Recipe> list = new ArrayList<>();
		list.add(new Recipe("test", "test", "test", "test", "test", "test"));
		list.add(new Recipe("test", "test", "test", "test", "test", "test"));
		//List<Recipe> list = List.of(new Recipe("test", "test", "test", "test", "test", "test"),
			//	new Recipe("test1", "test2", "test3", "test4", "test5", "test6"));
		when(criteriaRepository.findAllWithFilters(criteria)).thenReturn(list);
		List<Recipe> allRecipe = recipeService.allRecipe(criteria);
		assertEquals(list.size(), allRecipe.size());
	}

	/**
	 * Gets the by id test.
	 *
	 * @return the by id test
	 */
	@Test
	public void getByIdTest() {
		Recipe recipe = new Recipe("test", "test", "test", "test", "test", "test");
		when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
		Recipe recipe1 = recipeService.getById(1L);
		assertEquals(Optional.of(recipe).get(), recipe1);
	}

	/**
	 * Update recipe test.
	 */
	@Test
	public void updateRecipeTest() {

		Recipe recipe = new Recipe("test", "test", "test", "test", "test", "test");
		when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
		Recipe updatedRecipe = new Recipe("updated test", "test", "test", "test", "test", "test");
		when(recipeRepository.save(recipe)).thenReturn(updatedRecipe);
		Recipe serviceRecipe = recipeService.updateRecipe(1L, updatedRecipe);
		assertEquals(updatedRecipe, serviceRecipe);
	}

	/**
	 * Delete recipe test.
	 */
	@Test
	public void deleteRecipeTest() {
		Recipe recipe = new Recipe("test", "test", "test", "test", "test", "test");
		recipeRepository.delete(recipe);
		verify(recipeRepository, times(1)).delete(recipe);
	}

	@Test
	public void testSaveRecipe() {
		Recipe recipe = new Recipe("Recipe", "2", "veg", "Cook it well", "cook", "url");

		when(recipeRepository.save(recipe)).thenReturn(recipe);

		Recipe searchRecipe = recipeService.saveRecipe(recipe);
		assertEquals(recipe.getTitle(), searchRecipe.getTitle());
		assertEquals(recipe.getCookingIns(), searchRecipe.getCookingIns());
		assertEquals(recipe.getIsVeg(), searchRecipe.getIsVeg());
		assertEquals(recipe.getServingSize(), searchRecipe.getServingSize());
		assertEquals(recipe.getUrl(), searchRecipe.getUrl());

	}

	@Test
	public void testallRecipeFilter() {

		List<Recipe> list = new ArrayList<>();
		Recipe recipe1 = new Recipe("test", "3", "PUREVEG", "cook", "ingridents", "test");
		Recipe recipe2 = new Recipe("test1", "2", "PUREVEG", "cook", "ingridents", "test6");
		list.add(recipe1);
		list.add(recipe2);

		RecipeSearchCriteria recipesearch = new RecipeSearchCriteria();
		recipesearch.setCookingins("cook");
		recipesearch.setIsVeg("PUREVEG");
		recipesearch.setServingsize("3");
		recipesearch.setIngredients("ingridents");

		//when(criteriaRepository.findAllWithFilters(recipesearch)).thenReturn(list);

		List<Recipe> allRecipe = recipeService.allRecipe(recipesearch);
		assertEquals(0, allRecipe.size());

	}
	
	@Test
	public void testallRecipeFilterpositive() {

		List<Recipe> list = new ArrayList<>();
		Recipe recipe1 = new Recipe("test", "3", "PUREVEG", "cook", "ingridents", "test");
		Recipe recipe2 = new Recipe("test1", "2", "PUREVEG", "cook", "ingridents", "test6");
		list.add(recipe1);
		list.add(recipe2);

		RecipeSearchCriteria recipesearch = new RecipeSearchCriteria();
		recipesearch.setCookingins("cook");
		recipesearch.setIsVeg("PUREVEG");
		recipesearch.setServingsize("3");
		recipesearch.setIngredients("ingridents");

		when(criteriaRepository.findAllWithFilters(recipesearch)).thenReturn(list);

		List<Recipe> allRecipe = recipeService.allRecipe(recipesearch);
		assertEquals(list.size(), allRecipe.size());

	}
	@Test
	public void testgetByIdNegative() {
		Recipe recipe = new Recipe("test", "test", "test", "test", "test", "test");
		Optional<Recipe> orecipe = Optional.of(recipe);

		when(recipeRepository.findById(1L)).thenReturn(orecipe);
		assertThatThrownBy(() -> recipeService.getById(2L)).isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	public void testuRecipe() {
		Recipe recipe = new Recipe("test", "test", "test", "test", "test", "test");
		Optional<Recipe> orecipe = Optional.of(recipe);
		when(recipeRepository.findById(1L)).thenReturn(orecipe);
		Recipe updatedRecipe = new Recipe("updated test", "test", "test", "test", "test", "test");
		when(recipeRepository.save(recipe)).thenReturn(updatedRecipe);

		Recipe serviceRecipe = recipeService.updateRecipe(1L, updatedRecipe);

		assertEquals(updatedRecipe, serviceRecipe);
	}

	@Test
	public void testdRecipe() {
		Recipe recipe = new Recipe("test", "test", "test", "test", "test", "test");
		recipeRepository.delete(recipe);
		verify(recipeRepository, times(1)).delete(recipe);

	}
	
}
