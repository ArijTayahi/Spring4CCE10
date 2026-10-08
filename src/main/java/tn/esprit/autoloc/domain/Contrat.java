package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"paiements", "reservation"})
@EqualsAndHashCode(exclude = {"paiements", "reservation"})
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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idReservation", nullable = false, unique = true)
    private Reservation reservation;

    /**
     * Un contrat regroupe plusieurs paiements (côté inverse, bidirectionnel).
     * mappedBy pointe vers l'attribut "contrat" déclaré dans Paiement.
     * cascade = ALL : persister/supprimer le contrat propage l'opération à ses paiements.
     * orphanRemoval : retirer un paiement de la liste le supprime en base.
     */
    @OneToMany(mappedBy = "contrat",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Paiement> paiements = new ArrayList<>();
}
