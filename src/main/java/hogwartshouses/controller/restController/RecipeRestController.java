package hogwartshouses.controller.restController;

import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.types.Ingredient;
import hogwartshouses.service.RecipeService;
import hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-api/potions")
public class RecipeRestController {
    private final RecipeService recipeService;
    private final StudentService studentService;

    @Autowired
    public RecipeRestController(RecipeService recipeService, StudentService studentService) {
        this.recipeService = recipeService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<RecipeEntity> listRecipes() {
        return recipeService.listRecipes();
    }

    @PostMapping("/{studentId}")
    public String brewPotionByStudent(@PathVariable long studentId, @RequestBody List<Ingredient> ingredients) {
        recipeService.brewPotionByStudent(studentId, ingredients);

        return "redirect:/potions";
    }

    @PostMapping
    public void addPotion(@RequestBody RecipeEntity recipeEntity) {
        recipeService.addRecipe(recipeEntity);
    }

    @GetMapping("/{studentId}")
    public List<RecipeEntity> getRecipesOfStudent(@PathVariable long studentId) {
        return recipeService.findRecipesByStudentId(studentId);
    }

    @PutMapping("/rename/{potionId}")
    public void updatePotionById(@PathVariable long potionId, @RequestBody RecipeEntity recipeEntity) {
        recipeService.renameRecipeById(potionId, recipeEntity.getName());
    }

    @DeleteMapping("/{potionId}")
    public void deletePotionById(@PathVariable long potionId) {
        recipeService.deleteRecipeById(potionId);
    }
}