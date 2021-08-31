package hogwartshouses.dao;

import hogwartshouses.model.entity.WandEntity;

import java.util.List;

public interface WandDao {
    WandEntity save(WandEntity wandEntity);
    WandEntity findById(Long id);
    List<WandEntity> findAll();
    void deleteById(Long id);
}
