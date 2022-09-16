package recipes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Recipe {
    String name;
    String description;
    String ingredients;
    String directions;
}
