package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.RoomDao;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.PetType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class RoomDaoMem implements RoomDao {
    private static AtomicLong counter = new AtomicLong(0);
    private final List<RoomEntity> roomEntities = new ArrayList<>();
    private ModelMapper modelMapper;

    @Override
    public List<RoomEntity> listRooms() {
        return new ArrayList<>(roomEntities);
    }

    @Override
    public void addRoom(RoomEntity roomEntity) {
        roomEntity.setId(counter.incrementAndGet());
        roomEntities.add(roomEntity);
    }

    @Override
    public RoomEntity findRoomById(long id) {
        return roomEntities.stream()
                .filter(room -> room.getId().equals(id))
                .map(room -> room.toBuilder().build())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteRoomById(long id) {
        roomEntities.removeIf(room -> room.getId().equals(id));
    }

    @Override
    public void updateRoomById(long id, RoomEntity newRoomEntity) {
        roomEntities.forEach(room -> {
            if (room.getId().equals(id)) {
                room.setRoomNumber(newRoomEntity.getRoomNumber());
                room.setCapacity(newRoomEntity.getCapacity());
            }
        });
    }

    @Override
    public Set<StudentEntity> getStudentsByRoomId(long id) {
        return roomEntities.stream()
                .filter(room -> room.getId() == id)
                .map(RoomEntity::getStudentEntities)
                .map(HashSet::new)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<RoomEntity> findAvailableRooms() {
        List<RoomEntity> rooms = listRooms().stream()
                .filter(RoomEntity::isHasEmptyBed)
                .map(room -> new RoomEntity(
                        room.getId(),
                        room.getBuilding(),
                        room.getRoomNumber(),
                        room.getCapacity(),
                        room.getNumberOfBeds(),
                        room.getStudentEntities(),
                        room.isHasEmptyBed()))
                .collect(Collectors.toList());
        return rooms;
    }

    @Override
    public List<RoomEntity> findRoomWithNoCatOrOwl() {
        List<RoomEntity> roomsForRatOwners = findAvailableRooms().stream()
                .filter(roomEntity -> getStudentsByRoomId(roomEntity.getId()).stream()
                        .map(student -> !student.getPetType().equals(PetType.CAT) && !student.getPetType().equals(PetType.OWL))
                        .filter(value -> !value)
                        .findFirst()
                        .orElse(Boolean.TRUE))
                .map(room -> new RoomEntity(
                        room.getId(),
                        room.getBuilding(),
                        room.getRoomNumber(),
                        room.getCapacity(),
                        room.getNumberOfBeds(),
                        room.getStudentEntities(),
                        room.isHasEmptyBed()))
                .collect(Collectors.toList());
        return roomsForRatOwners;
    }
}