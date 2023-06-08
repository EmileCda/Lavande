# Jasmin

## Jasmin (Projet Ecommerce)

## Analyse Fonctionnel de projet  Jasmin:

 <img src="./images/analyse.png" width="600"/>
## Uses case Utilisés

### Cas d’utilisation : Gestion Admin

 <img src="./images/gestion_admin.png" width="600"/>
### Cas d’utilisation : Gestion d’aricle

<img src="./images/gestion_article.png" width="600"/>
### Cas d’utilisation : Gestion Client

### Cas d’utilisation : Gestion Achat

### Cas d’utilisation : Gestion Commande

<img src="./images/gestion_achat.png" width="600"/>
### Cas d’utilisation : Gestion Web Service

 <img src="./images/gestion_webService.png" width="600"/>### Cas d’utilisation : Gestion Système<img src="./images/gestion_systeme.png" width="600"/>
## Choix de projet

### Le nom du Projet : Jasmin

### Projet : Dynamic Web project  avec maven. (war)

### La langue de Développement : anglais.

 <img src="./images/Creation_Jasmin.png" width="600"/> <img src="./images/fichier_projet.png" width="600"/> <img src="./images/Creation_Jasmin.png" width="600"/> <img src="./images/web_module.png" width="600"/>
## Configuration pom.xml

Ajouter les dépendancies

## Travailler avec hibernante : (pouquoi ?)

Configuration de hibernate :
Ajouter les dépendancies nécessaire pour activer ce services
Créer une base de donnée mysqlWorkbansh : jasmin

Ajouter les  packages:
Créer les packages suivants :
package  « fr.jasmin.model.connector »
ajouter le ficher au dossier src/main/resources  «hibernate.cfg.xml »

package   « fr.jasmin.model.dao »
permettre de créer les entityDao exmelpe UtilisateurDao

package   « fr.jasmin model.dao .ineterfaces »

package   « fr.jasmin.backingbeanr »
package   « fr.jasmin.entity»
package   « fr.jasmin.enums»

package   « fr.jasmin.test» : pour les tests
package   « fr.jasmin.utils»

<img src="./images/dossier.png" width="600"/><img src="./images/dossier1.png" width="600"/>
## Partager les taches de projet jasmin

| kaoutar| emile | soumaya | Med reza  |
|----------|-------------|------|----|
| CRUD magasinier : 8 |                | Creation dun compte Client : 12  |           |
| K: 1                |                |    K : 1.25                      |           |
|I : 3                |                |    I : 1.5                       |           |

* CRUD magasinier : 8
  K: 1 
  I : 3
* Creation master Admin : 1 
  K : 0.25
  I : 0
* Créer un seul panier : 8
  K : 1
  I : 1.5
* Creation dun compte Client : 12		
  K : 1.25 
  I : 2.25
* CRUD Articles /Categorie : 11
  K : 1.5
  I :3.75	
## Taches kaoutar projet jasmin :

CRUD magasinier :
Création utilisateurs et adresse  avec les annotation hibernate
Package « fr.jasmin.entity»
Adresse.java
Utilisateur.java

package   « fr.jasmin model.dao .ineterfaces »
IUtilisateurDao .java
void addMagasinier(Utilisateur utilisateur) throws Exception ;
	Utilisateur getMagasinier(Integer id) throws Exception;
	void updateMagasinier(Utilisateur utilisateur) throws Exception;
	void removeMagasinier(Integer id) throws Exception;
	List<Utilisateur> getMagasiner();	
	void removeMagasinier(Utilisateur utilisateur) throws Exception;

package   « fr.jasmin.model.dao »
UtilisateurDao .java (Traiter les méthodes de IUtilisateurDao) .

package   « fr.jasmin.enums»
créer une énumération pour utiliser ces trois profils
ProfilUserEnum .java  

Trois profils différents d’un utilisateur, il s’agit de : Client / Magasinier / Admin.

## Le daily scrum    daté : le 23/05/2023  à 15 :00

## Le daily scrum  projet jasmin : 9h

