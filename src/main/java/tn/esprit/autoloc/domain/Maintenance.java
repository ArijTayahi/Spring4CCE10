package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "vehicule")
@EqualsAndHashCode(exclude = "vehicule")
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
     * Hibernate génère la colonne FK idVehicule dans la table maintenance.
     * Pas de cascade depuis ce côté : le véhicule gère la cascade PERSIST.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVehicule", nullable = false)
    private Vehicule vehicule;
}
