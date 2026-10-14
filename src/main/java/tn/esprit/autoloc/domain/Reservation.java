package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"client", "vehicule", "employe", "contrat"})
@EqualsAndHashCode(exclude = {"client", "vehicule", "employe", "contrat"})
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
}
