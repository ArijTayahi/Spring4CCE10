package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    public Agence create(Agence agence) { return agenceRepository.save(agence); }
    public Agence findById(Long id) { return agenceRepository.findById(id).orElse(null); }
    public List<Agence> findAll() { return agenceRepository.findAll(); }
    public Agence update(Long id, Agence agence) { agence.setIdAgence(id); return agenceRepository.save(agence); }
    public void deleteById(Long id) { agenceRepository.deleteById(id); }
}
