package hogwartshouses.data_sample;

import hogwartshouses.model.entity.RoomEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RoomCreator {
    public static List<RoomEntity> getRooms() {
        return List.of(RoomEntity.builder().roomNumber(1).capacity(5).build(),
                RoomEntity.builder().roomNumber(2).capacity(10).build());
    }
}