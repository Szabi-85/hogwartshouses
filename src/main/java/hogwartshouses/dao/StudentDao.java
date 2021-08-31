package hogwartshouses.dao;

import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;

import java.util.List;

public interface StudentDao {
    void save(StudentEntity studentEntity);
    List<StudentEntity> findAll();
    StudentEntity findStudentById(long id);
    void deleteStudent(long id);
    RoomEntity findRoomByStudentId(long id);
    void connectStudentAndRoom(StudentEntity studentEntity, RoomEntity roomEntity);
}