package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;

@Service
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    public ContratServiceImpl(IContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public Contrat create(Contrat contrat) { return contratRepository.save(contrat); }

    @Override
    public Contrat findById(Long id) { return contratRepository.findById(id).orElse(null); }

    @Override
    public List<Contrat> findAll() { return contratRepository.findAll(); }

    @Override
    public Contrat update(Long id, Contrat contrat) { contrat.setIdContrat(id); return contratRepository.save(contrat); }

    @Override
    public void deleteById(Long id) { contratRepository.deleteById(id); }
}
