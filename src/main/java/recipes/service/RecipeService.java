package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public Map<String, Long> addRecipe(Recipe recipe) {
        return Map.of("id", recipeRepository.save(recipe).getId());
    }

    public Optional<Recipe> getRecipe(long id) {
        return Optional.ofNullable(recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new));
    }

    public List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }

    public void updateRecipe(Recipe recipe, long id) {
        Recipe recipeToUpdate = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        recipeToUpdate.setName(recipe.getName());
        recipeToUpdate.setCategory(recipe.getCategory());
        recipeToUpdate.setDescription(recipe.getDescription());
        recipeToUpdate.setIngredients(recipe.getIngredients());
        recipeToUpdate.setDirections(recipe.getDirections());

        recipeRepository.save(recipeToUpdate);
    }

    public void deleteRecipe(long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException();
        }
        recipeRepository.deleteById(id);
    }

}
