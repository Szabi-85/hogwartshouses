package hogwartshouses;

import hogwartshouses.controller.TeacherController;
import hogwartshouses.model.entity.TeacherEntity;
import hogwartshouses.service.TeacherService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
public class TeacherEntityControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService teacherService;

    private static TeacherEntity teacherOne;
    private static TeacherEntity teacherTwo;
    private static TeacherEntity teacherThree;


    @BeforeAll
    public static void init() {
        teacherOne = new TeacherEntity();
        teacherOne.setName("John Doe");
        teacherTwo = new TeacherEntity();
        teacherTwo.setName("Jane Doe");
        teacherThree = new TeacherEntity();
        teacherThree.setName("Jack Doe");
    }

    @Test
    public void findById_ReturnsTheTeacherWithTheRequestedId() throws Exception {
        when(teacherService.getTeacher(1L)).thenReturn(teacherOne);
        when(teacherService.getTeacher(2L)).thenReturn(teacherTwo);
        when(teacherService.getTeacher(3L)).thenReturn(teacherThree);

        mockMvc.perform(get("/teachers/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        mockMvc.perform(get("/teachers/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));

        mockMvc.perform(get("/teachers/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jack Doe"));
    }

    @Test
    public void findAll_ReturnsTheProperTeacherList() throws Exception {
        List<TeacherEntity> teachers = List.of(teacherOne, teacherTwo, teacherThree);

        when(teacherService.listAllTeacher()).thenReturn(teachers);

        mockMvc.perform(get("/teachers")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"))
                .andExpect(jsonPath("$[2].name").value("Jack Doe"));
    }
}