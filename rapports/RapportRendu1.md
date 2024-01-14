# Rapport rendu 1
Pour le moment, aucune vue et aucun contrôleur n'ont été réalisés. Seul le modèle est implémenté. L'UML contient uniquement des vues et des contrôleurs vides, avec quasiment aucune relation.

Certaines fonctionnalités voire des mécaniques de jeu ne sont pas encore modélisés dans l'UML. Cependant, ces derniers le seront d'ici le deuxième rendu.

Aucun code source n'est dans ce rendu car nous n'avons pas encore de version fonctionnelle.
## Présentation des classes
### Game (modèle)
Game est une classe représentant une partie de Mastermind, c'est-à-dire un ensemble de manches représentées par des instances de la classe Round.
### Round (modèle)
Round est une classe représentant une manche d'une partie, c'est-à-dire un ensemble d'essais représentés par des instances de la classe Attempt.
### Combination (modèle)
Combination est une classe représentant une combinaison de pions.
### Solution (modèle)
Solution est une classe représentant la combinaison secrète à trouver. Une solution est un type de combinaison particulier. Ainsi, la classe Solution hérite de la classe Combination.
### Attempt (modèle)
Attempt est une classe représentant une tentative réalisée par le joueur. Une tentative ne représente pas seulement une combinaison soumise par le joueur mais également les indices résultant de la comparaison entre cette combinaison et la solution.
### PawnColor (modèle)
PawnColor est une énumération représentant une couleur de pion. Cette énumération est constituée de toutes les couleurs de pions possibles, même si certaines ne sont pas utilisées dans une partie spécifique.
### GameMode (modèle)
GameMode est une énumération représentant le mode de jeu d'une partie. Ce mode de jeu peut être soit `CLASSIC`, soit `EASY`, soit `NUMERIC`. Logiquement et comme pour PawnColor, cette énumération est constituée de tous les modes de jeu possibles, même si un seul est utilisé dans un round spécifique. En effet, il est possible de changer le mode de jeu entre deux manches.
### Clue (modèle)
Clue est une énumération représentant l'état d'un indice relatif à une tentative. Cet indice peut être soit `WELL_PLACES`, soit `MISPLACED`, soit `WRONG`. Un indice résulte de la comparaison entre une combinaison soumise par le joueur et la combinaison secrète.
## Choix réalisés
### Global
- Malgré le fait que l'on puisse modifier les paramètres d'une partie qui n'ait pas commencée, aucun attribut n'a le mot-clé static dans les différentes classes pour respecter le principe d'encapsulation. Il n'y a pas non plus de classe correspondant à une collection de paramètres de jeu stockés dans des attributs car cela ne respecterait pas des principes de base de la POO et obligerait à demander à cette classe le paramètre désiré à chaque fois que l'on en aurait besoin.
### Game
- Game stocke toutes les instances de Round de la partie afin de simplifier l'implémentation de nouvelles fonctionnalités nécessitant de parcourir les manches du jeu. Il n'était évidemment pas envisageable de stocker les informations des manches dans la vue car cela ne respecterait pas le principe même de MVC.
- Game ne stocke plus le numéro correspondant à la manche actuelle pour comparer avec le nombre de manches maximum, car cela est désormais très facilement calculable avec le nombre d'éléments dans le tableau de manches.
- Ce n'est pas encore décidé mais il est possible que Game contiennne des attributs permettant de stocker des paramètres utiles à l'instanciation des manches. Cela permettrait de ne pas avoir à les passer en paramètre de la méthode instanciant une nouvelle manche. Cela est permis car les paramètres d'instanciation d'une manche ne sont pas modifiables entre deux manches, sauf GameMode. L'UML ainsi que le code actuel ne reflètent pas ce choix car Game n'utiliserait ces attributs uniquement pour instancier une nouvelle manche.
- Nous avions pensé à utiliser le design pattern Observer pour prévenir Game de la fin d'une manche afin de passer à la manche suivante ou bien de terminer la partie. Cependant, cela nous embêtait sachant que Game stockait déjà l'instance de Round, cela aurait été comme avoir Game qui contient Round qui contient Game. Nous envisageons donc le fait que ça soit le contrôleur qui prévienne Game de la fin d'une manche. Evidemment, nous n'avons pas encore les vues ainsi que les contrôleurs donc nous ne pouvons pas encore implémenter cette fonctionnalité ou bien la modéliser dans l'UML.
### Round
- Round stocke toutes les instances de Attempt de la manche afin de simplifier l'implémentation de nouvelles fonctionnalités nécessitant de parcourir les essais de la manche. Comme pour Game, il n'était pas envisageable de stocker les informations des essais dans la vue car cela ne respecterait pas le principe même de MVC.
- A l'instar de Game, Round ne stocke plus le numéro correspondant à l'essai actuel pour comparer avec le nombre d'essais maximum car cela est désormais très facilement calculable avec le nombre d'éléments dans le tableau d'essais.
### Combination
- Une simple combinaison n'a aucune méthode en dehors de constructeurs, de getter et de setter. Cela est dû au fait que des actions sont utiles uniquement pour une combinaison spéciale, la combinaison secrète. En effet, une combinaison secrète peut être comparée à une autre combinaison, mais il n'y a pas d'intérêt à comparer deux combinaisons quelconques par exemple.
### Attempt
- Un essai était avant une simple combinaison donc n'avait pas de sens en tant que classe. Cependant, il a été décidé de créer une classe Attempt afin de pouvoir stocker le tableau d'indices résultant de la comparaison entre la combinaison de l'essai et la solution. L'utilisé de cette classe est également de diminuer les responsabilités de Round. Cela n'était aussi pas nécessaire quand on ne stockait pas les informations des manches.
### PawnColor
- Il n'y a pas de classe Pawn car un pion est représenté par une couleur de pion, donc PawnColor. Il n'y a donc pas d'intérêt à créer une classe Pawn.
### GameMode
- GameMode va très légérement intervenir dans le modèle afin de calculer le score d'une manche ou de l'ensemble de la partie. Néanmoins, il aura un rôle plus important dans la vue, pour savoir comment afficher les indices.
- Nous n'autorisons pas le changement de mode durant une même manche car cela n'aurait aucun sens à cause du calcul de points. En effet, le calcul de points est différent selon le mode de jeu. Cependant, il est possible de changer de mode entre deux manches.