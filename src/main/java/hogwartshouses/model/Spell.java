package hogwartshouses.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spell {
    private Long id;
    private String name;
    private int power;
    private boolean isBanned;

    public Spell(String name, int power, boolean isBanned) {
        this.name = name;
        this.power = power;
        this.isBanned = isBanned;
    }
}