package hogwartshouses.dao.implementations.jpa;

import hogwartshouses.dao.SpellDao;
import hogwartshouses.dao.implementations.repository.SpellDaoRepository;
import hogwartshouses.model.entity.SpellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SpellDaoJpa implements SpellDao {
    @Autowired
    private SpellDaoRepository spellDaoRepository;

    @Override
    public SpellEntity save(SpellEntity spellEntity) {
        return spellDaoRepository.save(spellEntity);
    }

    @Override
    public SpellEntity findById(Long id) {
        Optional<SpellEntity> spellOptional = spellDaoRepository.findById(id);
        if (spellOptional.isEmpty()) throw new RuntimeException("Spell not found!");
        return spellOptional.get();
    }

    @Override
    public List<SpellEntity> findAll() {
        List<SpellEntity> spells = new ArrayList<>();
        spellDaoRepository.findAll().forEach(spells::add);
        return spells;
    }

    @Override
    public void deleteById(Long id) {
        spellDaoRepository.deleteById(id);
    }
}