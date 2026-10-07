package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "vehicule")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"equipements", "maintenances"})
@EqualsAndHashCode(exclude = {"equipements", "maintenances"})
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

    // ── Relations ──────────────────────────────────────────────────────────

    /** Un véhicule appartient à une agence (côté propriétaire). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgence", nullable = false)
    private Agence agence;

    /**
     * Un véhicule peut être équipé de plusieurs équipements optionnels.
     * Côté propriétaire : la table de jointure est gérée ici.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "vehicule_equipement",
        joinColumns        = @JoinColumn(name = "idVehicule"),
        inverseJoinColumns = @JoinColumn(name = "idEquipement")
    )
    private List<Equipement> equipements;

    /** Un véhicule peut subir plusieurs opérations de maintenance. */
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Maintenance> maintenances;
}
