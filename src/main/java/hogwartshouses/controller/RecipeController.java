package hogwartshouses.controller;

import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.types.Ingredient;
import hogwartshouses.service.RecipeService;
import hogwartshouses.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final StudentService studentService;

    @Autowired
    public RecipeController(RecipeService recipeService, StudentService studentService) {
        this.recipeService = recipeService;
        this.studentService = studentService;
    }

    @GetMapping("/potions")
    public String listRecipes(Model model) {
        model.addAttribute("recipes", recipeService.listRecipes());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("ingredients", Ingredient.values());

        return "potions";
    }

    @PostMapping("/potions/{studentId}")
    public String brewPotionByStudent(@PathVariable long studentId, @RequestBody List<Ingredient> ingredients) {
        recipeService.brewPotionByStudent(studentId, ingredients);

        return "redirect:/potions";
    }

    @GetMapping("/potions/{studentId}")
    public String getRecipesOfStudent(@PathVariable long studentId, Model model) {
        model.addAttribute("recipes", recipeService.findRecipesByStudentId(studentId));

        return "potions";
    }

    @PutMapping("/potions/rename/{potionId}")
    public String updatePotionById(@PathVariable long potionId, @RequestBody RecipeEntity recipeEntity) {
        recipeService.renameRecipeById(potionId, recipeEntity.getName());

        return "redirect:/potions";
    }

    @DeleteMapping("/potions/delete/{potionId}")
    public String deletePotionById(@PathVariable long potionId) {
        recipeService.deleteRecipeById(potionId);

        return "redirect:/potions";
    }
}