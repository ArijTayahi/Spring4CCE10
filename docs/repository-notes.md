# Atelier 3 — Repository Layer Notes

## Interface choices

| Interface | Extends | Justification |
|---|---|---|
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IPaiementRepository | JpaRepository<Paiement, Long> | Lecture des paiements ; la création/suppression passe par le Contrat parent. |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |

JpaRepository is retained for all entities: it combines ListCrudRepository, ListPagingAndSortingRepository, and JPA-specific methods (flush, getReferenceById, deleteAllInBatch).

## SonarQube for IDE — anomalies corrected

| Anomalie | Règle / explication | Correction apportée |
|---|---|---|
| `@Repository` sur une interface Spring Data | L'annotation est redondante : Spring Data génère le proxy sans elle (règle java:S2176 / code smell). | Suppression de `@Repository` sur `IVehiculeRepository`. |
| Commentaire de code mort dans `IVehiculeRepository` | Les commentaires décrivant les méthodes héritées constituent du code mort (règle java:S125). | Suppression du bloc de commentaire dans le corps de l'interface. |
| Import inutilisé (`org.springframework.stereotype.Repository`) | Un import non utilisé alourdit le code inutilement (règle java:S1128). | Suppression de l'import après retrait de `@Repository`. |
