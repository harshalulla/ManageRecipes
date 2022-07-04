package com.example.managerecipes.controller;

import com.example.managerecipes.model.Recipe;
import com.example.managerecipes.service.RecipeService;
import org.hamcrest.core.Every;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Mock
    private RecipeService recipeService;

    TestRestTemplate restTemplate = new TestRestTemplate();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Order(1)
    @Test
    public void addRecipe_thenSuccess200() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>("{\n" +
                "  \"dishType\": \"Veg\",\n" +
                "  \"id\": 10,\n" +
                "  \"ingredients\": [\n" +
                "    \"Masala\"\n" +
                "  ],\n" +
                "  \"instruction\": [\n" +
                "    \"Boil\"\n" +
                "  ],\n" +
                "  \"recipeName\": \"Pav Masala\",\n" +
                "  \"servingCount\": 1\n" +
                "}", getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/recipe/addRecipe"), HttpMethod.POST, entity, String.class);
        Assert.assertEquals("Recipes Added Successfully", response.getBody());
    }


    @Order(2)
    @Test
    public void updateRecipeById_thenSuccess200() {
        HttpEntity<String> entity = new HttpEntity<String>("{\n" +
                "  \"dishType\": \"Veg\",\n" +
                "  \"id\": 10,\n" +
                "  \"ingredients\": [\n" +
                "    \"Masala Pav\"\n" +
                "  ],\n" +
                "  \"instruction\": [\n" +
                "    \"Boil\"\n" +
                "  ],\n" +
                "  \"recipeName\": \"Pav Masala\",\n" +
                "  \"servingCount\": 2\n" +
                "}", getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/recipe/updateRecipeById"), HttpMethod.PUT, entity, String.class);
        Assert.assertEquals("Recipes Updated Successfully", response.getBody());
    }

    @Order(3)
    @Test
    public void getRecipes_thenSuccess200() throws JSONException {
        HttpEntity<Integer> entity = new HttpEntity<Integer>(null, getHeaders());
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe = new Recipe();
        recipe.setId(10);
        recipe.setDishType("Veg");
        recipe.setServingCount(2);
        List<String> instructionList = new ArrayList<>();
        instructionList.add("Boil");
        recipe.setInstruction(instructionList);
        List<String> ingredientsList = new ArrayList<>();
        ingredientsList.add("Masala Pav");
        recipeList.add(recipe);
        String[] ingredients = null;
        String[] instruction = {"Boil"};
        String[] excludeIngredients = {"Maggie"};
        Mockito.when(recipeService.findRecipesByProperties("Veg",2,ingredients,instruction,excludeIngredients)).thenReturn(recipeList);
        //Mockito.when(recipeService.findRecipesByProperties(any(),any(),any(),any(),any())).thenReturn(any());
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/recipe/getRecipes?dishType=Veg&servingCount=2"), HttpMethod.GET, entity, String.class);
        Assert.assertEquals("[{\"id\":10,\"recipeName\":\"Pav Masala\",\"dishType\":\"Veg\",\"servingCount\":2,\"ingredients\":[\"Masala Pav\"],\"instruction\":[\"Boil\"]}]", response.getBody());

    }

    @Order(4)
    @Test
    public void deleteRecipe_thenSuccess200() {
        HttpEntity<Integer> entity = new HttpEntity<Integer>(null, getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/recipe/deleteRecipeById?id=1"), HttpMethod.DELETE, entity, String.class);
        Assert.assertEquals("Recipe Deleted Successfully", response.getBody());
    }



}
