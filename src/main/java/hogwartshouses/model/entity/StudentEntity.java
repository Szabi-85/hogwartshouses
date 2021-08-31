package hogwartshouses.model.entity;

import hogwartshouses.model.entity.types.HouseType;
import hogwartshouses.model.entity.types.PetType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
@EqualsAndHashCode
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    @ManyToOne
    @JsonIgnore
    private RoomEntity roomEntity;

    private boolean hasPureBlood;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "student_recipe",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeEntity> recipeEntities;
}