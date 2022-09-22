package recipes.repository;

import recipes.model.Recipe;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class RecipeRepository {

    public Map<Integer, Recipe> recipes = new HashMap<>();

    public int addRecipe(Recipe recipe) {
        int id = recipes.size() + 1;
        recipes.put(id, recipe);
        return id;
    }

    public Optional<Recipe> getRecipe(int id) {
        return Optional.ofNullable(recipes.get(id));
    }


}
