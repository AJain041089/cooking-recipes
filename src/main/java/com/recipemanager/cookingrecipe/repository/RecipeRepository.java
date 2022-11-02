package com.recipemanager.cookingrecipe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipemanager.cookingrecipe.model.Recipe;


/**
 * The Interface RecipeRepository.
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
