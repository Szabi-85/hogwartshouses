package hogwartshouses.model.entity;

import hogwartshouses.model.entity.types.Ingredient;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(targetClass = Ingredient.class, fetch = FetchType.EAGER)
    @Column(name = "ingredient", nullable = false)
    @CollectionTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"))
    @Enumerated(EnumType.STRING)
    private List<Ingredient> ingredients;
}