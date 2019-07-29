package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Recipe;

public interface RecipeRepository extends JpaRepository <Recipe,Integer>{

	List<Recipe> findAllByOwnerId(Integer userId);

}
