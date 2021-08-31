package hogwartshouses.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellDTO {
    private String name;
    private int power;
    private boolean isBanned;
}
