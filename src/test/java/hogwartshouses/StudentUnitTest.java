package hogwartshouses;

import hogwartshouses.controller.StudentController;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.HouseType;
import hogwartshouses.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(StudentController.class)
public class StudentUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    private static StudentEntity studentOne;
    private static StudentEntity studentTwo;

    @BeforeAll
    public static void init() {
        studentOne = new StudentEntity();
        studentOne.setName("Ron Weasley");
        studentOne.setHouseType(HouseType.GRYFFINDOR);
        studentOne.setHasPureBlood(true);

        studentTwo = new StudentEntity();
        studentTwo.setName("Sanyi");
        studentTwo.setHouseType(HouseType.GRYFFINDOR);
        studentTwo.setHasPureBlood(false);
    }

    @Test
    public void findById_ReturnsTheSpellWithTheRequestedId() throws Exception {
        Long firstId = 1L;
        Long secondId = 2L;

        when(studentService.findStudentById(firstId)).thenReturn(studentOne);
        when(studentService.findStudentById(secondId)).thenReturn(studentTwo);

        mockMvc.perform(get("/students/{id}", firstId)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(studentOne.getName()))
                .andExpect(jsonPath("$.houseType").value(studentOne.getHouseType().name()))
                .andExpect(jsonPath("$.hasPureBlood").value(studentOne.isHasPureBlood()))
        ;

        mockMvc.perform(get("/students/{id}", secondId)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(studentTwo.getName()))
                .andExpect(jsonPath("$.houseType").value(studentTwo.getHouseType().name()))
                .andExpect(jsonPath("$.hasPureBlood").value(studentTwo.isHasPureBlood()))
        ;
    }

    @Test
    public void findAll_ReturnsTheProperSpellList() throws Exception {
        List<StudentEntity> students = List.of(studentOne, studentTwo);

        when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(get("/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(studentOne.getName()))
                .andExpect(jsonPath("$[0].houseType").value(studentOne.getHouseType().name()))
                .andExpect(jsonPath("$[0].hasPureBlood").value(studentOne.isHasPureBlood()))
                .andExpect(jsonPath("$[1].name").value(studentTwo.getName()))
                .andExpect(jsonPath("$[1].houseType").value(studentTwo.getHouseType().name()))
                .andExpect(jsonPath("$[1].hasPureBlood").value(studentTwo.isHasPureBlood()))
        ;
    }

    @Test
    public void add_worksAsExpected() throws Exception {
        doNothing().when(studentService).save(studentOne);

        mockMvc.perform(post("/students")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(studentOne)))
                .andExpect(status().isOk());

        verify(studentService, times(1)).save(studentOne);
    }

    @Test
    public void update_worksAsExpected() throws Exception {
        Long id = 1L;
        studentOne.setId(id);

        doNothing().when(studentService).save(studentOne);

        mockMvc.perform(put("/students/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(studentOne)))
                .andExpect(status().isOk());

        verify(studentService, times(1)).save(studentOne);
    }

    @Test
    public void delete_worksAsExpected() throws Exception {
        Long id = 1L;

        doNothing().when(studentService).deleteStudent(id);

        mockMvc.perform(delete("/students/{id}", id)).andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(id);
    }
}