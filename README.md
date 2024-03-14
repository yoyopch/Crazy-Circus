Ce projet de développement orienté objet correspond à notre 3ème travail en groupe en 
rapport à la programmation, mais il est notre premier en Java.
L’objectif du projet était de réaliser un jeu qui permettrait l’affrontement de dompteurs.<br><br>
Le jeu est constitué de 2 podiums – l’un rouge, l’un bleu -, 3 animaux – un ours, un éléphant 
et un lion – ainsi que de 24 cartes objectifs représentants la situation de podiums à obtenir à 
chaque tour.<br><br>
Le but était de former le plus rapidement une combinaison de déplacements bien spécifique 
de manière à obtenir une situation finale de podium à partir d’une carte piochée. 
Un dompteur gagne la manche lorsqu’il trouve en premier la bonne combinaison ; et il 
récupère la carte. S’il joue et donne une mauvaise combinaison, alors il doit attendre la 
prochaine manche. <br><br>
S’il ne reste qu’un joueur a joué, alors il gagne automatiquement la manche ainsi que la 
carte.<br><br>
Le jeu se termine lorsque toutes les 24 cartes ont été jouées. Le dompteur ayant obtenu le 
plus de cartes gagne la partie.<br><br>
Les différentes combinaisons de déplacements possibles sont :
<ul>

 <li>
     <b>KI</b> : Permet de déplacer l’animal qui est en haut du podium bleu vers le sommet du podium rouge
 </li> 
 <li>
     <b>LO</b> : Permet de déplacer l’animal qui est en haut du podium rouge vers le sommet du podium bleu
 </li>
  <li>
      <b>SO</b> : Permet d’inverser les animaux se trouvant sur les sommets des 2 podiums
  </li>
  <li>
    <b>NI</b> : Permet de déplacer l’animal se trouvant en bas du podium bleu, tout en haut du même podium
  </li>
  <li>
      <b>MA</b> : Permet de déplacer l’animal se trouvant en bas du podium rouge, tout en haut du même podium
  </li>

  
</ul>

