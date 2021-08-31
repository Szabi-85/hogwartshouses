package hogwartshouses.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotBlank(message = "This field must not be empty!")
    private BuildingEntity building;

    @DecimalMin("1")
    private Integer roomNumber;

    private Integer capacity;

    @JsonIgnore
    @DecimalMin("1")
    private Integer numberOfBeds;

    @JsonIgnore
    @OneToMany(mappedBy = "roomEntity")
    private Set<StudentEntity> studentEntities;

    @JsonIgnore
    private boolean hasEmptyBed;
}