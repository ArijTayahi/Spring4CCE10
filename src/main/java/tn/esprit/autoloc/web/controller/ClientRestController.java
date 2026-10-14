package tn.esprit.autoloc.web.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    private final IClientService clientService;

    public ClientRestController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client create(@RequestBody Client client) { return clientService.create(client); }

    @GetMapping
    public List<Client> findAll() { return clientService.findAll(); }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) { return clientService.findById(id); }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) { return clientService.update(id, client); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { clientService.deleteById(id); }
}
