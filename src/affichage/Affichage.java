package affichage;

import jeu.Jeu;
import elements.*;
import dompteur.Dompteur;
import java.util.ArrayList;

/**
 * Enumere les differents animaux existants
 * 
 * @author WEBER Yotam & PECH Yohann
 * @version 2.2 02/03/2023
 */
public class Affichage {

    public static final String border = "------------------------------------------\n";

    public static final String vide = "        ";

    /**
     * Permet l'affichage � la console de la situation de jeu
     *
     * @return un String contenant tout l'affichage du jeu
     */
    public static String displayJeu(Carte carteInit, Carte carteObj) {
        StringBuilder sJeu = new StringBuilder();

        String separator = "  ----  ";

        displayManche(sJeu);

        for (int i = 2; i >= 0; --i) {

            sJeu.append(carteInit.getBleu().getAnimaux().get(i));
            sJeu.append(" ");

            sJeu.append(carteInit.getRouge().getAnimaux().get(i));
            sJeu.append("     ");

            sJeu.append(carteObj.getBleu().getAnimaux().get(i));
            sJeu.append(" ");

            sJeu.append(carteObj.getRouge().getAnimaux().get(i));

            sJeu.append(System.lineSeparator());
        }

        for (int i = 0; i < 2; ++i)
            sJeu.append(separator).append(" ");

        sJeu.append("==>");

        for (int i = 0; i < 2; ++i)
            sJeu.append(" ").append(separator);

        sJeu.append(System.lineSeparator());

        sJeu.append("  BLEU     ROUGE        BLEU     ROUGE");

        sJeu.append(System.lineSeparator());
        sJeu.append(border);

        sJeu.append("KI : BLEU --> ROUGE    NI : BLEU  ^");
        sJeu.append(System.lineSeparator());
        sJeu.append("LO : BLEU <-- ROUGE    MA : ROUGE ^");
        sJeu.append(System.lineSeparator());
        sJeu.append("SO : BLEU <-> ROUGE");
        sJeu.append(System.lineSeparator().repeat(2));
        sJeu.append(border);
        return sJeu.toString();
    }

    /**
     * 
     * @param sJeu le StrinBuilder auquel on va ajouter des �l�ments
     */
    public static void displayManche(StringBuilder sJeu) {
        sJeu.append(border);
        sJeu.append("               ");
        sJeu.append("MANCHE ").append((23 - Carte.getCartes().size()) + 1);
        sJeu.append(System.lineSeparator());
        if (Carte.getCartes().size() == 23)
            sJeu.append("Nombre de cartes dans la pioche avant la manche : ").append(Carte.getCartes().size() + 1);
        else {
            sJeu.append("Nombre de cartes dans la pioche avant la manche : ").append(Carte.getCartes().size());
        }
        sJeu.append(System.lineSeparator());
        sJeu.append(border);
    }

    /**
     * Permet l'affichage du classement des Dompteurs
     *
     * @return une String contenant l'affichage
     * @see Jeu#ordreDompteurs()
     */
    public static String displayLeaderboard(ArrayList<Dompteur> dompteurs) {
        StringBuilder sLeaderboard = new StringBuilder();
        sLeaderboard.append(border);
        if (dompteurs.size() != 1) {
            int classement = 1;
            for (Dompteur d : dompteurs) {
                sLeaderboard.append(classement);
                sLeaderboard.append("- ");
                sLeaderboard.append(d.getNomDompteur());
                sLeaderboard.append(" ");
                sLeaderboard.append(d.getScoreDompteur());
                if (d.getScoreDompteur() <= 1)
                    sLeaderboard.append(" carte.");
                else {
                    sLeaderboard.append(" cartes.");
                }
                sLeaderboard.append(System.lineSeparator());
                classement++;
            }

        } else
            sLeaderboard.append("Fin de l'entrainement !");

        return sLeaderboard.toString();
    }

    /**
     * affichageGagnant - Afficher le gagnant de la manche
     * @param dompteurs Le nom du dompteur
     * @return l'affichage du gagnat
     */
    public static String affichageGagnant(String dompteurs) {
        StringBuilder sLeaderboard = new StringBuilder();
        sLeaderboard.append(dompteurs);
        sLeaderboard.append(" a gagne la manche !");
        return sLeaderboard.toString();
    }

    /**
     * affichageFinDuJeu - Afficher le gagnant du jeu final
     * @param dompteurs le gagnat du jeu final
     * @return l'affichage du gagnant final
     */
    public static String affichageFinDuJeu(String dompteurs) {
        StringBuilder sLeaderboard = new StringBuilder();
        sLeaderboard.append("Felicitations ! ");
        sLeaderboard.append(dompteurs);
        sLeaderboard.append(" a gagne la partie !");
        return sLeaderboard.toString();
    }

    /**
     * debutJeu - Affichage graphique de "Crazy Circus"
     * @return l'affichage graphique
     */
    public static String debutJeu() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                " ######   ########     ###    ######## ##    ##     ######   #### ########   ######   ##     ##  ######  \n");
        sb.append(
                "##    ##  ##     ##   ## ##        ##   ##  ##     ##    ##   ##  ##     ## ##    ##  ##     ## ##    ## \n");
        sb.append(
                "##        ##     ##  ##   ##      ##     ####      ##         ##  ##     ## ##        ##     ## ##       \n");
        sb.append(
                "##        ########  ##     ##    ##       ##       ##         ##  ########  ##        ##     ##  ######  \n");
        sb.append(
                "##        ##   ##   #########   ##        ##       ##         ##  ##   ##   ##        ##     ##       ## \n");
        sb.append(
                "##    ##  ##    ##  ##     ##  ##         ##       ##    ##   ##  ##    ##  ##    ##  ##     ## ##    ## \n");
        sb.append(
                " ######   ##     ## ##     ## ########    ##        ######   #### ##     ##  ######    #######   ######  \n");

        sb.append(System.lineSeparator());

        sb.append("Demarrage du jeu en cours...\n");
        sb.append("Saississez vos fouets et la scene est a vous ! ");
        sb.append(System.lineSeparator());

        return sb.toString();
    }

}
