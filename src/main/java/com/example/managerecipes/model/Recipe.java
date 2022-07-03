package com.example.managerecipes.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import javax.sound.midi.Sequence;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Recipes")
public class Recipe {
    @Id
    private int id;
    private String recipeName;
    private String dishType;
    private int servingCount;
    private List<String> ingredients;
    private List<String> instruction;
}
