package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.WandDao;
import hogwartshouses.model.entity.WandEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class WandDaoMem implements WandDao {
    private List<WandEntity> wands = new ArrayList<>();
    private final AtomicLong atomicLong = new AtomicLong(1);

    @Override
    public WandEntity save(WandEntity wandEntity) {
        if (wandEntity.getId() == null || wandEntity.getId().equals(0L)) {
            wandEntity.setId(atomicLong.getAndIncrement());
            wands.add(wandEntity);
        } else {
            wands.forEach(wand -> {
                if(wand.getId().equals(wandEntity.getId())){
                    wand.setWoodType(wandEntity.getWoodType());
                    wand.setColor(wandEntity.getColor());
                    wand.setTeacher(wandEntity.getTeacher());
                    wand.setSpellEntities(wandEntity.getSpellEntities());
                }
            });
        }
        return wandEntity;
    }

    @Override
    public WandEntity findById(Long id) {
        return wands.stream()
                .filter(wandEntity -> wandEntity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<WandEntity> findAll() {
        return new ArrayList<>(wands);
    }

    @Override
    public void deleteById(Long id) {
        wands.removeIf(wandEntity -> wandEntity.getId().equals(id) && wandEntity.getTeacher() == null);
    }
}