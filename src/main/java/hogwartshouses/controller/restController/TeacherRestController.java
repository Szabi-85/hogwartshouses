package hogwartshouses.controller.restController;

import hogwartshouses.model.entity.TeacherEntity;
import hogwartshouses.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest-api/teachers")
public class TeacherRestController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherRestController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherEntity> listAllTeacher() {
        return teacherService.listAllTeacher();
    }

    @GetMapping("/{id}")
    public TeacherEntity getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping
    public void addTeacher(@RequestBody TeacherEntity teacher) {
        teacher.setId(null);
        teacherService.addTeacher(teacher);
    }

    @PutMapping("/{id}")
    public void updateTeacher(@PathVariable Long id, @RequestBody TeacherEntity teacher) {
        teacherService.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @GetMapping("/wand/{wood_type}")
    public List<TeacherEntity> listTeacherByWandWoodType(@PathVariable("wood_type") String woodType) {
        return teacherService.listTeacherByWandWoodType(woodType);
    }
}