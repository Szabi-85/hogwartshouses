package hogwartshouses.dao;

import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.types.Ingredient;

import java.util.List;

public interface RecipeDao {
    List<RecipeEntity> listRecipes();
    void addRecipe(RecipeEntity recipeEntity);
    RecipeEntity findRecipeById(long id);
    void deleteRecipeById(long id);
    void updateRecipeById(long id, RecipeEntity recipeEntity);
    RecipeEntity getRecipeIfExists(List<Ingredient> ingredients);
    void addRecipeToStudentById(RecipeEntity recipeEntity, long studentId);
    List<RecipeEntity> findRecipesByStudentId(long studentId);
    void renameRecipeById(long id, String name);
}