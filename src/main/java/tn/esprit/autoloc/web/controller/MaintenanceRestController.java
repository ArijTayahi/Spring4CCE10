package tn.esprit.autoloc.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceRestController {

    private final IMaintenanceService maintenanceService;

    public MaintenanceRestController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public Maintenance create(@RequestBody Maintenance maintenance) { return maintenanceService.create(maintenance); }

    @GetMapping
    public List<Maintenance> findAll() { return maintenanceService.findAll(); }

    @GetMapping("/{id}")
    public Maintenance findById(@PathVariable Long id) { return maintenanceService.findById(id); }

    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) { return maintenanceService.update(id, maintenance); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { maintenanceService.deleteById(id); }
}
