package hogwartshouses.service;

import hogwartshouses.dao.WandDao;
import hogwartshouses.model.entity.WandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WandService {
    @Autowired
    private WandDao wandDao;

    public void save(WandEntity wandEntity) {
        wandDao.save(wandEntity);
    }

    public WandEntity findById(Long aLong) {
        return wandDao.findById(aLong);
    }

    public List<WandEntity> findAll() {
        return wandDao.findAll();
    }

    public void deleteById(Long aLong) {
        wandDao.deleteById(aLong);
    }
}