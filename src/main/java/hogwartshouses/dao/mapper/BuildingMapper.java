package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.BuildingEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BuildingMapper implements RowMapper<BuildingEntity> {
    @Override
    public BuildingEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return BuildingEntity.builder()
                .id(resultSet.getLong("id"))
                .numberOfRooms(resultSet.getInt("number_of_rooms"))
                .build();
    }
}