package com.recipemanager.cookingrecipe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * The Class Recipe.
 */
@Entity
@Table(name = "recipe")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
@Data

// date and time,dd-mm-yyyy hh:mm , veg or non veg, serving size, ingredients,
// cooking instruction
public class Recipe implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The title. */
	@NotBlank
	private String title; // name of recipe

	/** The serving size. */
	@NotBlank
	private String servingSize; // count of people

	/** The is veg. */
	@NotBlank
	private String isVeg; // veg or nonveg

	/** The cooking ins. */
	@NotBlank
	private String cookingIns; // cooking instruction

	/** The ingredients. */
	@NotBlank
	private String ingredients; // list of ingredients

	/** The url. */
	@NotBlank
	private String url; // url of image

	/** The created at. */
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@CreatedDate
	private Date createdAt;

	/** The updated at. */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@LastModifiedDate
	private Date updatedAt;

	/**
	 * Instantiates a new recipe.
	 */
	public Recipe() {
		super();
	}

	/**
	 * Instantiates a new recipe.
	 *
	 * @param title       the title
	 * @param servingSize the serving size
	 * @param isVeg       the is veg
	 * @param cookingIns  the cooking ins
	 * @param ingredients the ingredients
	 * @param url         the url
	 */
	public Recipe(@NotBlank String title, @NotBlank String servingSize, @NotBlank String isVeg,
			@NotBlank String cookingIns, @NotBlank String ingredients, @NotBlank String url) {
		super();
		this.title = title;
		this.servingSize = servingSize;
		this.isVeg = isVeg;
		this.cookingIns = cookingIns;
		this.ingredients = ingredients;
		this.url = url;
	}
}
