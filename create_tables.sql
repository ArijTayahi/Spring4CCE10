CREATE DATABASE IF NOT EXISTS autoloc_db;
USE autoloc_db;

CREATE TABLE vehicule (
    idVehicule BIGINT AUTO_INCREMENT PRIMARY KEY,
    immatriculation VARCHAR(20) NOT NULL UNIQUE,
    marque VARCHAR(50) NOT NULL,
    modele VARCHAR(50) NOT NULL,
    categorie ENUM('CITADINE','BERLINE','SUV','UTILITAIRE') NOT NULL,
    tarifJournalier DECIMAL(10,2) NOT NULL,
    statut ENUM('DISPONIBLE','LOUE','MAINTENANCE') NOT NULL
);

CREATE TABLE agence (
    idAgence BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    ville VARCHAR(50) NOT NULL,
    adresse VARCHAR(150) NOT NULL,
    telephone VARCHAR(20)
);

CREATE TABLE client (
    idClient BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    numPermis VARCHAR(30) NOT NULL UNIQUE,
    dateInscription DATE NOT NULL
);

CREATE TABLE employe (
    idEmploye BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    role ENUM('AGENT','MANAGER') NOT NULL
);

CREATE TABLE equipement (
    idEquipement BIGINT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE reservation (
    idReservation BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL,
    statut ENUM('EN_ATTENTE','CONFIRMEE','ANNULEE','TERMINEE') NOT NULL
);

CREATE TABLE contrat (
    idContrat BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateSignature DATE NOT NULL,
    montantTotal DECIMAL(10,2) NOT NULL,
    valide BOOLEAN NOT NULL
);

CREATE TABLE paiement (
    idPaiement BIGINT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10,2) NOT NULL,
    datePaiement DATE NOT NULL,
    modePaiement ENUM('CARTE','ESPECES','VIREMENT') NOT NULL
);

CREATE TABLE maintenance (
    idMaintenance BIGINT AUTO_INCREMENT PRIMARY KEY,
    dateDebut DATE NOT NULL,
    dateFin DATE NULL,
    description VARCHAR(255)
);
