-- ============================================================
-- AutoLoc – Schéma complet avec toutes les relations
-- À exécuter après DROP + CREATE de autoloc_db
-- phpMyAdmin > SQL (sur le serveur, PAS sur autoloc_db)
-- ============================================================

DROP DATABASE IF EXISTS autoloc_db;
CREATE DATABASE autoloc_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE autoloc_db;

SET FOREIGN_KEY_CHECKS = 0;

-- ── Tables sans dépendances ────────────────────────────────

CREATE TABLE agence (
    idAgence  BIGINT       AUTO_INCREMENT PRIMARY KEY,
    nom       VARCHAR(100) NOT NULL,
    ville     VARCHAR(50)  NOT NULL,
    adresse   VARCHAR(150) NOT NULL,
    telephone VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE client (
    idClient        BIGINT       AUTO_INCREMENT PRIMARY KEY,
    nom             VARCHAR(50)  NOT NULL,
    prenom          VARCHAR(50)  NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    telephone       VARCHAR(20),
    numPermis       VARCHAR(30)  NOT NULL UNIQUE,
    dateInscription DATE         NOT NULL
) ENGINE=InnoDB;

CREATE TABLE equipement (
    idEquipement BIGINT       AUTO_INCREMENT PRIMARY KEY,
    libelle      VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- ── Tables dépendant de agence ─────────────────────────────

CREATE TABLE vehicule (
    idVehicule      BIGINT         AUTO_INCREMENT PRIMARY KEY,
    immatriculation VARCHAR(20)    NOT NULL UNIQUE,
    marque          VARCHAR(50)    NOT NULL,
    modele          VARCHAR(50)    NOT NULL,
    categorie       ENUM('CITADINE','BERLINE','SUV','UTILITAIRE') NOT NULL,
    tarifJournalier DECIMAL(10,2)  NOT NULL,
    statut          ENUM('DISPONIBLE','LOUE','MAINTENANCE') NOT NULL,
    idAgence        BIGINT         NOT NULL,
    CONSTRAINT fk_vehicule_agence FOREIGN KEY (idAgence) REFERENCES agence (idAgence)
) ENGINE=InnoDB;

CREATE TABLE employe (
    idEmploye BIGINT      AUTO_INCREMENT PRIMARY KEY,
    nom       VARCHAR(50) NOT NULL,
    prenom    VARCHAR(50) NOT NULL,
    role      ENUM('AGENT','MANAGER') NOT NULL,
    idAgence  BIGINT      NOT NULL,
    CONSTRAINT fk_employe_agence FOREIGN KEY (idAgence) REFERENCES agence (idAgence)
) ENGINE=InnoDB;

-- ── maintenance dépend de vehicule ────────────────────────

CREATE TABLE maintenance (
    idMaintenance BIGINT       AUTO_INCREMENT PRIMARY KEY,
    dateDebut     DATE         NOT NULL,
    dateFin       DATE         NULL,
    description   VARCHAR(255),
    idVehicule    BIGINT       NOT NULL,
    CONSTRAINT fk_maintenance_vehicule FOREIGN KEY (idVehicule) REFERENCES vehicule (idVehicule)
) ENGINE=InnoDB;

-- ── vehicule_equipement : ManyToMany ──────────────────────

CREATE TABLE vehicule_equipement (
    idVehicule   BIGINT NOT NULL,
    idEquipement BIGINT NOT NULL,
    PRIMARY KEY (idVehicule, idEquipement),
    CONSTRAINT fk_ve_vehicule   FOREIGN KEY (idVehicule)   REFERENCES vehicule   (idVehicule),
    CONSTRAINT fk_ve_equipement FOREIGN KEY (idEquipement) REFERENCES equipement (idEquipement)
) ENGINE=InnoDB;

-- ── reservation dépend de client, vehicule, employe ───────

CREATE TABLE reservation (
    idReservation BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateDebut     DATE   NOT NULL,
    dateFin       DATE   NOT NULL,
    statut        ENUM('EN_ATTENTE','CONFIRMEE','ANNULEE','TERMINEE') NOT NULL,
    idClient      BIGINT NOT NULL,
    idVehicule    BIGINT NOT NULL,
    idEmploye     BIGINT NULL,
    CONSTRAINT fk_reservation_client   FOREIGN KEY (idClient)   REFERENCES client   (idClient),
    CONSTRAINT fk_reservation_vehicule FOREIGN KEY (idVehicule) REFERENCES vehicule (idVehicule),
    CONSTRAINT fk_reservation_employe  FOREIGN KEY (idEmploye)  REFERENCES employe  (idEmploye)
) ENGINE=InnoDB;

-- ── contrat dépend de reservation (OneToOne) ──────────────

CREATE TABLE contrat (
    idContrat     BIGINT        AUTO_INCREMENT PRIMARY KEY,
    dateSignature DATE          NOT NULL,
    montantTotal  DECIMAL(10,2) NOT NULL,
    valide        TINYINT(1)    NOT NULL,
    idReservation BIGINT        NOT NULL UNIQUE,
    CONSTRAINT fk_contrat_reservation FOREIGN KEY (idReservation) REFERENCES reservation (idReservation)
) ENGINE=InnoDB;

-- ── paiement dépend de contrat (OneToOne) ─────────────────

CREATE TABLE paiement (
    idPaiement   BIGINT        AUTO_INCREMENT PRIMARY KEY,
    montant      DECIMAL(10,2) NOT NULL,
    datePaiement DATE          NOT NULL,
    modePaiement ENUM('CARTE','ESPECES','VIREMENT') NOT NULL,
    idContrat    BIGINT        NOT NULL UNIQUE,
    CONSTRAINT fk_paiement_contrat FOREIGN KEY (idContrat) REFERENCES contrat (idContrat)
) ENGINE=InnoDB;

SET FOREIGN_KEY_CHECKS = 1;
