package com.example.managerecipes.service.impl;

import com.example.managerecipes.model.Recipe;
import com.example.managerecipes.repository.RecipeRepository;
import com.example.managerecipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Recipe updateRecipe(Recipe recipe){

        Recipe updatedRecipe = repository.findById(recipe.getId()).get();

        if(Objects.nonNull(recipe.getRecipeName()) && emptyOrNullOrStringOrBlank(recipe.getRecipeName())){
            updatedRecipe.setRecipeName(recipe.getRecipeName());
        }

        if(Objects.nonNull(recipe.getServingCount())){
            updatedRecipe.setServingCount(recipe.getServingCount());
        }

        if(Objects.nonNull(recipe.getDishType()) && emptyOrNullOrStringOrBlank(recipe.getDishType())){
            updatedRecipe.setDishType(recipe.getDishType());
        }

        if(Objects.nonNull(recipe.getIngredients()) && !recipe.getIngredients().isEmpty() && !recipe.getIngredients().equals("") && emptyOrNullOrStringOrBlankList(recipe.getIngredients())){
            updatedRecipe.setIngredients(recipe.getIngredients());
        }
        if(Objects.nonNull(recipe.getInstruction()) && !recipe.getInstruction().isEmpty() && emptyOrNullOrStringOrBlankList(recipe.getInstruction())){
            updatedRecipe.setInstruction(recipe.getInstruction());
        }

        return repository.save(updatedRecipe);


    }

    @Override
    public void deleteRecipeById(int id) {
        repository.deleteById(id);
    }

    private boolean emptyOrNullOrStringOrBlankList(List<String> list) {
        if(list!=null && !list.isEmpty() && !list.get(0).equals("") && !list.get(0).equals("string")){
            return true;
        }
        return false;
    }

    public Boolean emptyOrNullOrStringOrBlank(String s){
        if(!s.isEmpty() && !s.equals("") && !s.equals("null") && !s.equals("string") ){
            return true;
        }
        return false;
    }

    public List<Recipe> findRecipesByProperties(String dishType, Integer servingCount, String[] ingredients, String[] instruction, String[] excludeIngredients) {
        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();
        if (dishType != null && !dishType.isEmpty())
            criteria.add(Criteria.where("dishType").is(dishType));
        if (servingCount != null)
            criteria.add(Criteria.where("servingCount").is(servingCount));
        if (ingredients != null && ingredients.length>0)
            criteria.add(Criteria.where("ingredients").in(ingredients));
        if (excludeIngredients != null && excludeIngredients.length>0)
            criteria.add(Criteria.where("ingredients").not().in(excludeIngredients));
        if (instruction != null && instruction.length>0)
            criteria.add(Criteria.where("instruction").in(instruction));
        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        return mongoTemplate.find(query, Recipe.class);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        repository.save(recipe);
    }
}
