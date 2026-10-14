package tn.esprit.autoloc.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
public class AgenceRestController {

    private final IAgenceService agenceService;

    public AgenceRestController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping
    public Agence create(@RequestBody Agence agence) { return agenceService.create(agence); }

    @GetMapping
    public List<Agence> findAll() { return agenceService.findAll(); }

    @GetMapping("/{id}")
    public Agence findById(@PathVariable Long id) { return agenceService.findById(id); }

    @PutMapping("/{id}")
    public Agence update(@PathVariable Long id, @RequestBody Agence agence) { return agenceService.update(id, agence); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { agenceService.deleteById(id); }
}
