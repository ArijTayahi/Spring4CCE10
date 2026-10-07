package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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
     * La table de jointure vehicule_equipement est gérée par Vehicule.
     */
    @ManyToMany(mappedBy = "equipements", fetch = FetchType.LAZY)
    private List<Vehicule> vehicules;
}
