package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    public Vehicule create(Vehicule vehicule) { return vehiculeRepository.save(vehicule); }
    public Vehicule findById(Long id) { return vehiculeRepository.findById(id).orElse(null); }
    public List<Vehicule> findAll() { return vehiculeRepository.findAll(); }
    public Vehicule update(Long id, Vehicule vehicule) { vehicule.setIdVehicule(id); return vehiculeRepository.save(vehicule); }
    public void deleteById(Long id) { vehiculeRepository.deleteById(id); }
}
