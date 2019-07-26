package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.revature.models.UserRecipe;

@Component ("currentRecipeRepository")
public interface CurrentRecipeRepository extends JpaRepository<UserRecipe,Integer> {

	UserRecipe findByOwnerId(Integer id);


}
