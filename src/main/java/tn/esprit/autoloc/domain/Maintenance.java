package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaintenance;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column
    private LocalDate dateFin;

    @Column(length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehicule", nullable = false)
    @JsonIgnoreProperties({"equipements", "maintenances", "agence"})
    private Vehicule vehicule;

    public Maintenance() {}

    public Maintenance(Long idMaintenance, LocalDate dateDebut, LocalDate dateFin, String description, Vehicule vehicule) {
        this.idMaintenance = idMaintenance;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.vehicule = vehicule;
    }

    public Long getIdMaintenance() { return idMaintenance; }
    public void setIdMaintenance(Long idMaintenance) { this.idMaintenance = idMaintenance; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Vehicule getVehicule() { return vehicule; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }
}
