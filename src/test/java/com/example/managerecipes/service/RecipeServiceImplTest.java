package com.example.managerecipes.service;

import com.example.managerecipes.model.Recipe;
import com.example.managerecipes.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeServiceImplTest {

    @Autowired
    private RecipeRepository repository;

    @Autowired
    private RecipeServiceImpl serviceImpl;

    @Test
    void addRecipe_thenSuccess() throws Exception {
        Recipe rs = new Recipe();
        rs.setRecipeName("Maggi");
        rs.setId(1);
        serviceImpl.addRecipe(rs);
        assert repository.existsById(1);
    }

    @Test
    void deleteRecipe_thenSuccess() throws Exception {
        Recipe rs = new Recipe();
        rs.setRecipeName("Maggi");
        rs.setId(1);
        serviceImpl.addRecipe(rs);
        serviceImpl.deleteRecipeById(1);
        assert !repository.existsById(1);
    }


    @Test
    void updateRecipe_thenSuccess() {
        Recipe rs = new Recipe();
        rs.setRecipeName("Panner");
        rs.setId(10);
        repository.save(rs);
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(10);
        updatedRecipe.setDishType("Veg");
        updatedRecipe.setServingCount(3);
        List<String> instructionList = new ArrayList<>();
        instructionList.add("Boil");
        updatedRecipe.setInstruction(instructionList);
        List<String> ingredientsList = new ArrayList<>();
        ingredientsList.add("Panner");
        updatedRecipe.setIngredients(ingredientsList);
        Recipe result = serviceImpl.updateRecipe(updatedRecipe);
        assertEquals(result.getDishType(),updatedRecipe.getDishType());

    }

    @Test
    void getRecipe_thenSuccess() {
        String[] ingredients = {"Rice"};
        String[] instruction = {"Boil"};
        String[] excludeIngredients = {"Maggie"};
        List<Recipe> rs = serviceImpl.findRecipesByProperties("Veg", 5, ingredients,instruction,excludeIngredients);
        assertEquals("Veg",rs.get(0).getDishType());
    }

}