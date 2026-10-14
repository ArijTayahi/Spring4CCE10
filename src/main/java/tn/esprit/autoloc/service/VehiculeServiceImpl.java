package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    public VehiculeServiceImpl(IVehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Vehicule create(Vehicule vehicule) { return vehiculeRepository.save(vehicule); }

    @Override
    public Vehicule findById(Long id) { return vehiculeRepository.findById(id).orElse(null); }

    @Override
    public List<Vehicule> findAll() { return vehiculeRepository.findAll(); }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) { vehicule.setIdVehicule(id); return vehiculeRepository.save(vehicule); }

    @Override
    public void deleteById(Long id) { vehiculeRepository.deleteById(id); }
}
