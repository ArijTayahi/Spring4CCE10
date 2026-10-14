package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    public AgenceServiceImpl(IAgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Agence create(Agence agence) { return agenceRepository.save(agence); }

    @Override
    public Agence findById(Long id) { return agenceRepository.findById(id).orElse(null); }

    @Override
    public List<Agence> findAll() { return agenceRepository.findAll(); }

    @Override
    public Agence update(Long id, Agence agence) { agence.setIdAgence(id); return agenceRepository.save(agence); }

    @Override
    public void deleteById(Long id) { agenceRepository.deleteById(id); }
}
