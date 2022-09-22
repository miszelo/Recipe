package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.Recipe;
import recipes.service.RecipeService;

import java.util.Map;


@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {this.recipeService = recipeService;}


    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping("/new")
    public Map<String, Integer> postRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }
}
