package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicule")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"agence", "equipements", "maintenances"})
@EqualsAndHashCode(exclude = {"agence", "equipements", "maintenances"})
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

    /**
     * Un véhicule appartient à une agence (côté propriétaire).
     * Hibernate génère la colonne FK idAgence dans la table vehicule.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgence")
    private Agence agence;

    /**
     * Un véhicule peut être équipé de plusieurs équipements (côté propriétaire).
     * Utilisation d'un Set : les équipements sont partagés, l'ordre n'est pas garanti.
     * Pas de cascade : les équipements sont des entités indépendantes et réutilisables.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "vehicule_equipement",
        joinColumns        = @JoinColumn(name = "idVehicule"),
        inverseJoinColumns = @JoinColumn(name = "idEquipement")
    )
    private Set<Equipement> equipements = new HashSet<>();

    /**
     * Un véhicule peut subir plusieurs opérations de maintenance (côté inverse).
     * cascade = PERSIST : créer un véhicule peut créer ses maintenances associées.
     * Pas de REMOVE : une maintenance a une valeur historique et ne doit pas être
     * supprimée en cascade.
     */
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Maintenance> maintenances = new ArrayList<>();
}
