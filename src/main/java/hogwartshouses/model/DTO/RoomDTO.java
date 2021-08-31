package hogwartshouses.model.DTO;

import hogwartshouses.model.entity.BuildingEntity;
import hogwartshouses.model.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    @NotBlank(message = "This field must not be empty!")
    private BuildingEntity building;
    private Integer roomNumber;
    private Integer capacity;
    @DecimalMin("1")
    private Integer numberOfBeds;
    private Set<StudentEntity> studentEntities;
    private boolean hasEmptyBed;
}