package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "employe")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleEmploye role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgence")
    @JsonIgnoreProperties({"vehicules", "employes"})
    private Agence agence;

    public Employe() {}

    public Employe(Long idEmploye, String nom, String prenom, RoleEmploye role, Agence agence) {
        this.idEmploye = idEmploye;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.agence = agence;
    }

    public Long getIdEmploye() { return idEmploye; }
    public void setIdEmploye(Long idEmploye) { this.idEmploye = idEmploye; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public RoleEmploye getRole() { return role; }
    public void setRole(RoleEmploye role) { this.role = role; }
    public Agence getAgence() { return agence; }
    public void setAgence(Agence agence) { this.agence = agence; }
}
