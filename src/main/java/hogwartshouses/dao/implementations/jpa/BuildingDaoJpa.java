package hogwartshouses.dao.implementations.jpa;

import hogwartshouses.dao.BuildingDao;
import hogwartshouses.dao.implementations.repository.BuildingDaoRepository;
import hogwartshouses.model.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BuildingDaoJpa implements BuildingDao {
    @Autowired
    private BuildingDaoRepository buildingDaoRepository;

    @Override
    public <S extends BuildingEntity> S save(S buildingEntity) {
        return buildingDaoRepository.save(buildingEntity);
    }

    @Override
    public Optional<BuildingEntity> findById(Long id) {
        return buildingDaoRepository.findById(id);
    }

    @Override
    public Iterable<BuildingEntity> findAll() {
        return buildingDaoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        buildingDaoRepository.deleteById(id);
    }
}
