package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employe")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "agence")
@EqualsAndHashCode(exclude = "agence")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RoleEmploye role;

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * Un employé appartient à une agence (côté propriétaire).
     * Hibernate génère la colonne FK idAgence dans la table employe.
     * Pas de cascade : un employé survit à la suppression de son agence.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAgence")
    private Agence agence;
}
