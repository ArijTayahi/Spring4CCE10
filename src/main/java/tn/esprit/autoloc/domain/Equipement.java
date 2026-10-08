package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "vehicules")
@EqualsAndHashCode(exclude = "vehicules")
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipement;

    @Column(nullable = false, unique = true, length = 100)
    private String libelle;

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * Un équipement peut être associé à plusieurs véhicules (côté inverse).
     * mappedBy pointe vers l'attribut "equipements" déclaré dans Vehicule.
     * Pas de cascade : les équipements sont partagés entre véhicules.
     */
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    private Set<Vehicule> vehicules = new HashSet<>();
}
