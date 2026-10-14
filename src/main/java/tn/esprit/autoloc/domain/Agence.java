package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agence")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 150)
    private String adresse;

    @Column(length = 20)
    private String telephone;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("agence")
    private List<Vehicule> vehicules = new ArrayList<>();

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("agence")
    private List<Employe> employes = new ArrayList<>();

    public Agence() {}

    public Agence(Long idAgence, String nom, String ville, String adresse, String telephone, List<Vehicule> vehicules, List<Employe> employes) {
        this.idAgence = idAgence;
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
        this.telephone = telephone;
        this.vehicules = vehicules;
        this.employes = employes;
    }

    public Long getIdAgence() { return idAgence; }
    public void setIdAgence(Long idAgence) { this.idAgence = idAgence; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public List<Vehicule> getVehicules() { return vehicules; }
    public void setVehicules(List<Vehicule> vehicules) { this.vehicules = vehicules; }
    public List<Employe> getEmployes() { return employes; }
    public void setEmployes(List<Employe> employes) { this.employes = employes; }
}
