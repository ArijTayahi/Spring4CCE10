package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicule")
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategorieVehicule categorie;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutVehicule statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgence")
    @JsonIgnoreProperties({"vehicules", "employes"})
    private Agence agence;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "vehicule_equipement",
        joinColumns        = @JoinColumn(name = "idVehicule"),
        inverseJoinColumns = @JoinColumn(name = "idEquipement")
    )
    @JsonIgnoreProperties("vehicules")
    private Set<Equipement> equipements = new HashSet<>();

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("vehicule")
    private List<Maintenance> maintenances = new ArrayList<>();

    public Vehicule() {}

    public Vehicule(Long idVehicule, String immatriculation, String marque, String modele,
                    CategorieVehicule categorie, BigDecimal tarifJournalier, StatutVehicule statut,
                    Agence agence, Set<Equipement> equipements, List<Maintenance> maintenances) {
        this.idVehicule = idVehicule;
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
        this.tarifJournalier = tarifJournalier;
        this.statut = statut;
        this.agence = agence;
        this.equipements = equipements;
        this.maintenances = maintenances;
    }

    public Long getIdVehicule() { return idVehicule; }
    public void setIdVehicule(Long idVehicule) { this.idVehicule = idVehicule; }
    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }
    public CategorieVehicule getCategorie() { return categorie; }
    public void setCategorie(CategorieVehicule categorie) { this.categorie = categorie; }
    public BigDecimal getTarifJournalier() { return tarifJournalier; }
    public void setTarifJournalier(BigDecimal tarifJournalier) { this.tarifJournalier = tarifJournalier; }
    public StatutVehicule getStatut() { return statut; }
    public void setStatut(StatutVehicule statut) { this.statut = statut; }
    public Agence getAgence() { return agence; }
    public void setAgence(Agence agence) { this.agence = agence; }
    public Set<Equipement> getEquipements() { return equipements; }
    public void setEquipements(Set<Equipement> equipements) { this.equipements = equipements; }
    public List<Maintenance> getMaintenances() { return maintenances; }
    public void setMaintenances(List<Maintenance> maintenances) { this.maintenances = maintenances; }
}
