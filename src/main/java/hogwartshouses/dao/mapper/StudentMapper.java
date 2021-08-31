package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.StudentEntity;
import hogwartshouses.model.entity.types.HouseType;
import hogwartshouses.model.entity.types.PetType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentMapper implements RowMapper<StudentEntity> {
    @Override
    public StudentEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return StudentEntity.builder().id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .houseType(HouseType.valueOf(resultSet.getString("house_type")))
                .petType(PetType.valueOf(resultSet.getString("pet_type")))
                .hasPureBlood(resultSet.getBoolean("pure_blood"))
                .build();
    }
}