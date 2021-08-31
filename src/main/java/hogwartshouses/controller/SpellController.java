package hogwartshouses.controller;

import hogwartshouses.model.entity.SpellEntity;
import hogwartshouses.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/spells")
public class SpellController {
    @Autowired
    private SpellService spellService;

    @PostMapping
    public String add(@RequestBody SpellEntity spellEntity) {
        spellService.save(spellEntity);

        return "redirect:/spells";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody SpellEntity spellEntity) {
        spellEntity.setId(id);
        spellService.save(spellEntity);

        return "redirect:/spells";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        spellService.delete(id);

        return "redirect:/spells";
    }

    @GetMapping("/{id}")
    public String findSpellById(@PathVariable Long id, Model model) {
        SpellEntity spellEntity = spellService.findSpellById(id);
        if (spellEntity == null) return "error";

        model.addAttribute("room", spellEntity);
        return "spell";
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("spells", spellService.findAll());

        return "spells";
    }
}
