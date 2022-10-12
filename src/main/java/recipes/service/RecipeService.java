package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.repository.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public ResponseEntity<Map<String, Long>> addRecipe(Recipe recipe,
                                                       UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        recipe.setUser(user);

        recipeRepository.save(recipe);

        return ResponseEntity.ok(Map.of("id",recipe.getId()));
    }

    public ResponseEntity<Recipe> getRecipe(long id) {
        return ResponseEntity.of(Optional.ofNullable(recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new)));
    }

    public ResponseEntity<List<Recipe>> findByCategoryIgnoreCaseOrderByDateDesc(String category) {
        return ResponseEntity.ok(recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category));
    }

    public ResponseEntity<List<Recipe>> findByNameIgnoreCaseContainsOrderByDateDesc(String name) {
        return ResponseEntity.ok(recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name));
    }

    public ResponseEntity<?> updateRecipe(Recipe recipe, long id,
                             UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        Recipe recipeToUpdate = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (!recipeToUpdate.getUser().getEmail().equals(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        recipeToUpdate.setName(recipe.getName());
        recipeToUpdate.setCategory(recipe.getCategory());
        recipeToUpdate.setDescription(recipe.getDescription());
        recipeToUpdate.setDate(LocalDateTime.now());
        recipeToUpdate.setIngredients(recipe.getIngredients());
        recipeToUpdate.setDirections(recipe.getDirections());
        recipeRepository.save(recipeToUpdate);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> deleteRecipe(long id,
                             UserDetails userDetails) {

        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        Recipe recipeToDelete = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (!recipeToDelete.getUser().getEmail().equals(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}