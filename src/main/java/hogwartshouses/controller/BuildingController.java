package hogwartshouses.controller;

import hogwartshouses.model.entity.BuildingEntity;
import hogwartshouses.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public String add(@RequestBody BuildingEntity buildingEntity) {
        buildingEntity.setId(null);
        buildingService.save(buildingEntity);

        return "redirect:/buildings";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody BuildingEntity buildingEntity, @PathVariable Long id) {
        buildingEntity.setId(id);
        buildingService.save(buildingEntity);

        return "redirect:/buildings";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        BuildingEntity buildingEntity = buildingService.findById(id);
        if (buildingEntity == null) return "error";

        model.addAttribute("building", buildingEntity);
        return "building";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("buildings", buildingService.findAll());

        return "buildings";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        buildingService.deleteById(id);

        return "redirect:/buildings";
    }
}