package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.revature.models.UserRecipe;

@Component ("historyRecipeRepository")
public interface HistoryRecipeRepository extends JpaRepository<UserRecipe,Integer>{

	UserRecipe findByOwnerId(Integer id);

}
