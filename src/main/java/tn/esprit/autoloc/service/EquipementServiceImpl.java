package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.repository.IEquipementRepository;

import java.util.List;

@Service
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    public EquipementServiceImpl(IEquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public Equipement create(Equipement equipement) { return equipementRepository.save(equipement); }

    @Override
    public Equipement findById(Long id) { return equipementRepository.findById(id).orElse(null); }

    @Override
    public List<Equipement> findAll() { return equipementRepository.findAll(); }

    @Override
    public Equipement update(Long id, Equipement equipement) { equipement.setIdEquipement(id); return equipementRepository.save(equipement); }

    @Override
    public void deleteById(Long id) { equipementRepository.deleteById(id); }
}
