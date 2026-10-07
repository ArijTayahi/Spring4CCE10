-- ============================================================
-- AutoLoc – Ajout des colonnes FK et contraintes de relation
-- Atelier 2 : Associations JPA
-- Exécuter dans phpMyAdmin > autoloc_db > onglet SQL
-- ============================================================

USE autoloc_db;

-- Désactiver temporairement les vérifications FK pour éviter
-- les erreurs d'ordre d'exécution
SET FOREIGN_KEY_CHECKS = 0;

-- ── 1. vehicule ← agence ──────────────────────────────────
ALTER TABLE vehicule
    ADD COLUMN idAgence BIGINT NOT NULL DEFAULT 0;

ALTER TABLE vehicule
    ADD CONSTRAINT fk_vehicule_agence
        FOREIGN KEY (idAgence) REFERENCES agence (idAgence);

-- ── 2. employe ← agence ───────────────────────────────────
ALTER TABLE employe
    ADD COLUMN idAgence BIGINT NOT NULL DEFAULT 0;

ALTER TABLE employe
    ADD CONSTRAINT fk_employe_agence
        FOREIGN KEY (idAgence) REFERENCES agence (idAgence);

-- ── 3. reservation ← client ───────────────────────────────
ALTER TABLE reservation
    ADD COLUMN idClient BIGINT NOT NULL DEFAULT 0;

ALTER TABLE reservation
    ADD CONSTRAINT fk_reservation_client
        FOREIGN KEY (idClient) REFERENCES client (idClient);

-- ── 4. reservation ← vehicule ─────────────────────────────
ALTER TABLE reservation
    ADD COLUMN idVehicule BIGINT NOT NULL DEFAULT 0;

ALTER TABLE reservation
    ADD CONSTRAINT fk_reservation_vehicule
        FOREIGN KEY (idVehicule) REFERENCES vehicule (idVehicule);

-- ── 5. reservation ← employe (nullable) ───────────────────
ALTER TABLE reservation
    ADD COLUMN idEmploye BIGINT NULL;

ALTER TABLE reservation
    ADD CONSTRAINT fk_reservation_employe
        FOREIGN KEY (idEmploye) REFERENCES employe (idEmploye);

-- ── 6. contrat ← reservation (OneToOne, unique) ───────────
ALTER TABLE contrat
    ADD COLUMN idReservation BIGINT NOT NULL DEFAULT 0;

ALTER TABLE contrat
    ADD CONSTRAINT uq_contrat_reservation UNIQUE (idReservation);

ALTER TABLE contrat
    ADD CONSTRAINT fk_contrat_reservation
        FOREIGN KEY (idReservation) REFERENCES reservation (idReservation);

-- ── 7. paiement ← contrat (OneToOne, unique) ──────────────
ALTER TABLE paiement
    ADD COLUMN idContrat BIGINT NOT NULL DEFAULT 0;

ALTER TABLE paiement
    ADD CONSTRAINT uq_paiement_contrat UNIQUE (idContrat);

ALTER TABLE paiement
    ADD CONSTRAINT fk_paiement_contrat
        FOREIGN KEY (idContrat) REFERENCES contrat (idContrat);

-- ── 8. maintenance ← vehicule ─────────────────────────────
ALTER TABLE maintenance
    ADD COLUMN idVehicule BIGINT NOT NULL DEFAULT 0;

ALTER TABLE maintenance
    ADD CONSTRAINT fk_maintenance_vehicule
        FOREIGN KEY (idVehicule) REFERENCES vehicule (idVehicule);

-- ── 9. Table de jointure ManyToMany vehicule ↔ equipement ─
CREATE TABLE IF NOT EXISTS vehicule_equipement (
    idVehicule   BIGINT NOT NULL,
    idEquipement BIGINT NOT NULL,
    PRIMARY KEY (idVehicule, idEquipement),
    CONSTRAINT fk_ve_vehicule
        FOREIGN KEY (idVehicule)   REFERENCES vehicule   (idVehicule),
    CONSTRAINT fk_ve_equipement
        FOREIGN KEY (idEquipement) REFERENCES equipement (idEquipement)
);

-- Réactiver les vérifications FK
SET FOREIGN_KEY_CHECKS = 1;
