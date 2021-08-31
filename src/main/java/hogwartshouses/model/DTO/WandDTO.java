package hogwartshouses.model.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class WandDTO {
    private String woodType;
    private String color;
    private TeacherDTO teacher;
    private List<SpellDTO> spells;
}