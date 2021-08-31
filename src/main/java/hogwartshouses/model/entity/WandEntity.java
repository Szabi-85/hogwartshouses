package hogwartshouses.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wand")
public class WandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String woodType;

    private String color;

    @OneToOne(mappedBy = "wandEntity")
    @JsonIgnore
    private TeacherEntity teacher;

    @ManyToMany
    @JoinTable(
            name = "WAND_SPELLS",
            joinColumns = @JoinColumn(name = "WAND_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPELL_ID"))
    private List<SpellEntity> spellEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WandEntity that = (WandEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(woodType, that.woodType) && Objects.equals(color, that.color) && Objects.equals(teacher, that.teacher) && Objects.equals(spellEntities, that.spellEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, woodType, color, teacher, spellEntities);
    }
}