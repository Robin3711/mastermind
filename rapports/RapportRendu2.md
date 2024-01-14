# Rapport rendu 2
Ce rapport est le rapport final du projet. Il ne répète pas les informations du premier rapport, mais les complète et les corrige. Ce rapport est donc à lire en complément du premier rapport.

Le projet n'étant pas parfait principalement par manque de temps, le rapport présente également des améliorations possibles.
## Présentations des nouvelles classes
### MenuWindow (vue)
MenuWindow est une classe représentant la fenêtre du menu principal. Elle permet de choisir les paramètres de jeu et de lancer une partie.

### GameWindow (vue)
GameWindow est une classe représentant la fenêtre de jeu. Elle permet de jouer une partie.

### EndWindow (vue)
EndWindow est une classe représentant la fenêtre de fin de partie. Elle permet de voir la synthèse de la partie et de revenir au menu principal.

### GameController (contrôleur)
GameController est une classe représentant le contrôleur de jeu. Il permet de gérer les interactions entre le modèle et la vue.

## Changements et choix réalisés
### Attributs de Game (rapport 1 ligne 30)
Il a été décidé de ne pas stocker d'attributs permettant l'instanciation des manches dans `Game` car ces attributs seraient utilisés uniquement pour instancier une nouvelle manche, donc ces informations sont demandées à d'autres classes lors de l'instanciation d'une nouvelle manche.

Cela permet de ne pas avoir à stocker ces informations dans `Game`, ce qui respecte le principe d'encapsulation.
### Design pattern Observer - Game / Round (rapport 1 ligne 31)
Nous n'avons pas mis d'observer permettant au `Game` d'observer `Round`. En effet, cela aurait été comme avoir `Game` qui contient `Round` qui contient `Game`, et ce en boucle. Cela aurait également surchargé le projet pour quelque chose qui n'est pas nécessaire.

Nous avons ainsi décidé que `GameController` fasse la vérification et appelle les méthodes nécessaires de Game en fonction de la fin d'une manche.
### Get/Set - UML
Nous avons décidé de ne pas mettre de `get` et de `set` dans l'UML car cela aurait surchargé l'UML pour quelque chose qui est évident.

## Améliorations possibles
### GameMode - Design pattern Strategy
Le design pattern `Strategy` aurait pu être utilisé pour les différents modes de jeu.

Cela aurait permis de ne pas avoir à faire de `switch` dans le code pour savoir quel mode de jeu est utilisé, surtout que le mode de jeu est toujours le même pour toutes les manches d'une même partie.

Nous avions pensé à cela au début mais nous pensions que cela n'était pas nécessaire car il n'y avait que trois modes de jeu, sans oublier qu'il n'y en a que 2 du point de vue du modèle (easy et autres) et 2 du point de vue de la vue (numeric et autres). En effet, le principe `KISS (Keep It Stupid, Simple)` nous a fait penser que cela n'était pas intéressant.

Néanmoins, dans une logique d'extension du projet, il aurait été intéressant d'utiliser ce design pattern. Ainsi, si l'on souhaite ajouter un nouveau mode de jeu, il faudrait ajouter une nouvelle valeur à l'énumération `GameMode`, ajouter une nouvelle classe héritant de la classe abstraite `GameModeStrategy` et implémenter les méthodes de cette nouvelle classe. Cela permettrait de ne pas avoir à modifier le code existant, ce qui est un principe de base de la `POO`.

Nous n'avons malheureusement pas eu le temps de mettre cela en place, mais c'est également un souci d'équilibre entre les principes de la `POO` et le principe `KISS`.