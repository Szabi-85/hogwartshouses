package hogwartshouses.controller.restController;

import hogwartshouses.model.entity.WandEntity;
import hogwartshouses.service.WandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-api/wands")
public class WandRestController {
    @Autowired
    private WandService wandService;

    @PostMapping
    public void save(@RequestBody WandEntity wandEntity) {
        wandEntity.setId(null);
        wandService.save(wandEntity);
    }

    @GetMapping("/{id}")
    public WandEntity findById(@PathVariable Long id) {
        return wandService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody WandEntity wandEntity){
        wandEntity.setId(id);
        save(wandEntity);
    }

    @GetMapping
    public List<WandEntity> findAll() {
        return wandService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        wandService.deleteById(id);
    }
}