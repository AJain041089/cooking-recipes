package com.recipemanager.cookingrecipe.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import com.recipemanager.cookingrecipe.model.Recipe;
import com.recipemanager.cookingrecipe.model.RecipeSearchCriteria;

/**
 * The Class RecipeCriteriaRepository.
 */
@Repository
public class RecipeCriteriaRepository {

	/** The entity manager. */
	private final EntityManager entityManager;

	/** The criteria builder. */
	private final CriteriaBuilder criteriaBuilder;

	/**
	 * Instantiates a new recipe criteria repository.
	 *
	 * @param entityManager the entity manager
	 */
	public RecipeCriteriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	/**
	 * Find all with filters.
	 *
	 * @param recipeSearchCriteria the recipe search criteria
	 * @return the list
	 */
	public List<Recipe> findAllWithFilters(RecipeSearchCriteria recipeSearchCriteria) {

		CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
		Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);
		Predicate predicate = getPredicate(recipeSearchCriteria, recipeRoot);
		criteriaQuery.where(predicate);
		TypedQuery<Recipe> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();

	}

	/**
	 * Gets the predicate.
	 *
	 * @param recipeSearchCriteria the recipe search criteria
	 * @param recipeRoot           the recipe root
	 * @return the predicate
	 */
	private Predicate getPredicate(RecipeSearchCriteria recipeSearchCriteria, Root<Recipe> recipeRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (Objects.nonNull(recipeSearchCriteria.getServingsize())) {
			predicates.add(criteriaBuilder.like(recipeRoot.get("servingSize"),
					"%" + recipeSearchCriteria.getServingsize() + "%"));
		}
		if (Objects.nonNull(recipeSearchCriteria.getIsVeg())) {
			predicates.add(criteriaBuilder.like(recipeRoot.get("isVeg"), "%" + recipeSearchCriteria.getIsVeg() + "%"));
		}
		if (Objects.nonNull(recipeSearchCriteria.getCookingins())) {
			predicates.add(criteriaBuilder.like(recipeRoot.get("cookingIns"),
					"%" + recipeSearchCriteria.getCookingins() + "%"));
		}
		if (Objects.nonNull(recipeSearchCriteria.getIngredients())) {
			predicates.add(criteriaBuilder.like(recipeRoot.get("ingredients"),
					"%" + recipeSearchCriteria.getIngredients() + "%"));
		}
		

		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
