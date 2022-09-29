package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/recipe")
@Validated
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipe(@PathVariable long id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping("/new")
    public Map<String,Long> postRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
    }
}

