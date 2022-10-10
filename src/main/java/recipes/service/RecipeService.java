package recipes.service;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.exceptions.RecipeNotFoundException;
import recipes.model.Recipe;
import recipes.model.User;
import recipes.repository.RecipeRepository;
import recipes.security.auth.ApplicationUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    ApplicationUserService applicationUserService;

    public Map<String, Long> addRecipe(Recipe recipe,
                                       UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        recipe.setUser(user);

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

    public void updateRecipe(Recipe recipe, long id,
                             UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        Recipe recipeToUpdate = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (!recipeToUpdate.getUser().getEmail().equals(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeToUpdate.setName(recipe.getName());
        recipeToUpdate.setCategory(recipe.getCategory());
        recipeToUpdate.setDescription(recipe.getDescription());
        recipeToUpdate.setDate(LocalDateTime.now());
        recipeToUpdate.setIngredients(recipe.getIngredients());
        recipeToUpdate.setDirections(recipe.getDirections());

        recipeRepository.save(recipeToUpdate);
    }

    public void deleteRecipe(long id,
                             UserDetails userDetails) {

        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        Recipe recipeToDelete = recipeRepository.findById(id)
                .orElseThrow(RecipeNotFoundException::new);

        if (!recipeToDelete.getUser().getEmail().equals(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        recipeRepository.deleteById(id);
    }


}
