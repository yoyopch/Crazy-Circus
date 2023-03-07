package dompteur;

public class Dompteur {
    private String NomDompteur; // Le nom du dompteur
    private int scoreDompteur; // Le nombre de cartes du dompteur
    private boolean PeutJouer; // Le dompteur peut jouer dans la manche

    /**
     * Constructor
     * @param nomDompteur - le nom du nouveau dompteur
     */
    public Dompteur(String nomDompteur) {
        this.NomDompteur = nomDompteur;
        this.scoreDompteur = 0;
        PeutJouer = true;
    }
/**
 * getNomDompteur - permet d'avoir le nom du compteur
 * @return NomDompteur - le nom du dompteur
 */
    public String getNomDompteur() {
        return NomDompteur;
    }
/**
 * getScoreDompteur - permet d'avoir le nombre de cartes du dompteur
 * @return scoreDompteur - le nombre de cartes du dompteur
 */
    public int getScoreDompteur() {
        return this.scoreDompteur;
    }
/**
 * Le Dompteur courant ne peut plus jouer 
 * On met le booleén à false
 */
    public void AJoue() {
        PeutJouer = false;
    }
/**
 * Le dompteur a gangné la manche
 * Il gagne 1 point
 */
    public void AGagne() {
        scoreDompteur++;
    }
/**
 * Le booleen PeutJouer
 * @return PeutJouer
 */
    public boolean PeutJouer() {
        return PeutJouer;
    }
/**
 * Nouvelle Manche, le dompteur peut jouer
 * On met le booleen à true
 */
    public void setPeutJouerTrue() {
        PeutJouer = true;
    }
}
