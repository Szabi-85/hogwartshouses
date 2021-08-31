package hogwartshouses.service;

import hogwartshouses.dao.BuildingDao;
import hogwartshouses.model.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {
    @Autowired
    private BuildingDao buildingDao;

    public BuildingEntity save(BuildingEntity buildingEntity) {
        return buildingDao.save(buildingEntity);
    }

    public BuildingEntity findById(Long id) {
        return buildingDao.findById(id).orElse(null);
    }

    public List<BuildingEntity> findAll() {
        List<BuildingEntity> buildings = new ArrayList<>();
        buildingDao.findAll().forEach(buildings::add);
        return buildings;
    }

    public void deleteById(Long id) {
        buildingDao.deleteById(id);
    }
}
