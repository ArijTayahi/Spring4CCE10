package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {

    Vehicule addVehicule(Vehicule vehicule);

    Vehicule updateVehicule(Vehicule vehicule);

    void deleteVehicule(Long id);

    Vehicule getVehiculeById(Long id);

    List<Vehicule> getAllVehicules();
}
