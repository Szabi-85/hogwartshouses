package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.RoomEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomMapper implements RowMapper<RoomEntity> {
    @Override
    public RoomEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RoomEntity.builder()
                .id(rs.getLong("id"))
                .roomNumber(rs.getInt("room_number"))
                .capacity(rs.getInt("capacity"))
                .numberOfBeds(rs.getInt("number_of_beds"))
                .hasEmptyBed(rs.getBoolean("empty_bed"))
                .build();
    }
}