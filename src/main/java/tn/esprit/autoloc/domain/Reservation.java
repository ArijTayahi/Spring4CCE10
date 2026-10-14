package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutReservation statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    @JsonIgnoreProperties("reservations")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehicule", nullable = false)
    @JsonIgnoreProperties({"equipements", "maintenances", "agence"})
    private Vehicule vehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmploye")
    @JsonIgnoreProperties("agence")
    private Employe employe;

    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"reservation", "paiements"})
    private Contrat contrat;

    public Reservation() {}

    public Reservation(Long idReservation, LocalDate dateDebut, LocalDate dateFin,
                       StatutReservation statut, Client client, Vehicule vehicule,
                       Employe employe, Contrat contrat) {
        this.idReservation = idReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.client = client;
        this.vehicule = vehicule;
        this.employe = employe;
        this.contrat = contrat;
    }

    public Long getIdReservation() { return idReservation; }
    public void setIdReservation(Long idReservation) { this.idReservation = idReservation; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Vehicule getVehicule() { return vehicule; }
    public void setVehicule(Vehicule vehicule) { this.vehicule = vehicule; }
    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }
    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) { this.contrat = contrat; }
}
