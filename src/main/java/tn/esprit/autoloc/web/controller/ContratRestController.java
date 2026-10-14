package tn.esprit.autoloc.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
public class ContratRestController {

    private final IContratService contratService;

    public ContratRestController(IContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping
    public Contrat create(@RequestBody Contrat contrat) { return contratService.create(contrat); }

    @GetMapping
    public List<Contrat> findAll() { return contratService.findAll(); }

    @GetMapping("/{id}")
    public Contrat findById(@PathVariable Long id) { return contratService.findById(id); }

    @PutMapping("/{id}")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat contrat) { return contratService.update(id, contrat); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { contratService.deleteById(id); }
}
