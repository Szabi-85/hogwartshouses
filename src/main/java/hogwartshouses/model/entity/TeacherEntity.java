package hogwartshouses.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String subject;

    private boolean isWitch;

    private int age;

    @OneToOne(optional = false)
    @JoinColumn(name = "wand_id", referencedColumnName = "id")
    private WandEntity wandEntity;
}