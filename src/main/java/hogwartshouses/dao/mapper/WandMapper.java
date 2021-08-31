package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.WandEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class WandMapper implements RowMapper<WandEntity> {
    @Override
    public WandEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return WandEntity.builder()
                .id(resultSet.getLong("id"))
                .woodType(resultSet.getString("woodType"))
                .color(resultSet.getString("color"))
                .build();
    }
}