package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.RecipeEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecipeMapper implements RowMapper<RecipeEntity> {
    @Override
    public RecipeEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return RecipeEntity.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}