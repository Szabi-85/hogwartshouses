package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.StudentDao;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class StudentDaoMem implements StudentDao {
    private static AtomicLong counter = new AtomicLong(0);
    Set<StudentEntity> studentEntities = new HashSet<>();

    @Override
    public void save(StudentEntity studentEntity) {
        studentEntity.setId(counter.incrementAndGet());
        studentEntities.add(studentEntity);
    }

    @Override
    public StudentEntity findStudentById(long id) {
        return studentEntities.stream()
                .filter(student -> student.getId().equals(id))
                .map(student -> student.toBuilder().build())
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteStudent(long id) {
        studentEntities.removeIf(building -> building.getId().equals(id));
    }

    @Override
    public RoomEntity findRoomByStudentId(long id) {
        StudentEntity studentEntity = findStudentById(id);
        if (studentEntity != null) return studentEntity.getRoomEntity();
        return null;
    }

    @Override
    public void connectStudentAndRoom(StudentEntity studentEntity, RoomEntity roomEntity) {
        if (roomEntity.getStudentEntities() == null) roomEntity.setStudentEntities(new HashSet<>());
        roomEntity.getStudentEntities().add(studentEntity);
        studentEntity.setRoomEntity(roomEntity);
    }

    @Override
    public List<StudentEntity> findAll() {
        return new ArrayList<>(studentEntities);
    }
}