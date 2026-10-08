package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "paiement")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "contrat")
@EqualsAndHashCode(exclude = "contrat")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(nullable = false)
    private LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ModePaiement modePaiement;

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * Un paiement est rattaché à un contrat (côté propriétaire).
     * Hibernate génère automatiquement la colonne FK dans la table paiement
     * à partir du nom de l'attribut et de la PK référencée (ex. contrat_id_contrat).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Contrat contrat;
}
