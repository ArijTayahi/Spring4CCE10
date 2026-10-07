-- ============================================================
-- Script : Ajout des clés étrangères (FK) - Atelier 2 AutoLoc
-- À exécuter dans phpMyAdmin > onglet SQL sur la DB autoloc_db
-- ============================================================

USE autoloc_db;

-- 1. vehicule → agence
ALTER TABLE vehicule
    ADD COLUMN IF NOT EXISTS idAgence BIGINT NOT NULL,
    ADD CONSTRAINT fk_vehicule_agence
        FOREIGN KEY (idAgence) REFERENCES agence(idAgence);

-- 2. employe → agence
ALTER TABLE employe
    ADD COLUMN IF NOT EXISTS idAgence BIGINT NOT NULL,
    ADD CONSTRAINT fk_employe_agence
        FOREIGN KEY (idAgence) REFERENCES agence(idAgence);

-- 3. reservation → client
ALTER TABLE reservation
    ADD COLUMN IF NOT EXISTS idClient BIGINT NOT NULL,
    ADD CONSTRAINT fk_reservation_client
        FOREIGN KEY (idClient) REFERENCES client(idClient);

-- 4. reservation → vehicule
ALTER TABLE reservation
    ADD COLUMN IF NOT EXISTS idVehicule BIGINT NOT NULL,
    ADD CONSTRAINT fk_reservation_vehicule
        FOREIGN KEY (idVehicule) REFERENCES vehicule(idVehicule);

-- 5. reservation → employe (nullable : une réservation peut ne pas encore avoir d'employé assigné)
ALTER TABLE reservation
    ADD COLUMN IF NOT EXISTS idEmploye BIGINT NULL,
    ADD CONSTRAINT fk_reservation_employe
        FOREIGN KEY (idEmploye) REFERENCES employe(idEmploye);

-- 6. contrat → reservation (OneToOne, unique)
ALTER TABLE contrat
    ADD COLUMN IF NOT EXISTS idReservation BIGINT NOT NULL UNIQUE,
    ADD CONSTRAINT fk_contrat_reservation
        FOREIGN KEY (idReservation) REFERENCES reservation(idReservation);

-- 7. paiement → contrat (OneToOne, unique)
ALTER TABLE paiement
    ADD COLUMN IF NOT EXISTS idContrat BIGINT NOT NULL UNIQUE,
    ADD CONSTRAINT fk_paiement_contrat
        FOREIGN KEY (idContrat) REFERENCES contrat(idContrat);

-- 8. maintenance → vehicule
ALTER TABLE maintenance
    ADD COLUMN IF NOT EXISTS idVehicule BIGINT NOT NULL,
    ADD CONSTRAINT fk_maintenance_vehicule
        FOREIGN KEY (idVehicule) REFERENCES vehicule(idVehicule);

-- 9. Table de jointure ManyToMany : vehicule <-> equipement
CREATE TABLE IF NOT EXISTS vehicule_equipement (
    idVehicule   BIGINT NOT NULL,
    idEquipement BIGINT NOT NULL,
    PRIMARY KEY (idVehicule, idEquipement),
    CONSTRAINT fk_ve_vehicule   FOREIGN KEY (idVehicule)   REFERENCES vehicule(idVehicule),
    CONSTRAINT fk_ve_equipement FOREIGN KEY (idEquipement) REFERENCES equipement(idEquipement)
);
