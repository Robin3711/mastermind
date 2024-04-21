Mastermind
==========

![](mastermind.jpg)

Les fonctionnalités
-------------------

sont implémentées :

- la génération de la combinaison secrète,
- la génération des indices associés à une combinaison proposée par le joueur,
- la détection de la victoire et de la défaite du joueur,
- la gestion des manches et des parties,
- la gestion du score

paramètres :

- avant de démarrer une partie, le joueur fixe ces paramètres qui seront les mêmes pour toutes ses manches :
  - le **nombre de manches** : 1 minimum, 3 par défaut, 5 maximum
  - le **nombre de pions disponibles** : 4 minimum, 8 par défaut, 8 maximum
  - le **nombre de pions d'une combinaison** : 2 minimum, 4 par défaut, 6 maximum
  - le **nombre de tentatives** maximum pour trouver la combinaison secrète : 2 minimum, 10 par défaut, 12 maximum
  - le **mode d'affichage des indices** : facile, classique ou numérique
- le **score d'une manche** est calculé à partir de la dernière tentative du joueur comme la somme du nombre de pions mal placés, de trois fois celle du nombre de pions bien placés et de 4 points bonus si on est en mode classique.

L'interface graphique
---------------------

l'app dispose de 3 écrans :

- un **écran de démarrage** pour choisir les paramètres : nom du joueur, type de partie, etc.
- un **écran de jeu** avec le plateau qui affiche les combinaisons tentées, les indices associés, etc.
- un **écran de fin de partie** qui affiche le score du joueur et s'il a gagné ou perdu

#### Les interactions de l'utilisateur

Un joueur peut :

- choisir les couleurs de sa prochaine combinaison
- valider sa combinaison pour recevoir l'indice de l'ordinateur
- remettre à zéro sa combinaison
- abandonner la manche courante pour passer à la suivante

### L'affichage des indices

L'affichage des indices dépend du mode choisit au niveau des paramètres :

- **mode "facile"** : les jetons noirs et blancs sont affichés en correspondance de la combinaison proposée par le joueur (i.e. à la même place)
- **mode "classique"** (mode par défaut) : les jetons noirs sont affichés en premier, puis les jetons blancs
- **mode numérique** : on affiche le nombre de pions bien placés et le nombre de pions mal placés.
