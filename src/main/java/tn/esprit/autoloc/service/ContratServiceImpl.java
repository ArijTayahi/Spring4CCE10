package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.repository.IContratRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    public Contrat create(Contrat contrat) { return contratRepository.save(contrat); }
    public Contrat findById(Long id) { return contratRepository.findById(id).orElse(null); }
    public List<Contrat> findAll() { return contratRepository.findAll(); }
    public Contrat update(Long id, Contrat contrat) { contrat.setIdContrat(id); return contratRepository.save(contrat); }
    public void deleteById(Long id) { contratRepository.deleteById(id); }
}
