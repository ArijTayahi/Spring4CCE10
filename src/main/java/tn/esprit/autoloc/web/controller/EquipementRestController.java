package tn.esprit.autoloc.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
public class EquipementRestController {

    private final IEquipementService equipementService;

    public EquipementRestController(IEquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @PostMapping
    public Equipement create(@RequestBody Equipement equipement) { return equipementService.create(equipement); }

    @GetMapping
    public List<Equipement> findAll() { return equipementService.findAll(); }

    @GetMapping("/{id}")
    public Equipement findById(@PathVariable Long id) { return equipementService.findById(id); }

    @PutMapping("/{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) { return equipementService.update(id, equipement); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { equipementService.deleteById(id); }
}
