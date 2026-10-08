package tn.esprit.autoloc.domain;

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

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * La réservation est faite par un client (côté propriétaire).
     * Génère la colonne FK idClient dans la table reservation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;

    /**
     * La réservation concerne un véhicule (côté propriétaire).
     * Génère la colonne FK idVehicule dans la table reservation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehicule", nullable = false)
    private Vehicule vehicule;

    /**
     * La réservation est traitée par un employé (côté propriétaire, optionnel).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmploye")
    private Employe employe;

    /**
     * Un contrat peut être établi pour cette réservation (côté inverse).
     * Le FK idReservation se trouve dans la table contrat (côté propriétaire = Contrat).
     * mappedBy pointe vers l'attribut "reservation" déclaré dans Contrat.
     */
    @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY)
    private Contrat contrat;
}
