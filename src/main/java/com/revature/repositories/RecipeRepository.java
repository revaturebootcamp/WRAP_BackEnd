package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Recipe;

public interface RecipeRepository extends JpaRepository <Recipe,Integer>{

	List<Recipe> findAllByOwnerId(Integer userId);

	List<Recipe> findAllByOwnerIdAndIsCurrent(Integer userId, boolean b);

	List<Recipe> findAllByOwnerIdAndIsFavorite(Integer userId, boolean b);

	List<Recipe> findAllByOwnerIdAndIsHistory(Integer userId, boolean b);

}
