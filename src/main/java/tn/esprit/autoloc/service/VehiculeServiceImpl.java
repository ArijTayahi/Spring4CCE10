package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
        log.info("Ajout d'un véhicule : {}", vehicule.getImmatriculation());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Vehicule vehicule) {
        log.info("Mise à jour du véhicule id={}", vehicule.getIdVehicule());
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteVehicule(Long id) {
        log.info("Suppression du véhicule id={}", id);
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule getVehiculeById(Long id) {
        log.info("Récupération du véhicule id={}", id);
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable, id=" + id));
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        log.info("Récupération de tous les véhicules");
        return vehiculeRepository.findAll();
    }
}
