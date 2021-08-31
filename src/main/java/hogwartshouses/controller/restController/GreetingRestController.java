package hogwartshouses.controller.restController;

import hogwartshouses.controller.GreetingController;
import hogwartshouses.service.RoomService;
import hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest-api")
public class GreetingRestController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Bitches and Wizards") String name) {
        return "menu list";
    }

    @GetMapping("/about")
    public List<String> about() {
        return List.of(
                "Csanádi Balázs",
                "Dr. Faragóné Kmecs Lilla",
                "Gábor Marcell",
                "Gede András",
                "Németh Zsolt",
                "Ormay Szabolcs",
                "Szabó György",
                "Szakál Zsófia",
                "Vincze-Palkó Andrea"
        );
    }

    @GetMapping("/init")
    public void initData(){
        GreetingController.init(roomService, studentService);
    }
}