package hogwartshouses.data_sample;

import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.HouseType;
import hogwartshouses.model.entity.types.PetType;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class StudentCreator {
    public static List<StudentEntity> getStudents(){
        return List.of(StudentEntity.builder().name("Hairmione").houseType(HouseType.GRYFFINDOR).petType(PetType.CAT).hasPureBlood(false).build(),
                        StudentEntity.builder().name("Mell Foly").houseType(HouseType.SLYTHERIN).petType(PetType.OWL).hasPureBlood(false).build(),
                        StudentEntity.builder().name("Herry Potter").houseType(HouseType.GRYFFINDOR).petType(PetType.OWL).hasPureBlood(true).build(),
                        StudentEntity.builder().name("Ron").houseType(HouseType.GRYFFINDOR).petType(PetType.RAT).hasPureBlood(true).build());
    }
}