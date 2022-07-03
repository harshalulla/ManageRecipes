package com.example.managerecipes.controller;

import com.example.managerecipes.model.Recipe;
import com.example.managerecipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/addRecipe")
    public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return new ResponseEntity("Recipes Added Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateRecipeById")
    public ResponseEntity updateRecipeById(@RequestBody Recipe recipe) {
        recipeService.updateRecipe(recipe);
        return new ResponseEntity("Recipes Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteRecipeById")
    public ResponseEntity deleteRecipeById(@RequestParam int id) {
        recipeService.deleteRecipeById(id);
        return new ResponseEntity("Recipes Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/getRecipes")
    public ResponseEntity getRecipes(@RequestParam(name = "dishType", required = false) String dishType,
                                     @RequestParam(name = "servingCount", required = false) Integer servingCount,
                                     @RequestParam(name = "ingredients", required = false) String[] ingredients,
                                     @RequestParam(name = "instruction", required = false) String[] instruction,
                                     @RequestParam(name = "excludeIngredients", required = false) String[] excludeIngredients) {
        return new ResponseEntity(recipeService.findRecipesByProperties(dishType, servingCount, ingredients, instruction, excludeIngredients), HttpStatus.OK);
    }
}
