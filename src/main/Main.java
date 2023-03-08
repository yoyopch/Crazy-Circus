package main;

import affichage.Affichage;
import jeu.Jeu;

public class Main {
    public static void main(String[] args) {

        // On cree le jeu avec les cartes et les dompteurs
        Jeu j = new Jeu(args);
        System.out.println(Affichage.debutJeu());
        j.jouerDompteurs(Jeu.choixDifficulte());

    }


}