package jeu;

import affichage.Affichage;
import elements.*;

import java.util.ArrayList;

public class CrazyCircus {

    private ArrayList<Podium> Jeu; // Le jeu - ArrayList de Podium
    public static final int BLEU = 0; // Indice correspondant au podium Bleu
    public static final int ROUGE = 1; // Indice correspondant au podium Rouge

    /**
     * Constructor de CrazyCircus
     * @param Bleu Le podium Bleu
     * @param Rouge Le podium Rouge
     */
    public CrazyCircus(Podium Bleu, Podium Rouge) {
        Jeu = new ArrayList<>();
        Jeu.add(Bleu);
        Jeu.add(Rouge);
    }

    /**
     * estVide - permet de savoir si le podium courant est vide
     * @param numPod le numero du podium - 0 pour Bleu - 1 pour Rouge
     * @return 1 si le Podium courant est vide, sinon 0
     */
    public boolean estVide(int numPod) {
        return Jeu.get(numPod).getAnimaux().isEmpty();
    }

    /**
     * depiler - permet de dépiler le dernier element du podium et le remplacer par du vide
     * @param numPod le numero du podium - 0 pour Bleu - 1 pour Rouge
     */
    public void depiler(int numPod) {
        assert !estVide(numPod);
        if (numPod == ROUGE)
            getPodiumRouge().getAnimaux().set(getIndexLastPodium(ROUGE), Affichage.vide);
        else {
            getPodiumBleu().getAnimaux().set(getIndexLastPodium(BLEU), Affichage.vide);
        }
    }

    /**
     * getIndexLastPodium - permet d'obtenir l'index du dernier animal du podium courant
     * @param numPod le numero du podium - 0 pour Bleu - 1 pour Rouge
     * @return la position du dernier animal du podium
     */
    public int getIndexLastPodium(int numPod) {
        Podium tmp = Jeu.get(numPod);
        for (int i = 0; i < tmp.getAnimaux().size(); ++i)
            if (tmp.getAnimaux().get(i).equals(Affichage.vide))
                return (i - 1);

        return 2;
    }
    /**
     * KI - permet d'effectuer le déplacement KI
     */
    public void KI() {
        assert !estVide(BLEU);
        if (getIndexLastPodium(BLEU) >= 0) {
            getPodiumRouge().getAnimaux().set(getIndexLastPodium(ROUGE) + 1,
                    getPodiumBleu().getAnimaux().get(getIndexLastPodium(BLEU)));
            depiler(BLEU);
        }
    }

    /**
     * LO - permet d'effectuer le déplacement LO
     */
    public void LO() {
        assert !estVide(ROUGE);
        if (getIndexLastPodium(ROUGE) >= 0) {
            getPodiumBleu().getAnimaux().set(getIndexLastPodium(BLEU) + 1,
                    getPodiumRouge().getAnimaux().get(getIndexLastPodium(ROUGE)));
            depiler(ROUGE);
        }
    }
    /**
     * SO - permet d'effectuer le déplacement SO
     */
    public void SO() {
        assert !estVide(BLEU) && !estVide(ROUGE);
        if (getIndexLastPodium(BLEU) >= 0 && getIndexLastPodium(ROUGE) >= 0) {
            String tmp = Jeu.get(BLEU).getAnimaux().get(getIndexLastPodium(BLEU));
            depiler(BLEU);
            LO();
            Jeu.get(ROUGE).getAnimaux().set(getIndexLastPodium(ROUGE) + 1, tmp);
        }
    }
    /**
     * NI - permet d'effectuer le déplacement NI
     */
    public void NI() {
        assert !estVide(BLEU);
        String tmp = Jeu.get(BLEU).getAnimaux().get(0);
        Jeu.get(BLEU).getAnimaux().remove(0);
        Jeu.get(BLEU).getAnimaux().add(Affichage.vide);
        Jeu.get(BLEU).getAnimaux().set(getIndexLastPodium(BLEU) + 1, tmp);
    }

    /**
     * MA - permet d'effectuer le déplacement MA
     */
    public void MA() {
        assert !estVide(ROUGE);
        String tmp = Jeu.get(ROUGE).getAnimaux().get(0);
        Jeu.get(ROUGE).getAnimaux().remove(0);
        Jeu.get(ROUGE).getAnimaux().add(Affichage.vide);
        Jeu.get(ROUGE).getAnimaux().set(getIndexLastPodium(ROUGE) + 1, tmp);
    }

    /**
     * ordreValide - permet de Vérifier si la combinaison du dompteur est valide
     * @param ordre Combinaison du dompteur
     * @return true si la combinaison ne comporte pas d'erreur, sinon false
     */
    public static boolean ordreValide(String ordre) {
        if (ordre.length() % 2 != 0)
            return false;

        for (int i = 0; i < ordre.length(); i = i + 2) {
            if (!ordre.startsWith("KI", i) && !ordre.startsWith("LO", i)
                    && !ordre.startsWith("SO", i) && !ordre.startsWith("NI", i)
                    && !ordre.startsWith("MA", i))
                return false;
        }
        return true;
    }

    /**
     * deplacer - permet de simuler la combinaison saisie par le dompteur
     * @param carte Carte à partir de laquelle on veut simuler l'ordre
     * @param deplacements Combinaison du dompteur
     */
    public void deplacer(Carte carte, String deplacements) {
        Jeu.set(BLEU, carte.getBleu());
        Jeu.set(ROUGE, carte.getRouge());
        if (ordreValide(deplacements)) {
            int LongeurChaineDeplacements = deplacements.length();
            for (int i = 0; i < LongeurChaineDeplacements; i += 2) {
                switch (deplacements.substring(i, i + 2)) {
                    case "KI":
                        KI();
                        break;
                    case "LO":
                        LO();
                        break;
                    case "SO":
                        SO();
                        break;
                    case "NI":
                        NI();
                        break;
                    case "MA":
                        MA();
                        break;
                }
            }
        }
    }

    /**
     * getPodiumBleu - permet d'obtenir le podium Bleu
     * @return le podium bleu
     */
    public Podium getPodiumBleu() {
        return Jeu.get(BLEU);
    }

    /**
     * getPodiumRouge - permet d'obtenir le podium Rouge
     * @return le podium rouge
     */
    public Podium getPodiumRouge() {
        return Jeu.get(ROUGE);
    }

}
