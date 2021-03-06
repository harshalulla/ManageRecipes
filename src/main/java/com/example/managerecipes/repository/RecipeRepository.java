package com.example.managerecipes.repository;

import com.example.managerecipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

}
