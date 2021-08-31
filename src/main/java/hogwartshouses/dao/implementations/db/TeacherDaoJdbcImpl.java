package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.TeacherDao;
import hogwartshouses.model.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TeacherDaoJdbcImpl implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<TeacherEntity> teacherMapper;

    @Autowired
    public TeacherDaoJdbcImpl(JdbcTemplate jdbcTemplate, RowMapper<TeacherEntity> teacherMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public List<TeacherEntity> listAllTeacher() {
        String sql = "SELECT t.id, t.name, t.subject, t.is_witch, t.age, t.wand_id, w.wood_type, w.color " +
                "FROM teacher t INNER JOIN wand w ON t.wand_id = w.id;";
        return jdbcTemplate.query(sql, teacherMapper);
    }

    @Override
    public TeacherEntity getTeacher(Long id) {
        try {
            String sql = "SELECT t.id, t.name, t.subject, t.is_witch, t.age, t.wand_id, w.wood_type, w.color " +
                    "FROM teacher t INNER JOIN wand w ON t.wand_id = w.id WHERE t.id = ?;";
            return jdbcTemplate.queryForObject(sql, teacherMapper, id);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("Teacher not found!");
        }
    }

    @Override
    public void addTeacher(TeacherEntity teacher) {
        String sql = "INSERT INTO teacher (name, subject, is_witch, age, wand_id) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, teacher.getName(), teacher.getSubject(),
                teacher.isWitch(), teacher.getAge(), teacher.getWandEntity().getId());
    }

    @Override
    public void updateTeacher(Long id, TeacherEntity teacher) {
        String sql = "UPDATE teacher SET name = ?, subject = ?, is_witch = ?, age = ?, wand_id = ? WHERE id = ?;";
        jdbcTemplate.update(sql, teacher.getName(), teacher.getSubject(), teacher.isWitch(),
                teacher.getAge(), teacher.getWandEntity().getId(), id);
    }

    @Override
    public void deleteTeacher(Long id) {
        String sql = "DELETE FROM teacher WHERE id = ?;";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public List<TeacherEntity> listTeacherByWandWoodType(String woodType) {
        String sql = "SELECT t.id, t.name, t.subject, t.is_witch, t.age, t.wand_id, w.wood_type, w.color " +
                "FROM teacher t INNER JOIN wand w ON t.wand_id = w.id WHERE w.wood_type = ?;";
        return jdbcTemplate.query(sql, teacherMapper, woodType);
    }
}