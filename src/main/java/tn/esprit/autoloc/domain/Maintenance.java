package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * Une opération de maintenance concerne un véhicule précis (côté propriétaire).
     * Génère la colonne FK idVehicule dans la table maintenance.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehicule", nullable = false)
    private Vehicule vehicule;
}
