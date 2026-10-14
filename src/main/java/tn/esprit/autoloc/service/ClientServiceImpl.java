package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public Client create(Client client) { return clientRepository.save(client); }
    public Client findById(Long id) { return clientRepository.findById(id).orElse(null); }
    public List<Client> findAll() { return clientRepository.findAll(); }
    public Client update(Long id, Client client) { client.setIdClient(id); return clientRepository.save(client); }
    public void deleteById(Long id) { clientRepository.deleteById(id); }
}
