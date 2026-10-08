package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agence")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"vehicules", "employes"})
@EqualsAndHashCode(exclude = {"vehicules", "employes"})
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 150)
    private String adresse;

    @Column(length = 20)
    private String telephone;

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * Une agence possède plusieurs véhicules (côté inverse).
     * Pas de cascade : un véhicule survit à la suppression de son agence.
     */
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private List<Vehicule> vehicules = new ArrayList<>();

    /**
     * Une agence emploie plusieurs employés (côté inverse).
     * Pas de cascade : un employé survit à la suppression de son agence.
     */
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private List<Employe> employes = new ArrayList<>();
}
