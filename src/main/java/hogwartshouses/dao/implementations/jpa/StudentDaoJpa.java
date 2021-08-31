package hogwartshouses.dao.implementations.jpa;

import hogwartshouses.dao.StudentDao;
import hogwartshouses.dao.implementations.repository.StudentDaoRepository;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoJpa implements StudentDao {
    @Autowired
    StudentDaoRepository studentDaoRepository;

    @Override
    public void save(StudentEntity studentEntity) {
        studentDaoRepository.save(studentEntity);
    }

    @Override
    public StudentEntity findStudentById(long id) {
        Optional<StudentEntity> studentOptional = studentDaoRepository.findById(id);
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("Student not found!");
        }
        return studentOptional.get();
    }

    @Override
    public void deleteStudent(long id) {
        studentDaoRepository.deleteById(id);
    }

    @Override
    public List<StudentEntity> findAll() {
        List<StudentEntity> students = new ArrayList<>();
        studentDaoRepository.findAll().forEach(students::add);
        return students;
    }

    @Override
    public RoomEntity findRoomByStudentId(long id) {
        StudentEntity studentEntity = findStudentById(id);
        return studentEntity.getRoomEntity();
    }

    @Override
    public void connectStudentAndRoom(StudentEntity studentEntity, RoomEntity roomEntity) {
        studentEntity.setRoomEntity(roomEntity);
        studentDaoRepository.save(studentEntity);
    }
}