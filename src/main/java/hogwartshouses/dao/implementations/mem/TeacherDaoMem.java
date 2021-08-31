package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.TeacherDao;
import hogwartshouses.model.entity.TeacherEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TeacherDaoMem implements TeacherDao {
    private final List<TeacherEntity> teachers = new ArrayList<>();
    private final AtomicLong atomicLong = new AtomicLong();

    @Override
    public List<TeacherEntity> listAllTeacher() {
        return teachers;
    }

    @Override
    public TeacherEntity getTeacher(Long id) {
        return teachers.stream()
                .filter(currentTeacher -> currentTeacher.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Teacher not found!"));
    }

    @Override
    public void addTeacher(TeacherEntity teacher) {
        if (teacher == null || teacher.getWandEntity() == null) return;
        teacher.setId(atomicLong.incrementAndGet());
        teacher.getWandEntity().setTeacher(teacher);
        teachers.add(teacher);
    }

    @Override
    public void updateTeacher(Long id, TeacherEntity teacher) {
        teacher.setId(id);
        teacher.getWandEntity().setTeacher(teacher);
        for (TeacherEntity currentTeacher : teachers) {
            if (currentTeacher.getId().equals(id)) {
                teachers.remove(currentTeacher);
                teachers.add(teacher);
                return;
            }
        }
    }

    @Override
    public void deleteTeacher(Long id) {
        teachers.stream()
                .filter(currentTeacher -> currentTeacher.getId().equals(id))
                .forEach(currentTeacher -> currentTeacher.getWandEntity().setTeacher(null));
        teachers.removeIf(currentTeacher -> currentTeacher.getId().equals(id));
    }

    @Override
    public List<TeacherEntity> listTeacherByWandWoodType(String woodType) {
        return teachers.stream()
                .filter(currentTeacher -> currentTeacher.getWandEntity().getWoodType().equals(woodType))
                .collect(Collectors.toList());
    }
}