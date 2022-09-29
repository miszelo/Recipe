package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public Optional<Recipe> getRecipe(long id) {
        return Optional.ofNullable(recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new));
    }

    public Map<String, Long> addRecipe(Recipe recipe) {
        return Map.of("id", recipeRepository.save(recipe).getId());
    }

    public void deleteRecipe(long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException();
        }
        recipeRepository.deleteById(id);
    }
}
