package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/recipe")
@Validated
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/new")
    public Map<String,Long> postRecipe(@Valid @RequestBody Recipe recipe,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        return recipeService.addRecipe(recipe, userDetails);
    }

    @GetMapping("/{id}")
    public Optional<Recipe> getRecipe(@PathVariable long id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping(value = "/search", params = "category")
    public List<Recipe> getRecipesByCategory(@RequestParam String category) {
        return recipeService.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    @GetMapping(value = "/search", params = "name")
    public List<Recipe> getRecipesByName(@RequestParam String name) {
        return recipeService.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@Valid @RequestBody Recipe recipe,
                             @PathVariable long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        recipeService.updateRecipe(recipe, id, userDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        recipeService.deleteRecipe(id, userDetails);
    }
}

