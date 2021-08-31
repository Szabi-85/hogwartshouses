package hogwartshouses.controller.restController;

import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest-api/students")
public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public void add(@RequestBody StudentEntity studentEntity) {
        studentEntity.setId(null);
        studentService.save(studentEntity);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable long id, @RequestBody StudentEntity studentEntity) {
        studentEntity.setId(id);
        studentService.save(studentEntity);
    }

    @GetMapping
    public List<StudentEntity> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentEntity findStudentById(@PathVariable long id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/{studentId}/room")
    public RoomEntity findRoomByStudentId(@PathVariable long studentId) {
        return studentService.findRoomByStudentId(studentId);
    }

    @PutMapping("/{studentId}/room/{roomId}/")
    public void connectStudentAndRoom(@PathVariable long studentId, @PathVariable long roomId) {
        studentService.connectStudentAndRoom(studentId, roomId);
    }
}