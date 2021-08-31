package hogwartshouses.dao.mapper;

import hogwartshouses.model.entity.TeacherEntity;
import hogwartshouses.model.entity.WandEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeacherMapper implements RowMapper<TeacherEntity> {
    @Override
    public TeacherEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        WandEntity wandEntity = new WandEntity();
        wandEntity.setId(resultSet.getLong("wand_id"));
        wandEntity.setWoodType(resultSet.getString("wood_type"));
        wandEntity.setColor(resultSet.getString("color"));
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(resultSet.getLong("id"));
        teacherEntity.setName(resultSet.getString("name"));
        teacherEntity.setSubject(resultSet.getString("subject"));
        teacherEntity.setWitch(resultSet.getBoolean("is_witch"));
        teacherEntity.setAge(resultSet.getInt("age"));
        teacherEntity.setWandEntity(wandEntity);
        return teacherEntity;
    }
}