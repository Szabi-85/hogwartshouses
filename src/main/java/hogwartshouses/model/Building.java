package hogwartshouses.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    private Long id;
    private Integer numberOfRooms;

    public Building(Building otherBuilding){
        this.id = otherBuilding.id;
        this.numberOfRooms = otherBuilding.numberOfRooms;
    }
}