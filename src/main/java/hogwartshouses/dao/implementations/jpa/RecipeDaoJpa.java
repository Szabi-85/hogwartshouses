package hogwartshouses.dao.implementations.jpa;

import hogwartshouses.dao.RecipeDao;
import hogwartshouses.dao.implementations.repository.RecipeDaoRepository;
import hogwartshouses.dao.implementations.repository.StudentDaoRepository;
import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RecipeDaoJpa implements RecipeDao {
    @Autowired
    private RecipeDaoRepository recipeDaoRepository;

    @Autowired
    private StudentDaoRepository studentDaoRepository;

    @Override
    public List<RecipeEntity> listRecipes() {
        List<RecipeEntity> recipes = new ArrayList<>();
        recipeDaoRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    @Override
    public void addRecipe(RecipeEntity recipeEntity) {
        recipeDaoRepository.save(recipeEntity);
    }

    @Override
    public RecipeEntity findRecipeById(long id) {
        Optional<RecipeEntity> temp = recipeDaoRepository.findById(id);
        if (temp.isEmpty()) {
            throw new RuntimeException("Recipe list is empty.");
        }
        return temp.get();
    }

    @Override
    public void deleteRecipeById(long id) {
        recipeDaoRepository.deleteById(id);
    }

    @Override
    public void updateRecipeById(long id, RecipeEntity recipeEntity) {
        recipeEntity.setId(id);
        recipeDaoRepository.save(recipeEntity);
    }

    @Override
    public RecipeEntity getRecipeIfExists(List<Ingredient> ingredients) {
        RecipeEntity resultEntity = null;
        for (RecipeEntity recipeEntity : listRecipes()) {
            if (recipeEntity.getIngredients().equals(ingredients)) {
                resultEntity = recipeEntity;
            }
        }
        return resultEntity;
    }

    @Override
    public void addRecipeToStudentById(RecipeEntity recipeEntity, long studentId) {
        StudentEntity tempStudent = studentDaoRepository.findById(studentId).get();
            if (!tempStudent.getRecipeEntities().contains(recipeEntity)) {
                tempStudent.getRecipeEntities().add(recipeEntity);
            }
        studentDaoRepository.save(tempStudent);
    }

    @Override
    public List<RecipeEntity> findRecipesByStudentId(long studentId) {
        StudentEntity tempStudent = studentDaoRepository.findById(studentId).get();
        return tempStudent.getRecipeEntities();
    }

    @Override
    public void renameRecipeById(long id, String name) {
        RecipeEntity tempRecipe = recipeDaoRepository.findById(id).get();
        tempRecipe.setName(name);
        recipeDaoRepository.save(tempRecipe);
    }
}