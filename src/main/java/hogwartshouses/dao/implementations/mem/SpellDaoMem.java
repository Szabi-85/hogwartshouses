package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.SpellDao;
import hogwartshouses.model.entity.SpellEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SpellDaoMem implements SpellDao {
    private static final AtomicLong counter = new AtomicLong(1);
    private final List<SpellEntity> spells = new ArrayList<>();

    @Override
    public SpellEntity save(SpellEntity spellEntity) {
        if (spellEntity.getId() == null || spellEntity.getId().equals(0L)) {
            spellEntity.setId(counter.getAndIncrement());
            spells.add(spellEntity);
        } else {
            spells.forEach(spell -> {
                if(spell.getId().equals(spellEntity.getId())){
                    spell.setName(spellEntity.getName());
                    spell.setPower(spellEntity.getPower());
                    spell.setBanned(spellEntity.isBanned());
                }
            });
        }
        return spellEntity;
    }

    @Override
    public SpellEntity findById(Long id) {
        return spells.stream()
                .filter(spell -> spell.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<SpellEntity> findAll() {
        return spells;
    }

    @Override
    public void deleteById(Long id) {
        spells.removeIf(building -> building.getId().equals(id));
    }
}