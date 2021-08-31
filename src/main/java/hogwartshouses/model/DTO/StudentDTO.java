package hogwartshouses.model.DTO;

import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.types.HouseType;
import hogwartshouses.model.entity.types.PetType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDTO {
    private Long id;
    private String name;
    private HouseType houseType;
    private PetType petType;
    private RoomEntity roomEntity;
    private boolean hasPureBlood;
}