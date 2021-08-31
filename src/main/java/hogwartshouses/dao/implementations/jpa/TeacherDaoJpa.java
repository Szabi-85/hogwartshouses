package hogwartshouses.dao.implementations.jpa;

import hogwartshouses.dao.TeacherDao;
import hogwartshouses.dao.implementations.repository.TeacherDaoRepository;
import hogwartshouses.model.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherDaoJpa implements TeacherDao {
    @Autowired
    private TeacherDaoRepository teacherDaoRepository;

    @Override
    public List<TeacherEntity> listAllTeacher() {
        List<TeacherEntity> teachers = new ArrayList<>();
        teacherDaoRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    @Override
    public TeacherEntity getTeacher(Long id) {
        Optional<TeacherEntity> teacher = teacherDaoRepository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get();
        }
        throw new RuntimeException("Teacher not found!");
    }

    @Override
    public void addTeacher(TeacherEntity teacher) {
        teacher.setId(null);
        teacherDaoRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Long id, TeacherEntity teacher) {
        teacher.setId(id);
        teacherDaoRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherDaoRepository.deleteById(id);
    }

    @Override
    public List<TeacherEntity> listTeacherByWandWoodType(String woodType) {
        return teacherDaoRepository.listTeacherByWandWoodType(woodType);
    }
}