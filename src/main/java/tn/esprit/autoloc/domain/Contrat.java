package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contrat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = "paiement")
@EqualsAndHashCode(exclude = "paiement")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    @Column(nullable = false)
    private LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(nullable = false)
    private boolean valide;

    // ── Relations ──────────────────────────────────────────────────────────

    /**
     * Un contrat est lié à une réservation (côté propriétaire).
     * Génère la colonne FK idReservation dans la table contrat.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idReservation", nullable = false, unique = true)
    private Reservation reservation;

    /**
     * Un contrat est associé à un paiement unique (côté inverse).
     * Le FK se trouve dans la table paiement.
     */
    @OneToOne(mappedBy = "contrat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Paiement paiement;
}
