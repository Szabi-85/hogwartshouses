package hogwartshouses;

import hogwartshouses.controller.SpellController;
import hogwartshouses.model.entity.SpellEntity;
import hogwartshouses.service.SpellService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpellController.class)
public class SpellUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SpellService spellService;

    private static SpellEntity spellOne;
    private static SpellEntity spellTwo;

    @BeforeAll
    public static void init() {
        spellOne = new SpellEntity();
        spellOne.setName("Spell One");
        spellOne.setPower(3);
        spellOne.setBanned(true);

        spellTwo = new SpellEntity();
        spellTwo.setName("Spell Two");
        spellTwo.setPower(5);
        spellTwo.setBanned(false);
    }

    @Test
    public void findById_ReturnsTheSpellWithTheRequestedId() throws Exception {
        Long firstId = 1L;
        Long secondId = 2L;

        when(spellService.findSpellById(firstId)).thenReturn(spellOne);
        when(spellService.findSpellById(secondId)).thenReturn(spellTwo);

        mockMvc.perform(get("/spells/{id}", firstId)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(spellOne.getName()))
                .andExpect(jsonPath("$.power").value(spellOne.getPower()))
                .andExpect(jsonPath("$.banned").value(spellOne.isBanned()))
        ;

        mockMvc.perform(get("/spells/{id}", secondId)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(spellTwo.getName()))
                .andExpect(jsonPath("$.power").value(spellTwo.getPower()))
                .andExpect(jsonPath("$.banned").value(spellTwo.isBanned()))
        ;
    }

    @Test
    public void findAll_ReturnsTheProperSpellList() throws Exception {
        List<SpellEntity> spells = List.of(spellOne, spellTwo);

        when(spellService.findAll()).thenReturn(spells);

        mockMvc.perform(get("/spells"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(spellOne.getName()))
                .andExpect(jsonPath("$[0].power").value(spellOne.getPower()))
                .andExpect(jsonPath("$[0].banned").value(spellOne.isBanned()))
                .andExpect(jsonPath("$[1].name").value(spellTwo.getName()))
                .andExpect(jsonPath("$[1].power").value(spellTwo.getPower()))
                .andExpect(jsonPath("$[1].banned").value(spellTwo.isBanned()))
        ;
    }

    @Test
    public void add_worksAsExpected() throws Exception {
        doNothing().when(spellService).save(spellOne);

        mockMvc.perform(post("/spells")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(spellOne)))
                .andExpect(status().isOk());

        verify(spellService, times(1)).save(spellOne);
    }

    @Test
    public void update_worksAsExpected() throws Exception {
        Long id = 1L;
        spellOne.setId(id);

        doNothing().when(spellService).save(spellOne);

        mockMvc.perform(put("/spells/{id}", id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(spellOne)))
                .andExpect(status().isOk());

        verify(spellService, times(1)).save(spellOne);
    }

    @Test
    public void delete_worksAsExpected() throws Exception {
        Long id = 1L;

        doNothing().when(spellService).delete(id);

        mockMvc.perform(delete("/spells/{id}", id)).andExpect(status().isOk());

        verify(spellService, times(1)).delete(id);
    }
}
