package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.RecipeDao;
import hogwartshouses.model.entity.RecipeEntity;
import hogwartshouses.model.entity.types.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class RecipeDaoJdbcImpl implements RecipeDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<RecipeEntity> recipeMapper;

    @Autowired
    public RecipeDaoJdbcImpl(JdbcTemplate jdbcTemplate, RowMapper<RecipeEntity> recipeMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public List<RecipeEntity> listRecipes() {
        String sql = "SELECT DISTINCT(recipe.name) AS name, (SELECT string_agg(ingredient::text, ',') " +
                     "      FROM ingredient " +
                     "      WHERE ingredient.recipe_id = id) AS ingredient, recipe.id " +
                     "FROM recipe " +
                     "INNER JOIN ingredient ON ingredient.recipe_id = recipe.id;";

        return jdbcTemplate.query(sql, recipeMapper);
    }

    @Override
    public void addRecipe(RecipeEntity recipeEntity) {
        String sql = "INSERT INTO recipe (name) VALUES (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                    ps.setString(1, recipeEntity.getName());
                    return ps;
                },
                keyHolder
        );

        recipeEntity.setId((long) keyHolder.getKey());

        connectIngredientsByRecipe(recipeEntity);
    }

    @Override
    public RecipeEntity findRecipeById(long id) {
        String sql = "SELECT DISTINCT(recipe.name) AS name, (SELECT string_agg(ingredient::text, ',') " +
                     "      FROM ingredient " +
                     "      WHERE ingredient.recipe_id = id) AS ingredient, recipe.id " +
                     "FROM recipe " +
                     "INNER JOIN ingredient ON ingredient.recipe_id = recipe.id " +
                     "WHERE recipe.id = ?;";

        return jdbcTemplate.queryForObject(sql, recipeMapper, id);
    }

    @Override
    public void deleteRecipeById(long id) {
        String sql = "DELETE FROM recipe WHERE id = ?;";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateRecipeById(long id, RecipeEntity recipeEntity) {
        // TODO update recipe ingredients connection
        String sql = "UPDATE recipe SET name = ? WHERE id = ?;";

        jdbcTemplate.update(sql, recipeEntity.getName(), id);
    }

    @Override
    public RecipeEntity getRecipeIfExists(List<Ingredient> ingredients) {
        return listRecipes().stream().findFirst().orElse(null);
    }

    @Override
    public void addRecipeToStudentById(RecipeEntity recipeEntity, long studentId) {
        String sql = "SELECT recipe_id FROM student_recipe WHERE recipe_id = ? AND student_id = ?;";
        if (jdbcTemplate.queryForList(sql, Long.class, recipeEntity.getId(), studentId).isEmpty()) {
            sql = "INSERT INTO student_recipe VALUES (?, ?);";
            jdbcTemplate.update(sql, studentId, recipeEntity.getId());
        }
    }

    @Override
    public List<RecipeEntity> findRecipesByStudentId(long studentId) {
        String sql = "SELECT DISTINCT(r.name) AS name, (SELECT string_agg(ingredient::text, ',') " +
                     "     FROM ingredient " +
                     "     WHERE ingredient.recipe_id = id) AS ingredient, r.id " +
                     "FROM recipe r " +
                     "INNER JOIN student_recipe sr ON sr.recipe_id = r.id " +
                     "INNER JOIN ingredient i ON i.recipe_id = r.id " +
                     "WHERE sr.student_id = ?;";

        return jdbcTemplate.query(sql, recipeMapper, studentId);
    }

    @Override
    public void renameRecipeById(long id, String name) {
        String sql = "UPDATE recipe SET name = ? WHERE id = ?;";

        jdbcTemplate.update(sql, name, id);
    }

    private void connectIngredientsByRecipe(RecipeEntity recipeEntity) {

    }
}