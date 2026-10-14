package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrat")
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idReservation", nullable = false, unique = true)
    @JsonIgnoreProperties({"contrat", "client", "vehicule", "employe"})
    private Reservation reservation;

    @OneToMany(mappedBy = "contrat",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    @JsonIgnoreProperties("contrat")
    private List<Paiement> paiements = new ArrayList<>();

    public Contrat() {}

    public Contrat(Long idContrat, LocalDate dateSignature, BigDecimal montantTotal,
                   boolean valide, Reservation reservation, List<Paiement> paiements) {
        this.idContrat = idContrat;
        this.dateSignature = dateSignature;
        this.montantTotal = montantTotal;
        this.valide = valide;
        this.reservation = reservation;
        this.paiements = paiements;
    }

    public Long getIdContrat() { return idContrat; }
    public void setIdContrat(Long idContrat) { this.idContrat = idContrat; }
    public LocalDate getDateSignature() { return dateSignature; }
    public void setDateSignature(LocalDate dateSignature) { this.dateSignature = dateSignature; }
    public BigDecimal getMontantTotal() { return montantTotal; }
    public void setMontantTotal(BigDecimal montantTotal) { this.montantTotal = montantTotal; }
    public boolean isValide() { return valide; }
    public void setValide(boolean valide) { this.valide = valide; }
    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }
    public List<Paiement> getPaiements() { return paiements; }
    public void setPaiements(List<Paiement> paiements) { this.paiements = paiements; }
}
