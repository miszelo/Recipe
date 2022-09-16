package recipes;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    public Recipe recipe;

    @GetMapping("/api/recipe")
    public Recipe getRecipe() {
        return recipe;
    }

    @PostMapping("/api/recipe")
    public void postRecipe(@RequestBody Recipe recipe) {
        this.recipe = recipe;
    }
}
