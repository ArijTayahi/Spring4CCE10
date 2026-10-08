# Note technique — Stratégie de fetch et de cascade (Atelier 2)

> Projet : **AutoLoc API**
> Date : Atelier 2 — ASI 26-27

## Tableau récapitulatif

| Association | Fetch | Cascade | orphanRemoval | Justification |
|---|---|---|---|---|
| Contrat → Paiement | LAZY | ALL | true | Un paiement n'a pas d'existence propre sans son contrat (composition). Supprimer le contrat supprime ses paiements (`REMOVE`). Retirer un paiement de la liste sans supprimer le contrat suffit également à le supprimer en base (`orphanRemoval`). LAZY évite de charger tous les paiements à chaque lecture d'un contrat. |
| Agence → Vehicule | LAZY | Aucune | false | Un véhicule a une existence indépendante de l'agence : il peut être réaffecté. Supprimer l'agence ne doit pas supprimer les véhicules (pas de `REMOVE`). LAZY car la liste des véhicules n'est pas toujours nécessaire lors de l'accès à une agence. |
| Agence → Employe | LAZY | Aucune | false | Un employé peut changer d'agence ou exister sans en être rattaché (mutation, mise en disponibilité). Pas de cascade de suppression. LAZY pour les mêmes raisons que pour les véhicules. |
| Vehicule ↔ Equipement | LAZY | Aucune | false | Relation `@ManyToMany` entre entités indépendantes. Un équipement (GPS, siège bébé…) est partagé entre plusieurs véhicules ; le supprimer en cascade lors de la suppression d'un véhicule détruirait des données liées à d'autres véhicules. Utilisation d'un `Set` pour garantir l'unicité et éviter les doublons dans la table de jointure. LAZY indispensable : charger tous les véhicules liés à chaque accès à un équipement serait coûteux. |
| Client → Reservation | LAZY | PERSIST | false | `PERSIST` permet d'enregistrer un client avec ses premières réservations en une seule opération. `REMOVE` est exclu : supprimer un client ne doit pas effacer l'historique des réservations (valeur juridique et comptable). LAZY car la liste des réservations n'est utile que dans les vues détaillées du compte client. |
| Reservation → Vehicule | LAZY | Aucune | false | `@ManyToOne` simple : une réservation référence un véhicule existant. Le véhicule a son propre cycle de vie. Pas de propagation. |
| Reservation ↔ Contrat | LAZY | ALL (côté Contrat) | false | Le contrat est le document formel qui formalise la réservation. `cascade = ALL` depuis `Contrat` vers `Reservation` n'est pas appliqué ici ; c'est `Contrat` qui porte la FK `idReservation` (côté propriétaire). Supprimer un contrat peut supprimer la réservation associée via `ALL`. LAZY des deux côtés car la navigation bidirectionnelle n'est pas toujours requise. |
| Vehicule → Maintenance | LAZY | PERSIST | false | `PERSIST` permet d'enregistrer une maintenance lors de la création ou de la mise à jour d'un véhicule. `REMOVE` est exclu : une opération de maintenance constitue un historique technique du véhicule ; le supprimer en cascade lors de la suppression d'un véhicule ferait perdre des données d'audit. LAZY car l'historique de maintenance n'est consulté que dans des vues spécifiques. |

## Règle générale appliquée

- **`cascade = ALL` + `orphanRemoval = true`** uniquement pour les relations de **composition forte**, où l'enfant n'a pas de sens en dehors du parent (ex. Paiement sans Contrat).
- **`cascade = PERSIST`** pour les relations où le parent peut initier la création de ses enfants, mais où les enfants ont une durée de vie indépendante (ex. Réservation, Maintenance).
- **Aucune cascade** pour les associations d'**agrégation** (Agence → Vehicule, Vehicule ↔ Equipement) où les entités liées ont un cycle de vie autonome.
- **`FetchType.LAZY`** systématiquement, sauf besoin explicite et documenté d'un chargement immédiat, pour éviter des requêtes N+1 et des chargements inutiles.
