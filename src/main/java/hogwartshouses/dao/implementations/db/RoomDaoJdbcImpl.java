package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.RoomDao;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.model.entity.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoomDaoJdbcImpl implements RoomDao {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<RoomEntity> roomMapper;
    private RowMapper<StudentEntity> studentMapper;
    private ModelMapper modelMapper;

    @Autowired
    public RoomDaoJdbcImpl(JdbcTemplate jdbcTemplate, RowMapper<RoomEntity> roomMapper, RowMapper<StudentEntity> studentMapper, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.roomMapper = roomMapper;
        this.studentMapper = studentMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoomEntity> listRooms() {
        String sql = "SELECT * FROM room;";
        return jdbcTemplate.query(sql, roomMapper).stream()
                .map(room -> {
                    room.setStudentEntities(getStudentsByRoomId(room.getId()));
                    return room;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addRoom(RoomEntity roomEntity) {
        String sql = "INSERT INTO room (building_id, room_number, capacity, number_of_beds, empty_bed) VALUES (?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                    ps.setLong(1, 1);
                    ps.setInt(2, roomEntity.getRoomNumber());
                    ps.setInt(3, roomEntity.getCapacity());
                    ps.setInt(4, roomEntity.getNumberOfBeds());
                    ps.setBoolean(5, roomEntity.isHasEmptyBed());
                    return ps;
                },
                keyHolder
        );
        roomEntity.setId((long) keyHolder.getKey());
    }


    @Override
    public RoomEntity findRoomById(long id) {
        String sql = "SELECT * FROM room WHERE id = ?;";
        try {
            RoomEntity roomEntity = jdbcTemplate.queryForObject(sql, roomMapper, id);
            roomEntity.setStudentEntities(getStudentsByRoomId(roomEntity.getId()));
            return roomEntity;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteRoomById(long id) {
        String sql = "DELETE FROM room WHERE id = ?;";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateRoomById(long id, RoomEntity newRoomEntity) {
        String sql = "UPDATE room SET building_id = ?, room_number = ?, capacity = ?, number_of_beds = ?, empty_bed = ? WHERE id = ?;";

        jdbcTemplate.update(sql,
                newRoomEntity.getRoomNumber(),
                newRoomEntity.getCapacity(),
                newRoomEntity.getNumberOfBeds(),
                newRoomEntity.isHasEmptyBed(), id);
    }
    @Override
    public Set<StudentEntity> getStudentsByRoomId(long id) {
        String sql = "SELECT student.id, student.name, student.house_type, student.pet_type, student.pure_blood " +
                "FROM resident " +
                "    INNER JOIN student " +
                "        ON resident.student_id = student.id " +
                "WHERE resident.room_id = ?";

        return new HashSet<>(jdbcTemplate.query(sql, studentMapper, id));
    }

    @Override
    public List<RoomEntity> findAvailableRooms() {
        String sql = "SELECT DISTINCT ROOM.ID, ROOM_NUMBER, CAPACITY, NUMBER_OF_BEDS, HAS_EMPTY_BEDS" +
                " FROM ROOM " +
                "JOIN RESIDENT RES ON ROOM.ID = RES.ROOM_ID " +
                "WHERE HAS_EMPTY_BEDS IS TRUE";
        return jdbcTemplate.query(sql, roomMapper);
    }

    @Override
    public List<RoomEntity> findRoomWithNoCatOrOwl() {
        String sql = "SELECT DISTINCT R.ID, R.ROOM_NUMBER, R.CAPACITY, R.NUMBER_OF_BEDS, R.HAS_EMPTY_BEDS, R.BUILDING_ID" +
                " FROM ROOM R " +
                "JOIN RESIDENT RES ON R.ID = RES.ROOM_ID " +
                "JOIN STUDENT S on RES.STUDENT_ID = S.ID " +
                "WHERE S.PET_TYPE NOT IN ('CAT', 'OWL')";
        return jdbcTemplate.query(sql, roomMapper);
    }
}