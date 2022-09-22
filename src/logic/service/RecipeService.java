package logic.service;

import model.recipy.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import repository.RecipeRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        Optional<Recipe> recipe = recipeRepository.getRecipe(id);
        return ResponseEntity.of(recipe);

    }

    public Map<String, Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeRepository.addRecipe(recipe);
        return Map.of("id", id);
    }
}
