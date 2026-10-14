package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipement")
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    @Column(nullable = false, unique = true, length = 100)
    private String libelle;

    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("equipements")
    private Set<Vehicule> vehicules = new HashSet<>();

    public Equipement() {}

    public Equipement(Long idEquipement, String libelle, Set<Vehicule> vehicules) {
        this.idEquipement = idEquipement;
        this.libelle = libelle;
        this.vehicules = vehicules;
    }

    public Long getIdEquipement() { return idEquipement; }
    public void setIdEquipement(Long idEquipement) { this.idEquipement = idEquipement; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public Set<Vehicule> getVehicules() { return vehicules; }
    public void setVehicules(Set<Vehicule> vehicules) { this.vehicules = vehicules; }
}
