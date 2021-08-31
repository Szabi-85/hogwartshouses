package hogwartshouses.controller;

import hogwartshouses.model.entity.TeacherEntity;
import hogwartshouses.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public String listAllTeacher(Model model) {
        model.addAttribute("teachers", teacherService.listAllTeacher());

        return "teachers";
    }

    @GetMapping("/{id}")
    public String getTeacher(@PathVariable Long id, Model model) {
        TeacherEntity teacherEntity = teacherService.getTeacher(id);
        if (teacherEntity == null) return "error";

        model.addAttribute("teacher", teacherEntity);
        return "teacher";
    }

    @PostMapping
    public String addTeacher(@RequestBody TeacherEntity teacher) {
        teacherService.addTeacher(teacher);

        return "redirect:/teachers";
    }

    @PutMapping("/{id}")
    public String updateTeacher(@PathVariable Long id, @RequestBody TeacherEntity teacher) {
        teacherService.updateTeacher(id, teacher);

        return "redirect:/teachers";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);

        return "redirect:/teachers";
    }

    @GetMapping("/wand/{wood_type}")
    public String listTeacherByWandWoodType(@PathVariable("wood_type") String woodType, Model model) {
        model.addAttribute("teachers", teacherService.listTeacherByWandWoodType(woodType));

        return "teachers";
    }
}