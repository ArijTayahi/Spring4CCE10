-- ============================================================
-- AutoLoc – Création complète du schéma avec relations FK
-- phpMyAdmin > autoloc_db > onglet SQL > coller > Exécuter
-- ============================================================

USE autoloc_db;

SET FOREIGN_KEY_CHECKS = 0;

-- ── 1. agence ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS agence (
    idAgence  BIGINT       NOT NULL AUTO_INCREMENT,
    nom       VARCHAR(100) NOT NULL,
    ville     VARCHAR(50)  NOT NULL,
    adresse   VARCHAR(150) NOT NULL,
    telephone VARCHAR(20),
    PRIMARY KEY (idAgence)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 2. client ─────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS client (
    idClient        BIGINT       NOT NULL AUTO_INCREMENT,
    nom             VARCHAR(50)  NOT NULL,
    prenom          VARCHAR(50)  NOT NULL,
    email           VARCHAR(100) NOT NULL,
    telephone       VARCHAR(20),
    numPermis       VARCHAR(30)  NOT NULL,
    dateInscription DATE         NOT NULL,
    PRIMARY KEY (idClient),
    UNIQUE KEY uq_client_email    (email),
    UNIQUE KEY uq_client_numpermis (numPermis)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 3. equipement ─────────────────────────────────────────
CREATE TABLE IF NOT EXISTS equipement (
    idEquipement BIGINT       NOT NULL AUTO_INCREMENT,
    libelle      VARCHAR(100) NOT NULL,
    PRIMARY KEY (idEquipement),
    UNIQUE KEY uq_equipement_libelle (libelle)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 4. vehicule (FK → agence) ─────────────────────────────
CREATE TABLE IF NOT EXISTS vehicule (
    idVehicule      BIGINT        NOT NULL AUTO_INCREMENT,
    immatriculation VARCHAR(20)   NOT NULL,
    marque          VARCHAR(50)   NOT NULL,
    modele          VARCHAR(50)   NOT NULL,
    categorie       ENUM('CITADINE','BERLINE','SUV','UTILITAIRE') NOT NULL,
    tarifJournalier DECIMAL(10,2) NOT NULL,
    statut          ENUM('DISPONIBLE','LOUE','MAINTENANCE') NOT NULL,
    idAgence        BIGINT        NOT NULL,
    PRIMARY KEY (idVehicule),
    UNIQUE KEY uq_vehicule_immat (immatriculation),
    CONSTRAINT fk_vehicule_agence FOREIGN KEY (idAgence) REFERENCES agence (idAgence)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 5. employe (FK → agence) ──────────────────────────────
CREATE TABLE IF NOT EXISTS employe (
    idEmploye BIGINT      NOT NULL AUTO_INCREMENT,
    nom       VARCHAR(50) NOT NULL,
    prenom    VARCHAR(50) NOT NULL,
    role      ENUM('AGENT','MANAGER') NOT NULL,
    idAgence  BIGINT      NOT NULL,
    PRIMARY KEY (idEmploye),
    CONSTRAINT fk_employe_agence FOREIGN KEY (idAgence) REFERENCES agence (idAgence)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 6. maintenance (FK → vehicule) ────────────────────────
CREATE TABLE IF NOT EXISTS maintenance (
    idMaintenance BIGINT       NOT NULL AUTO_INCREMENT,
    dateDebut     DATE         NOT NULL,
    dateFin       DATE,
    description   VARCHAR(255),
    idVehicule    BIGINT       NOT NULL,
    PRIMARY KEY (idMaintenance),
    CONSTRAINT fk_maintenance_vehicule FOREIGN KEY (idVehicule) REFERENCES vehicule (idVehicule)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 7. vehicule_equipement (ManyToMany) ───────────────────
CREATE TABLE IF NOT EXISTS vehicule_equipement (
    idVehicule   BIGINT NOT NULL,
    idEquipement BIGINT NOT NULL,
    PRIMARY KEY (idVehicule, idEquipement),
    CONSTRAINT fk_ve_vehicule   FOREIGN KEY (idVehicule)   REFERENCES vehicule   (idVehicule),
    CONSTRAINT fk_ve_equipement FOREIGN KEY (idEquipement) REFERENCES equipement (idEquipement)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 8. reservation (FK → client, vehicule, employe) ───────
CREATE TABLE IF NOT EXISTS reservation (
    idReservation BIGINT NOT NULL AUTO_INCREMENT,
    dateDebut     DATE   NOT NULL,
    dateFin       DATE   NOT NULL,
    statut        ENUM('EN_ATTENTE','CONFIRMEE','ANNULEE','TERMINEE') NOT NULL,
    idClient      BIGINT NOT NULL,
    idVehicule    BIGINT NOT NULL,
    idEmploye     BIGINT,
    PRIMARY KEY (idReservation),
    CONSTRAINT fk_reservation_client   FOREIGN KEY (idClient)   REFERENCES client   (idClient),
    CONSTRAINT fk_reservation_vehicule FOREIGN KEY (idVehicule) REFERENCES vehicule (idVehicule),
    CONSTRAINT fk_reservation_employe  FOREIGN KEY (idEmploye)  REFERENCES employe  (idEmploye)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 9. contrat (FK → reservation, OneToOne) ───────────────
CREATE TABLE IF NOT EXISTS contrat (
    idContrat     BIGINT        NOT NULL AUTO_INCREMENT,
    dateSignature DATE          NOT NULL,
    montantTotal  DECIMAL(10,2) NOT NULL,
    valide        TINYINT(1)    NOT NULL,
    idReservation BIGINT        NOT NULL,
    PRIMARY KEY (idContrat),
    UNIQUE KEY uq_contrat_reservation (idReservation),
    CONSTRAINT fk_contrat_reservation FOREIGN KEY (idReservation) REFERENCES reservation (idReservation)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ── 10. paiement (FK → contrat, OneToOne) ─────────────────
CREATE TABLE IF NOT EXISTS paiement (
    idPaiement   BIGINT        NOT NULL AUTO_INCREMENT,
    montant      DECIMAL(10,2) NOT NULL,
    datePaiement DATE          NOT NULL,
    modePaiement ENUM('CARTE','ESPECES','VIREMENT') NOT NULL,
    idContrat    BIGINT        NOT NULL,
    PRIMARY KEY (idPaiement),
    UNIQUE KEY uq_paiement_contrat (idContrat),
    CONSTRAINT fk_paiement_contrat FOREIGN KEY (idContrat) REFERENCES contrat (idContrat)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
