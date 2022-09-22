package model.recipy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Recipe {
    String name;
    String description;
    List<String> ingredients;
    List<String> directions;
}
