package hogwartshouses.controller;

import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public String add(@RequestBody StudentEntity studentEntity) {
        studentEntity.setId(null);
        studentService.save(studentEntity);

        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable long id, @RequestBody StudentEntity studentEntity) {
        studentEntity.setId(id);
        studentService.save(studentEntity);

        return "redirect:/students";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());

        return "students";
    }

    @GetMapping("/{id}")
    public String findStudentById(@PathVariable long id, Model model) {
        model.addAttribute("student", studentService.findStudentById(id));

        return "student";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id) {
        studentService.deleteStudent(id);

        return "redirect:/students";
    }

    @GetMapping("/{studentId}/room")
    public String findRoomByStudentId(@PathVariable long studentId, Model model) {
        model.addAttribute("room", studentService.findRoomByStudentId(studentId));

        return "room";
    }

    @PutMapping("/{studentId}/room/{roomId}/")
    public String connectStudentAndRoom(@PathVariable long studentId, @PathVariable long roomId) {
        studentService.connectStudentAndRoom(studentId, roomId);

        return "redirect:/students";
    }
}
