package hogwartshouses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
public class Room{
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long buildingId;
    private Integer roomNumber;
    private Integer capacity;
    @JsonIgnore
    private Integer numberOfBeds;
    @JsonIgnore
    private Set<Student> students;
    @JsonIgnore
    private boolean hasEmptyBed;
}