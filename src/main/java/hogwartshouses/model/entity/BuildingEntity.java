package hogwartshouses.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfRooms;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "building")
    private List<RoomEntity> roomEntityList;

    public BuildingEntity(BuildingEntity otherBuildingEntity){
        this.id = otherBuildingEntity.id;
        this.numberOfRooms = otherBuildingEntity.numberOfRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingEntity that = (BuildingEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(numberOfRooms, that.numberOfRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfRooms);
    }
}