package hogwartshouses.controller;

import hogwartshouses.model.entity.WandEntity;
import hogwartshouses.service.WandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wands")
public class WandController {
    @Autowired
    private WandService wandService;

    @PostMapping
    public String save(@RequestBody WandEntity wandEntity) {
        wandEntity.setId(null);
        wandService.save(wandEntity);

        return "redirect:/wands";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("wand", wandService.findById(id));

        return "wand";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id, @RequestBody WandEntity wandEntity){
        wandEntity.setId(id);
        save(wandEntity);

        return "redirect:/wands";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("wands", wandService.findAll());

        return "wands";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        wandService.deleteById(id);

        return "redirect:/wands";
    }
}