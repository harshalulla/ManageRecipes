
package com.example.managerecipes.controller;

import com.example.managerecipes.model.Recipe;
import com.example.managerecipes.service.RecipeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController = new RecipeController();

    @Mock
    private RecipeService recipeService;

    @Test
    void addRecipe_thenSuccess200() throws Exception {
        Recipe rs = new Recipe();
        rs.setRecipeName("Maggi");
        rs.setId(1);
        ResponseEntity responseEntity = recipeController.addRecipe(rs);
        Assert.assertEquals("Recipes Added Successfully", responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteRecipe_thenSuccess200() throws Exception {
        ResponseEntity responseEntity = recipeController.deleteRecipeById(1);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateRecipeById_thenSuccess200() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(10);
        recipe.setDishType("Veg");
        recipe.setServingCount(3);
        List<String> instructionList = new ArrayList<>();
        instructionList.add("Boil");
        recipe.setInstruction(instructionList);
        List<String> ingredientsList = new ArrayList<>();
        ingredientsList.add("Panner");
        ResponseEntity responseEntity = recipeController.updateRecipeById(recipe);
        Assert.assertEquals("Recipes Updated Successfully", responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getRecipe_thenSuccess200() {
        String[] ingredients = {"Rice"};
        String[] instruction = {"Boil"};
        String[] excludeIngredients = {"Maggie"};
        ResponseEntity responseEntity = recipeController.getRecipes("Veg", 3, ingredients, instruction, excludeIngredients);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
