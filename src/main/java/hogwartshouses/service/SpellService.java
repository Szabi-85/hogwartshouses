package hogwartshouses.service;

import hogwartshouses.dao.SpellDao;
import hogwartshouses.model.entity.SpellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpellService {
    @Autowired
    private SpellDao spellDao;

    public void save(SpellEntity spellEntity) {
        spellDao.save(spellEntity);
    }
    
    public void delete( Long id) {
        spellDao.deleteById(id);
    }

    public SpellEntity findSpellById(Long id) {
        return spellDao.findById(id);
    }

    public List<SpellEntity> findAll() {
        return spellDao.findAll();
    }
}