package hogwartshouses.dao.implementations.db;

import hogwartshouses.dao.WandDao;
import hogwartshouses.model.entity.SpellEntity;
import hogwartshouses.model.entity.WandEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class WandDaoJdbcImpl implements WandDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<WandEntity> wandMapper;
    private final RowMapper<SpellEntity> spellMapper;

    public WandDaoJdbcImpl(JdbcTemplate jdbcTemplate, RowMapper<WandEntity> wandMapper, RowMapper<SpellEntity> spellMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.wandMapper = wandMapper;
        this.spellMapper = spellMapper;
    }

    @Override
    public WandEntity save(WandEntity wandEntity) {
        if (wandEntity.getId() == null || wandEntity.getId().equals(0L)) {
            String sql = "INSERT INTO wand (wood_type, color) VALUES(?, ?);";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                        PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                        ps.setString(1, wandEntity.getWoodType());
                        ps.setString(2, wandEntity.getColor());
                        return ps;
                    },
                    keyHolder
            );
            wandEntity.setId((long) keyHolder.getKey());
        } else {
            String sql = "UPDATE wand SET wood_type = ?, color = ? WHERE id = ?;";
            jdbcTemplate.update(sql, wandEntity.getWoodType(), wandEntity.getColor(), wandEntity.getId());
        }

        return wandEntity;
    }

    @Override
    public WandEntity findById(Long id) {
        String sql = "SELECT * FROM wand WHERE id = ?;";
        WandEntity wandEntity = jdbcTemplate.queryForObject(sql, wandMapper, id);
        wandEntity.setSpellEntities(getSpellsOfWand(wandEntity.getId()));
        return  wandEntity;
    }

    @Override
    public List<WandEntity> findAll() {
        String sql = "SELECT * FROM wand;";
        List<WandEntity> wands = jdbcTemplate.query(sql, wandMapper);
        wands.forEach(wand -> wand.setSpellEntities(getSpellsOfWand(wand.getId())));
        return wands;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM wand WHERE id = ?;";
        jdbcTemplate.update(sql,id);
    }

    public List<SpellEntity> getSpellsOfWand(Long wandId){
        String sql = "SELECT * FROM WAND_SPELLS WHERE id = ?;";
        return jdbcTemplate.query(sql, spellMapper, wandId);
    }
}