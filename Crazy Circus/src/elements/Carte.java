package elements;

import java.util.ArrayList;
import java.util.Arrays;
import affichage.Affichage;

/**
 * Classe déterminant une Carte. Une carte contient deux Podiums
 *
 * @author WEBER Yotam & PECH Yohann
 * @version 2.2 02/03/2023
 */
public class Carte {

    private Podium bleu; // Le podium BLeu du jeu
    private Podium rouge; // Le podium Rouge du jeu
    private static ArrayList<Carte> cartes; // ArrayList de Carte

    /**
     * Constuctor de Carte
     */
    public Carte() {
        this.bleu = new Podium();
        this.rouge = new Podium();
    }

    /**
     * Génère les 24 cartes possibles du jeu
     * @see Carte
     * @see Podium
     */
    public void generateCarteObjectif() {

        String[][] combinaisons = {
                { "  LION  ", "ELEPHANT", "  OURS  " },
                { "  LION  ", "  OURS  ", "ELEPHANT" },
                { "ELEPHANT", "  LION  ", "  OURS  " },
                { "ELEPHANT", "  OURS  ", "  LION  " },
                { "  OURS  ", "ELEPHANT", "  LION  " },
                { "  OURS  ", "  LION  ", "ELEPHANT" }
        };

        cartes = new ArrayList<>();

        for (String[] combin : combinaisons) {
            Carte tmp1 = new Carte();
            Carte tmp2 = new Carte();

            ArrayList<String> Animaux = new ArrayList<>(Arrays.asList(combin));
            tmp1.getBleu().getAnimaux().addAll(Animaux);
            for (int i = 0; i < 3; i++)
                tmp1.getRouge().getAnimaux().add(Affichage.vide);

            tmp2.getBleu().getAnimaux().addAll(Animaux.subList(0, 2));
            tmp2.getBleu().getAnimaux().add(Affichage.vide);

            tmp2.getRouge().getAnimaux().addAll(Animaux.subList(2, 3));

            tmp2.getRouge().getAnimaux().add(Affichage.vide);
            tmp2.getRouge().getAnimaux().add(Affichage.vide);

            Carte carteEchange1 = new Carte();
            Carte carteEchange2 = new Carte();

            carteEchange1.getBleu().getAnimaux().addAll(tmp1.getRouge().getAnimaux());
            carteEchange1.getRouge().getAnimaux().addAll(tmp1.getBleu().getAnimaux());
            carteEchange2.getBleu().getAnimaux().addAll(tmp2.getRouge().getAnimaux());
            carteEchange2.getRouge().getAnimaux().addAll(tmp2.getBleu().getAnimaux());

            cartes.add(tmp1);
            cartes.add(carteEchange1);
            cartes.add(tmp2);
            cartes.add(carteEchange2);
        }
    }

    /**
     * getCarte : permet d'obtenir la carte du paquet
     * @see Carte
     * @param numeroCarte la position de la carte dans le paquet
     * @return la carte du paquet
     */
    public static Carte getCarte(int numeroCarte) {
        return cartes.get(numeroCarte);
    }

    /**
     * getCartes : permet d'obtenir le paquuet de cartes
     * @see Carte
     * @return le paquet de cartes
     */
    public static ArrayList<Carte> getCartes() {
        return cartes;
    }

    /**
     * getBleu : permet d'obtenir le podium Bleu
     * @return le podium bleu
     * @see Carte#bleu
     * @see Podium
     */
    public Podium getBleu() {
        return bleu;
    }

    /**
     * setBleu : permet de mettre en place le podium Bleu
     * @param bleu le nouveau podium bleu
     * @see Carte#bleu
     * @see Podium
     */
    public void setBleu(Podium bleu) {
        this.bleu = bleu;
    }

    /**
     * getRouge : permet d'obtenir le podiuù Rouge
     * @return le podium rouge
     * @see Carte#rouge
     * @see Podium
     */
    public Podium getRouge() {
        return this.rouge;
    }

    /**
     * setRouge : permet de mettre en place la podiuum Rouge
     * @param rouge le nouveau podium rouge
     * @see Carte#rouge
     * @see Podium
     */
    public void setRouge(Podium rouge) {
        this.rouge = rouge;
    }
}