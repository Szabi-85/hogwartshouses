package hogwartshouses.service;

import hogwartshouses.dao.RecipeDao;
import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.types.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {
    private final RecipeDao recipeDao;

    @Autowired
    public RecipeService(RecipeDao recipeDaoJdbcImpl) {
        this.recipeDao = recipeDaoJdbcImpl;
    }

    public List<RecipeEntity> listRecipes() {
        return recipeDao.listRecipes();
    }

    public void addRecipe(RecipeEntity recipeEntity) {
        recipeDao.addRecipe(recipeEntity);
    }

    public RecipeEntity findRecipeById(long id) {
        return recipeDao.findRecipeById(id);
    }

    public void deleteRecipeById(long id) {
        recipeDao.deleteRecipeById(id);
    }

    public void updateRecipeById(long id, RecipeEntity recipeEntity) {
        recipeDao.updateRecipeById(id, recipeEntity);
    }

    public void brewPotionByStudent(long studentId, List<Ingredient> ingredients) {
        RecipeEntity recipeEntity = recipeDao.getRecipeIfExists(ingredients);
        if (recipeEntity == null) {
            recipeEntity = RecipeEntity.builder()
                    .ingredients(ingredients)
                    .name("Potion #" + UUID.randomUUID())
                    .build();
            recipeDao.addRecipe(recipeEntity);
        }
        recipeDao.addRecipeToStudentById(recipeEntity, studentId);
    }

    public List<RecipeEntity> findRecipesByStudentId(long studentId) {
        return recipeDao.findRecipesByStudentId(studentId);
    }

    public void renameRecipeById(long id, String name) {
        recipeDao.renameRecipeById(id, name);
    }
}