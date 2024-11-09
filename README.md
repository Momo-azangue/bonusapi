# PRÉSENTATION DES TÂCHES À RÉALISER 

## OBJECTIF
La tâche importante que nous devrons réaliser est l'indépendance au niveau de la bse de données, entre notre API 
et une autre API qui souhaiterait l'utiliser. Cela passera par : 
- Redéfinir les entités
- Ajuster le fonctionnement des transactions

###  Définition de l'entité Client
Dans la version modifiée de l'API, les utilisateurs seront désormais appelés **Client**, et le termer **User** lui, 
sera conservé pour les API. Donc, l'authentification ne sera pas réaliser par les utilisateurs proprement parlé, mais
plutôt par les API qui utiliseront notre système. Seul ceux-ci seront considérés comme des utilisateur de notre système.
Pour enregistrer un Client, un User doit passer les paramètre du Client dans une requête. La classe Entity représentant 
une Client dans notre base de données aura la forme suivante : 
- **id :** l'id de l'utilisatieur dans notre base de données 
- **username :** l'id de l'utilisateur dans la base de données du user en question 
- **userId :** l'id du user dont cette utilisateur appartient (id de l'API qui a stocké cet utilisateur)
L'entité Client sera crée sur le même format que les autres entités et son interface Repository elle aussi sera créée
toujours en calquant les autre modèles.

### Redéfinition de l'entité User 
Dans la première version de notre API, l'entité User était censée représenter un utilisateur quelconque qui 
s'authentifierait directement avec notre système. Avec la nouvelle révision, cette entité contiendra maintenant les 
champs : 
- **id :** id de l'API 
- **login :** login de l'API 
- **email :** email par laquelle nous pourrions contacter les propriétaire de l'API qui utilisera la notre
- **password :** password
- **roles :** autorisations 
- **resetToken :** token permettant de s'authentifier si jamais l'authentification ne marchais pas

### Redéfinition de l'entité Point, Historique, Transaction et Reward
Le seul changement à effectuer sur toutes ces entités est le passage de User à Client. En effet, ces entités ont été 
créées avec l'optique d'être utilisées par les utilisateurs finaux, mais la redéfinition dans notre contexte, nous pousse
à passer des User aux Client. 

### Redéfinition de l'entité Rule 
Les règles était d'abord faite pour être appliquées de manière égale à chaque utilisateur. Sauf que maintenant, les règles
seront définis par les API et les règles définies par une API ne s'applique qu'à celle-ci.

Après l'ajout et la modifications des entités, la prochaine étape c'est la modification des méthodes metiers, des méthodes 
du service. En effet, les méthodes du services ont été conçus pour être utilisée directement par un utilisateur, sauf que
cette fois ci, les méthodes seront appelées par des API qui nous passeront ces utilisateurs en paramètre. 

### Redéfinition du UserService et UserController
Ici il faut retirer toutes les occurences au champs qui auront été retirés plus haut 

### Redéfinition de HistoriqueService et HistoriqueController
- Ajouter une méthode pour récupérer toutes les entités Historique 
- Ajouter une méthode pour qu'une API puisse récupérer toutes les entités Historique concernant ses utilisateurs 
- Ajouter une méthode pour qu'une API puisse récupérer tous les historiques pour un Client qui lui appartient
- Dans le controller il n'y a pas très souvent de méthodes implémentés. Nous allons utiliser cette convention ici. 
    Il faut déplacer toutes les méthodes du controller vers le service en créant les méthodes adaptées. 

### Redéfinition de PointService 
- Ajouter la méthode pour récupérer les points en fonction du Client et s'assurer que la personne qui retire est l'API
- Ajouter la méthode pour récupérer les points de tous les clients d'une API spécifique

### Redéfinition RewardService et RewardController
- Transférer les méthodes écrites dans le controller vers le service 
- Ignorer les annotations 

### Redéfinition RuleService et RuleController 
- Ajouter une méthode qui permet de récupérer l'ensemble des règles éditées par une API 


## Spring Security
Dans cette section, il y a l'ensemble des éléments à réaliser côté Spring Security. Les modifications sur Spring Security
ne pourront être apportées que si l'ensemble des modifications énoncé plus haut ont été réalisé. Ce qu'il y a à faire : 
- Les annotations sur les controller devront être ramené au niveau des services 
- Les endpoints qui demandent l'id du User (de l'API ) doivent être remplacée par des endpoints qui ne demandent pas cet 
    id, et à la place utiliser le ContextHolder. Cela concerne à peu près toutes les fonctions services. 
- Pour toutes fonctions qui impliquent l'utilisateur du client, il faudra se rassurer que le user ( l'API ) qui utilise 
    cela soit légitime. Il faudra donc comparer l'id du user authentifié à l'id de l'API du Client manipulé. 

