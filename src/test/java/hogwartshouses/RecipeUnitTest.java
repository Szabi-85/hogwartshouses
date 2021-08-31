package hogwartshouses;

import hogwartshouses.controller.RecipeController;
import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.Ingredient;
import hogwartshouses.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(RecipeController.class)
public class RecipeUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecipeService recipeService;
    private static StudentEntity student1;
    private static StudentEntity student2;
    private static RecipeEntity recipeOne;
    private static RecipeEntity recipeTwo;

    @BeforeAll
    public static void init() {
        List<Ingredient> set1 = new ArrayList<>();
        set1.add(Ingredient.BAT_WING);
        recipeOne = new RecipeEntity();

        List<Ingredient> set2 = new ArrayList<>();
        set2.add(Ingredient.SNAKE);
        recipeTwo = new RecipeEntity();

        recipeOne.toBuilder().name("Recipe One").ingredients(set1).build();
        recipeTwo.toBuilder().name("Recipe Two").ingredients(set2).build();

        List<RecipeEntity> recipeOneList = new ArrayList<>();
        List<RecipeEntity> recipeTwoList = new ArrayList<>();
        recipeOneList.add(recipeOne);
        recipeTwoList.add(recipeTwo);
        student1 = new StudentEntity();
        student2 = new StudentEntity();
        student1.toBuilder().name("asd").recipeEntities(recipeOneList).build();
        student2.toBuilder().name("asd2").recipeEntities(recipeOneList).build();

    }

    @Test
    public void findById_ReturnsTheRecipesWithTheRequestedId() throws Exception {
        Long firstId = 1L;
        Long secondId = 2L;
        List<RecipeEntity> recipeOneList;

    }
}