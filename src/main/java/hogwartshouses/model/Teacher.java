package hogwartshouses.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher {
    private Long id;
    private String name;
    private String subject;
    private boolean isWitch;
    private int age;
    private Wand wand;
}