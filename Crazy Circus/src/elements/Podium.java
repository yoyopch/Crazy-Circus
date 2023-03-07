package elements;

import jeu.CrazyCircus;

import java.util.ArrayList;

public class Podium {
    private ArrayList<String> Podiums; // Un podium - ArrayList de Sting

    /**
     * Constructor de podium
     */
    public Podium() {
        Podiums = new ArrayList<>();
    }

    /**
     * getAnimaux : permet d'obtnir le podium d'Animaux
     * @return le podium d'animaux
     */
    public ArrayList<String> getAnimaux() {
        return this.Podiums;
    }
    /**
     * verifComparaisonPodiums - Permet de vérifier si les podiums de la carte Objectif sont égaux à ceux du plateau
     * @return true s'ils sont égaux, sinon false
     */
    public static boolean verifComparaisonPodiums(Carte Objectif, CrazyCircus plateau) {
        return Objectif.getRouge().getAnimaux().equals(plateau.getPodiumRouge().getAnimaux()) &&
                Objectif.getBleu().getAnimaux().equals(plateau.getPodiumBleu().getAnimaux());
    }
}
