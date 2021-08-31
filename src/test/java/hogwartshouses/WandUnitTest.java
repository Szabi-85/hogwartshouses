package hogwartshouses;

import hogwartshouses.controller.WandController;
import hogwartshouses.model.entity.WandEntity;
import hogwartshouses.service.WandService;
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

@WebMvcTest(WandController.class)
public class WandUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WandService wandService;

    private static WandEntity wandOne;
    private static WandEntity wandTwo;

    @BeforeAll
    public static void init() {
        wandOne = new WandEntity();
        wandOne.setColor("brown");
        wandOne.setWoodType("ebony");

        wandTwo = new WandEntity();
        wandTwo.setColor("green");
        wandTwo.setWoodType("oak");
    }

    @Test
    public void findById_ReturnsTheWandWithTheRequestedId() throws Exception {
        Long firstId = 1L;
        Long secondId = 2L;

        when(wandService.findById(firstId)).thenReturn(wandOne);
        when(wandService.findById(secondId)).thenReturn(wandTwo);

        mockMvc.perform(get("/wands/{id}", firstId)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(wandOne.getColor()))
                .andExpect(jsonPath("$.woodType").value(wandOne.getWoodType()))
        ;

        mockMvc.perform(get("/wands/{id}", secondId)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value(wandTwo.getColor()))
                .andExpect(jsonPath("$.woodType").value(wandTwo.getWoodType()))
        ;

    }

    @Test
    public void findAll_ReturnsTheProperWandList() throws Exception {
        List<WandEntity> wands = List.of(wandOne, wandTwo);

        when(wandService.findAll()).thenReturn(wands);

        mockMvc.perform(get("/wands")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].color").value(wandOne.getColor()))
                .andExpect(jsonPath("$[0].woodType").value(wandOne.getWoodType()))
                .andExpect(jsonPath("$[1].color").value(wandTwo.getColor()))
                .andExpect(jsonPath("$[1].woodType").value(wandTwo.getWoodType()))
        ;
    }

    @Test
    public void add_worksAsExpected() throws Exception {
        doNothing().when(wandService).save(wandOne);

        mockMvc.perform(post("/wands")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(wandOne)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(wandService, times(1)).save(wandOne);
    }

    @Test
    public void update_worksAsExpected() throws Exception {
        Long id = 1L;

        doNothing().when(wandService).save(wandOne);

        mockMvc.perform(put("/wands/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(wandOne)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        wandOne.setId(id);

        verify(wandService, times(1)).save(wandOne);
    }

    @Test
    public void delete_worksAsExpected() throws Exception {
        Long id = 1L;

        doNothing().when(wandService).deleteById(id);

        mockMvc.perform(delete("/wands/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

        verify(wandService, times(1)).deleteById(id);
    }
}