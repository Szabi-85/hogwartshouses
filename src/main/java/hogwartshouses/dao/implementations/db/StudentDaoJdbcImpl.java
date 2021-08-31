package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.StudentDao;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class StudentDaoJdbcImpl implements StudentDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<StudentEntity> studentMapper;
    private final RowMapper<RoomEntity> roomMapper;

    @Autowired
    public StudentDaoJdbcImpl(JdbcTemplate jdbcTemplate, RowMapper<StudentEntity> studentMapper, RowMapper<RoomEntity> roomMapper) {
        this.studentMapper = studentMapper;
        this.roomMapper = roomMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(StudentEntity studentEntity) {
        String sql = "INSERT INTO student (name, house_type, pet_type, pure_blood) VALUES (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, studentEntity.getName());
                    ps.setString(2, studentEntity.getHouseType().toString());
                    ps.setString(3, studentEntity.getPetType().toString());
                    ps.setBoolean(4, studentEntity.isHasPureBlood());
                    return ps;
                },
                keyHolder
        );
        studentEntity.setId((long) keyHolder.getKey());
    }



    @Override
    public List<StudentEntity> findAll() {
        //TODO connect room number
        String sql = "SELECT * FROM student;";
        return jdbcTemplate.query(sql, studentMapper);
    }

    @Override
    public StudentEntity findStudentById(long id) {
        String sql = "SELECT * FROM student WHERE id = ?;";
        StudentEntity studentEntity = jdbcTemplate.queryForObject(sql, studentMapper, id);
        if (studentEntity != null && studentEntity.getId() != null) studentEntity.setRoomEntity(findRoomByStudentId(id));

        return studentEntity;
    }

    @Override
    public void deleteStudent(long id) {
        String sql = "DELETE * FROM student WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public RoomEntity findRoomByStudentId(long id) {
        String sql = "SELECT room.building_id, room.room_number, room.capacity, room.number_of_beds, room.empty_bed " +
                "FROM resident " +
                "  INNER JOIN room" +
                "      ON resident.room_id = room.id " +
                "WHERE resident.studen_id = ?";

        return jdbcTemplate.queryForObject(sql, roomMapper, id);
    }

    @Override
    public void connectStudentAndRoom(StudentEntity studentEntity, RoomEntity roomEntity) {
        //TODO Check if the student already exists, if exists, then refresh room_id
        String sql = "INSERT INTO resident (student_id, room_id) VALUES (?, ?);";
        jdbcTemplate.update(sql, studentEntity.getId(), roomEntity.getId());
    }
}