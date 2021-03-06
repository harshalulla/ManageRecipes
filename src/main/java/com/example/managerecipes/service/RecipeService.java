package com.example.managerecipes.service;

import com.example.managerecipes.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipeService {

    public void addRecipe(Recipe recipe) throws Exception;

    public Recipe updateRecipe(Recipe recipe);

    public String deleteRecipeById(int id);

    public List<Recipe> findRecipesByProperties(String dishType, Integer servingCount, String[] ingredients, String[] instruction, String[] excludeIngredients);
}
