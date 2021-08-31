package hogwartshouses.controller;

import hogwartshouses.data_sample.RoomCreator;
import hogwartshouses.data_sample.StudentCreator;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.PetType;
import hogwartshouses.service.RoomService;
import hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class GreetingController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Bitches and Wizards") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/init")
    public String initData(){
        GreetingController.init(roomService, studentService);

        return "redirect:/";
    }

    public static void init(RoomService roomService, StudentService studentService) {
        List<RoomEntity> roomEntities = RoomCreator.getRooms();
        roomEntities.forEach(roomService::saveRoom);

        Set<StudentEntity> studentEntities = new HashSet<>(StudentCreator.getStudents());
        studentEntities.forEach(student -> {
            studentService.save(student);
            if (student.getPetType().equals(PetType.CAT) || student.getPetType().equals(PetType.OWL)){
                roomService.addStudentToRoom(roomEntities.get(0).getId(), student);
            } else {
                roomService.addStudentToRoom(roomEntities.get(1).getId(), student);
            }
        });
    }
}