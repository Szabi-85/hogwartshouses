package hogwartshouses.dao;

import hogwartshouses.model.entity.SpellEntity;

import java.util.List;

public interface SpellDao {
    SpellEntity save(SpellEntity spellEntity);
    SpellEntity findById(Long id);
    List<SpellEntity> findAll();
    void deleteById(Long id);
}