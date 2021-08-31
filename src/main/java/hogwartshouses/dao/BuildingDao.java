package hogwartshouses.dao;

import hogwartshouses.model.entity.BuildingEntity;

import java.util.Optional;

public interface BuildingDao {
    <S extends BuildingEntity> S save(S buildingEntity);
    Optional<BuildingEntity> findById(Long id);
    Iterable<BuildingEntity> findAll();
    void deleteById(Long id);
}