package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client create(Client client) { return clientRepository.save(client); }

    @Override
    public Client findById(Long id) { return clientRepository.findById(id).orElse(null); }

    @Override
    public List<Client> findAll() { return clientRepository.findAll(); }

    @Override
    public Client update(Long id, Client client) { client.setIdClient(id); return clientRepository.save(client); }

    @Override
    public void deleteById(Long id) { clientRepository.deleteById(id); }
}
