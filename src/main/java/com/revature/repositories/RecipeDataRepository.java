package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.RecipeData;

public interface RecipeDataRepository extends JpaRepository<RecipeData,Integer>{

	RecipeData findByOwnerId(Integer id);

}
