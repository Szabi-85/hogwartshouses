package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.BuildingDao;
import hogwartshouses.model.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class BuildingDaoJdbcImpl implements BuildingDao {
    private final RowMapper<BuildingEntity> buildingMapper;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BuildingDaoJdbcImpl(JdbcTemplate jdbcTemplate, RowMapper<BuildingEntity> buildingMapper) {
        this.buildingMapper = buildingMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public <S extends BuildingEntity> S save(S buildingEntity) {
        if (buildingEntity.getId() == null || buildingEntity.getId().equals(0L)) {
            String sql = "INSERT INTO building (number_of_rooms) VALUES (?);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        ps.setInt(1, buildingEntity.getNumberOfRooms());
                        return ps;
                    },
                    keyHolder
            );
            buildingEntity.setId((long) keyHolder.getKey());
        }
        else {
            String sql = "UPDATE building SET number_of_rooms = ? WHERE id = ?;";
            jdbcTemplate.update(sql, buildingEntity.getNumberOfRooms(), buildingEntity.getId());
        }

        return buildingEntity;
    }

    @Override
    public Optional<BuildingEntity> findById(Long id) {
        String sql = "SELECT * FROM building WHERE id = ?;";
        BuildingEntity building = jdbcTemplate.queryForObject(sql, buildingMapper, id);

        return building == null ? Optional.empty() : Optional.of(building);
    }

    @Override
    public Iterable<BuildingEntity> findAll() {
        String sql = "SELECT * FROM building;";
        return jdbcTemplate.query(sql, buildingMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM building WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }
}