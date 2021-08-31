package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.SpellDao;
import hogwartshouses.model.entity.SpellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class SpellDaoJdbcImpl implements SpellDao {
    @Autowired
    private RowMapper<SpellEntity> spellMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SpellEntity save(SpellEntity spellEntity) {
        if (spellEntity.getId() == null || spellEntity.getId().equals(0L)) {
            String sql = "INSERT INTO spell (name, power, is_banned) VALUES (?, ?, ?);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                        ps.setString(1, spellEntity.getName());
                        ps.setInt(2, spellEntity.getPower());
                        ps.setBoolean(3, spellEntity.isBanned());
                        return ps;
                    },
                    keyHolder
            );
            spellEntity.setId((long) keyHolder.getKey());
        } else {
            String sql = "UPDATE spell SET name = ?, power = ?, is_banned = ? WHERE id = ?;";
            jdbcTemplate.update(
                    sql,
                    spellEntity.getName(),
                    spellEntity.getPower(),
                    spellEntity.isBanned(),
                    spellEntity.getId());
        }
        return spellEntity;
    }

    @Override
    public SpellEntity findById(Long id) {
        String sql = "SELECT * FROM spell WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, spellMapper, id);
    }

    @Override
    public List<SpellEntity> findAll() {
        String sql = "SELECT * FROM spell;";
        return jdbcTemplate.query(sql, spellMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM spell WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }
}