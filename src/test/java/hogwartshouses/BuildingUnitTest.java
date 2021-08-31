package hogwartshouses;

import hogwartshouses.controller.BuildingController;
import hogwartshouses.model.entity.BuildingEntity;
import hogwartshouses.service.BuildingService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BuildingController.class)
public class BuildingUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BuildingService buildingService;

    private static BuildingEntity buildingOne;
    private static BuildingEntity buildingTwo;

    @BeforeAll
    public static void init() {
        buildingOne = new BuildingEntity();
        buildingOne.setNumberOfRooms(8);

        buildingTwo = new BuildingEntity();
        buildingTwo.setNumberOfRooms(12);
    }

    @Test
    public void findById_ReturnsTheBuildingWithTheRequestedId() throws Exception {
        Long firstId = 1L;
        Long secondId = 2L;

        when(buildingService.findById(firstId)).thenReturn(buildingOne);
        when(buildingService.findById(secondId)).thenReturn(buildingTwo);

        mockMvc.perform(get("/buildings/{id}", firstId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRooms").value(buildingOne.getNumberOfRooms()))
        ;

        mockMvc.perform(get("/buildings/{id}", secondId)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRooms").value(buildingTwo.getNumberOfRooms()))
        ;
    }

    @Test
    public void findAll_ReturnsTheProperBuildingList() throws Exception {
        List<BuildingEntity> buildings = List.of(buildingOne, buildingTwo);

        when(buildingService.findAll()).thenReturn(buildings);

        mockMvc.perform(get("/buildings")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numberOfRooms").value(buildingOne.getNumberOfRooms()))
                .andExpect(jsonPath("$[1].numberOfRooms").value(buildingTwo.getNumberOfRooms()))
        ;
    }

    @Test
    public void add_worksAsExpected() throws Exception {
        when(buildingService.save(buildingOne)).thenReturn(buildingOne);

        mockMvc.perform(post("/buildings")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(buildingOne)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRooms").value(buildingOne.getNumberOfRooms()))
        ;
    }

    @Test
    public void update_worksAsExpected() throws Exception {
        Long id = 1L;
        buildingOne.setId(id);

        when(buildingService.save(buildingOne)).thenReturn(buildingOne);

        mockMvc.perform(put("/buildings/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(buildingOne)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRooms").value(buildingOne.getNumberOfRooms()));
    }

    @Test
    public void delete_worksAsExpected() throws Exception {
        Long id = 1L;

        doNothing().when(buildingService).deleteById(id);

        mockMvc.perform(delete("/buildings/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(buildingService, times(1)).deleteById(id);
    }
}