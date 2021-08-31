package hogwartshouses.service;

import hogwartshouses.dao.RoomDao;
import hogwartshouses.dao.StudentDao;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentDao studentDao;
    private final RoomDao roomDao;

    public StudentService(StudentDao studentDao, RoomDao roomDao) {
        this.studentDao = studentDao;
        this.roomDao = roomDao;
    }

    public void save(StudentEntity studentEntity) {
        studentDao.save(studentEntity);
    }

    public List<StudentEntity> findAll() {
        return studentDao.findAll();
    }

    public StudentEntity findStudentById(long id) {
        return studentDao.findStudentById(id);
    }

    public RoomEntity findRoomByStudentId(long id) {
        return studentDao.findRoomByStudentId(id);
    }

    public void deleteStudent(long id) {
        studentDao.deleteStudent(id);
    }

    public void connectStudentAndRoom(long studentId, long roomId) {
        StudentEntity studentEntity = studentDao.findStudentById(studentId);
        RoomEntity roomEntity = roomDao.findRoomById(roomId);
        studentDao.connectStudentAndRoom(studentEntity, roomEntity);
    }
}