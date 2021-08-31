package hogwartshouses.model.DTO;

import hogwartshouses.model.entity.types.Ingredient;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RecipeDTO {
    private Long id;
    private String name;
    private List<Ingredient> ingredients;
}