package hogwartshouses.dao.implementations.mem;

import hogwartshouses.dao.BuildingDao;
import hogwartshouses.model.entity.BuildingEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class BuildingDaoMem implements BuildingDao {

    private static final AtomicLong counter = new AtomicLong(1);
    private final List<BuildingEntity> buildings = new ArrayList<>();

    @Override
    public <S extends BuildingEntity> S save(S buildingEntity) {
        if (buildingEntity.getId() == null || buildingEntity.getId().equals(0L)) {
            buildingEntity.setId(counter.getAndIncrement());
            buildings.add(buildingEntity);
        }
        else {
            buildings.forEach(buildingToUpdate -> {
                if(buildingToUpdate.getId().equals(buildingEntity.getId())){
                    buildingToUpdate.setNumberOfRooms(buildingEntity.getNumberOfRooms());
                }
            });
        }
        return buildingEntity;
    }

    @Override
    public Optional<BuildingEntity> findById(Long id) {
        return buildings.stream()
                .filter(building -> building.getId().equals(id))
                .map(BuildingEntity::new)
                .findFirst();
    }

    @Override
    public Iterable<BuildingEntity> findAll() {
        return new ArrayList<>(buildings);
    }

    @Override
    public void deleteById(Long id) {
        buildings.removeIf(building -> building.getId().equals(id));
    }
}