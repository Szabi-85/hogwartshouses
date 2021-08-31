package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.SpellEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SpellMapper implements RowMapper<SpellEntity> {
    @Override
    public SpellEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return SpellEntity.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .power(resultSet.getInt("power"))
                .isBanned(resultSet.getBoolean("is_banned"))
                .build();
    }
}