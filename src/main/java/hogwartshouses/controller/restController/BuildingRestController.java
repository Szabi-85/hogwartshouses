package hogwartshouses.controller.restController;

import hogwartshouses.model.entity.BuildingEntity;
import hogwartshouses.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest-api/buildings")
public class BuildingRestController {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public BuildingEntity add(@RequestBody BuildingEntity buildingEntity) {
        buildingEntity.setId(null);
        return buildingService.save(buildingEntity);
    }

    @PutMapping("/{id}")
    public BuildingEntity update(@RequestBody BuildingEntity buildingEntity, @PathVariable Long id) {
        buildingEntity.setId(id);
        return buildingService.save(buildingEntity);
    }

    @GetMapping("/{id}")
    public BuildingEntity findById(@PathVariable Long id) {
        return buildingService.findById(id);
    }

    @GetMapping
    public List<BuildingEntity> findAll() {
        return buildingService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        buildingService.deleteById(id);
    }
}