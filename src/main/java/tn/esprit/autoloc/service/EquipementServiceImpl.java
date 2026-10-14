package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    public Equipement create(Equipement equipement) { return equipementRepository.save(equipement); }
    public Equipement findById(Long id) { return equipementRepository.findById(id).orElse(null); }
    public List<Equipement> findAll() { return equipementRepository.findAll(); }
    public Equipement update(Long id, Equipement equipement) { equipement.setIdEquipement(id); return equipementRepository.save(equipement); }
    public void deleteById(Long id) { equipementRepository.deleteById(id); }
}
