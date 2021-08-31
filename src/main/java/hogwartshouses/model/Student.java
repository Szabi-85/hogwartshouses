package hogwartshouses.model;

import hogwartshouses.model.entity.types.HouseType;
import hogwartshouses.model.entity.types.PetType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class Student {
    private Long id;
    private String name;
    private HouseType houseType;
    private PetType petType;
    private Room room;
    private boolean hasPureBlood;
}