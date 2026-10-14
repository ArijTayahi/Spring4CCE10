package tn.esprit.autoloc.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EmployeRestController {

    private final IEmployeService employeService;

    public EmployeRestController(IEmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping
    public Employe create(@RequestBody Employe employe) { return employeService.create(employe); }

    @GetMapping
    public List<Employe> findAll() { return employeService.findAll(); }

    @GetMapping("/{id}")
    public Employe findById(@PathVariable Long id) { return employeService.findById(id); }

    @PutMapping("/{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) { return employeService.update(id, employe); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { employeService.deleteById(id); }
}
