package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.RecipeDao;
import hogwartshouses.dao.StudentDao;
import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RecipeDaoMem implements RecipeDao {

    private final List<RecipeEntity> recipeEntities = new ArrayList<>();
    private static AtomicLong counter = new AtomicLong(0);

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<RecipeEntity> listRecipes() {
        return recipeEntities;
    }

    @Override
    public void addRecipe(RecipeEntity recipeEntity) {
        recipeEntity.setId(counter.incrementAndGet());
        recipeEntities.add(recipeEntity);
    }

    @Override
    public RecipeEntity findRecipeById(long id) {
        return recipeEntities.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteRecipeById(long id) {
        recipeEntities.removeIf(recipe -> recipe.getId().equals(id));
    }

    @Override
    public void updateRecipeById(long id, RecipeEntity recipeEntity) {
        recipeEntities.forEach(recipe -> {
            if (recipe.getId().equals(id)) {
                recipe.setName(recipeEntity.getName());
            }
        });
    }

    @Override
    public RecipeEntity getRecipeIfExists(List<Ingredient> ingredients) {
        RecipeEntity resultEntity = null;
        for (RecipeEntity recipeEntity : recipeEntities) {
            if (recipeEntity.getIngredients().equals(ingredients)) {
                resultEntity = recipeEntity;
            }
        }
        return resultEntity;
    }

    @Override
    public void addRecipeToStudentById(RecipeEntity recipeEntity, long studentId) {
        studentDao.findAll().forEach(student -> {
            if (student.getId().equals(studentId)) {
                if (!student.getRecipeEntities().contains(recipeEntity)) {
                    student.getRecipeEntities().add(recipeEntity);
                }
            }
        });
    }

    @Override
    public List<RecipeEntity> findRecipesByStudentId(long studentId) {
        List<RecipeEntity> tempList = new ArrayList<>();
            for (StudentEntity student : studentDao.findAll()) {
                if (student.getId().equals(studentId)) {
                    tempList = student.getRecipeEntities();
                }
            }
        return tempList;
    }

    @Override
    public void renameRecipeById(long id, String name) {
        recipeEntities.forEach(recipe -> {
            if (recipe.getId().equals(id)) {
                recipe.setName(name);
            }
        });
    }
}