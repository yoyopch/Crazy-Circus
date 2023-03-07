package jeu;

import elements.*;
import affichage.Affichage;
import dompteur.Dompteur;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {

    private ArrayList<Dompteur> dompteurs; // ArrayList de Dompteur

    private Carte Initiale; // Carte initiale contenant la situation iniale à chaque tour

    private Carte Objectif; // Carte objectif qui contient la situation finale à chaque tour

    private CrazyCircus plateau; // le plateau qui contient les podiums

    /**
     * Constructor de Jeu - permet de créer le jeu( les cartes initiales et objectifs, inscrit les dompteurs)
     * @param args Tableau contenant tout les dompteurs
     */
    public Jeu(String[] args) {
        dompteurs = new ArrayList<>();
        Initiale = new Carte();
        Objectif = new Carte();
        plateau = new CrazyCircus(Initiale.getBleu(), Initiale.getRouge());

        for (String s : args) {
            Dompteur d = new Dompteur(s);
            dompteurs.add(d);
        }
        Initiale.generateCarteObjectif();
        premierTour();
    }

    /**
     * CarteAleat - permet d'obtenir aléatoirement une carte dans le paquet
     * @return la carte tirée
     * @see Carte
     */
    public static Carte CarteAleat() {
        return Carte.getCarte((int) (Math.random() * Carte.getCartes().size()));
    }

    /**
     * piocheAutreCarte - Pioche une carte objectif différente de celle entrée en paramètre
     * @param tmp Carte
     * @return la carte objectif tirée
     * @see Jeu#CarteAleat()
     * @see Carte#getCartes()
     */
    public Carte piocheAutreCarte(Carte tmp) {
        this.Objectif = CarteAleat();
        while (this.Objectif.equals(tmp)) {
            this.Objectif = CarteAleat();

        }
        Carte.getCartes().remove(Objectif);
        return this.Objectif;
    }

    /**
     * premierTour - Tire les deux premières cartes du jeu
     * @see Jeu#CarteAleat()
     * @see Jeu#piocheAutreCarte(Carte)
     */
    public void premierTour() {
        this.Initiale = CarteAleat();
        this.Objectif = piocheAutreCarte(this.Initiale);
    }

    /**
     * nouvelleManche - Génère une nouvelle manche
     * @param FinManche indique comment s'est terminée la manche 
     * si le dompteur a gagné en proposant une combinaison ou pas
     * @see Jeu#piocheAutreCarte(Carte)
     * @see Dompteur#setPeutJouerTrue()
     */
    public void nouvelleManche(int FinManche) {
        if (FinManche == 1) {
            this.Initiale = this.Objectif;
        }
        this.Objectif = piocheAutreCarte(this.Initiale);
        for (Dompteur d : dompteurs) {
            d.setPeutJouerTrue();
        }
    }

    /**
     * copyInitiale - Permet de copier la carte initiale pour ensuite simuler une commande
     * @return copy la copie de la carte initiale
     * @see Carte
     * @see Jeu#Initiale
     */
    public Carte copyInitiale() {
        Carte copy = new Carte();
        for (int i = 0; i < 3; ++i) {
            copy.getBleu().getAnimaux().add(this.Initiale.getBleu().getAnimaux().get(i));
            copy.getRouge().getAnimaux().add(this.Initiale.getRouge().getAnimaux().get(i));
        }
        return copy;
    }

    /**
     * getDompteurs - Permet de savoir si le dompteur existe
     * @param NomDompteur le nom du dompteur
     * @return le dompteur s'il participe au jeu, un dompteur "null" sinon
     * @see Jeu#dompteurs
     * @see Dompteur
     */
    public Dompteur getDompteurs(String NomDompteur) {
        for (Dompteur d : dompteurs) {
            if (d.getNomDompteur().equals(NomDompteur)) {
                return d;
            }
        }
        return null;
    }

    /**
     * compareScores - Compare le score de deux dompteurs
     * @param d1 le premier dompteur
     * @param d2 le second dompteur
     * @return 1 si le second a un meilleur score, 0 s'ils ont le méme score, -1 si
     *         le premier a un meilleur score
     */
    public int compareScores(Dompteur d1, Dompteur d2) {
        return Integer.compare(d2.getScoreDompteur(), d1.getScoreDompteur());
    }

    /**
     * comparePseudos - Compare les noms de deux dompteurs
     * @param d1 le premier dompteur
     * @param d2 le second dompteur
     * @return int positif si le second se trouve avant alphanumériquement parlant,
     *         0 s'ils ont le même nom,
     *         int négatif si le premier se trouve avant alphanumériquement parlant
     */
    public int comparePseudos(Dompteur d1, Dompteur d2) {
        return d1.getNomDompteur().compareTo(d2.getNomDompteur());
    }

    /**
     * ordreDompteurs - Permet le tri des dompteurs en fonction de leur score puis de leur nom
     */
    public void ordreDompteurs() {
        this.dompteurs.sort((d1, d2) -> {
            if (compareScores(d1, d2) != 0)
                return compareScores(d1, d2);
            else
                return comparePseudos(d1, d2);
        });

    }

    /**
     * dernierDompteurAJouer - Savoir si il reste un seul dompteur qui peut jouer
     * @return le dernier dompteur qui a encore le droit de jouer, sinon null
     */
    public Dompteur dernierDompteurAJouer() {
        int cptDompteurRestants = 0;
        for (Dompteur d : dompteurs) {
            if (d.PeutJouer()) {
                cptDompteurRestants++;
            }
        }
        if (cptDompteurRestants == 1) {
            for (Dompteur d : dompteurs) {
                if (d.PeutJouer()) {
                    return d;
                }
            }
        }
        return null;
    }

    /**
     * jouerDompteurs - Gère une partie de jeu à partir du niveau de difficulté défini tant que le paquet de cartes n'est pas vide
     * @param niveau le niveau de jeu selectionné 
     * @see Jeu#dernierDompteurAJouer()
     * @see Jeu#getDompteurs(String)
     * @see Jeu#nouvelleManche(int)
     * @see CrazyCircus#ordreValide(String)
     * @see CrazyCircus#deplacer(Carte, String)
     * @see Affichage#displayJeu(Carte, Carte)
     * @see Affichage#affichageGagnant(String)
     * @see Affichage#displayLeaderboard(ArrayList)
     * @see Affichage#affichageFinDuJeu(String)
     */
    public void jouerDompteurs(int niveau) {
        do {
            System.out.print(Affichage.displayJeu(this.Initiale, this.Objectif));
            System.out.println("Entrez votre nom suivi de votre combinaison : ");
            Scanner sc = new Scanner(System.in);
            String NomDompteur = sc.next();

            // on vérifie si le dompteur existe
            //if (!verifNomDompteurs(NomDompteur)) {
            if(getDompteurs(NomDompteur)==null){

                System.out.println("Le nom du dompteur indiqué n'existe pas");
            } else {
                String combinaisonDompteur = sc.next();
                // on vérifie si le dompteur a deja joué ou pas
                if (!getDompteurs(NomDompteur).PeutJouer()) {
                    System.out.println("Vous avez déjé joué dans cette manche, attendez la prochaine manche.");
                } else {
                    // le dompteur n'a pas joué dans cette manche, il propose sa combinaison
                    Carte testDompteur = copyInitiale();
                    if (niveau == 2 && combinaisonDompteur.toUpperCase().contains("SO")) {
                        System.out.print(Affichage.border);
                        System.out.println("La commande <<SO>> est impossible en mode difficile.");
                    } else {
                        plateau.deplacer(testDompteur, combinaisonDompteur.toUpperCase());
                        // Mauvaise combinaison
                        if (!Podium.verifComparaisonPodiums(Objectif, plateau)) {
                            System.out.println(
                                    "Eh non, la combinaison n'est pas la bonne, vous ne pouvez plus rejouer, attendez la prochaine manche ! ");
                            getDompteurs(NomDompteur).AJoue();
                            // s'il reste un seul dompteur
                            if (dernierDompteurAJouer() != null) {
                                dernierDompteurAJouer().AGagne();
                                System.out.println("Tous les autres joueurs se sont trompés, "
                                        + dernierDompteurAJouer().getNomDompteur() + " remporte la manche !");
                                System.out
                                        .println(Affichage.affichageGagnant(dernierDompteurAJouer().getNomDompteur()));
                                nouvelleManche(0);
                            }
                        }
                        // bonne combinaison
                        else {
                            System.out.println("Bravo, vous avez trouvé la bonne combinaison !"
                                    + getDompteurs(NomDompteur).getNomDompteur() + " gagne 1 point !");
                            getDompteurs(NomDompteur).AGagne();
                            System.out.println(Affichage.affichageGagnant(getDompteurs(NomDompteur).getNomDompteur()));
                            nouvelleManche(1);
                        }
                    }
                }

            }

            ordreDompteurs();
            System.out.println(Affichage.displayLeaderboard(this.dompteurs));
            sc.close();
        } while (!Carte.getCartes().isEmpty());

        System.out.println(Affichage.affichageFinDuJeu(this.dompteurs.get(0).getNomDompteur()));
    }



    /**
     * choixDifficulte - Permet de choisir la difficulté du jeu
     * @return 1 si le niveau régulier est choisi, 2 si le niveau difficile est
     *         choisi
     */
    public static int choixDifficulte() {
        System.out.print(Affichage.border);
        System.out.println("NIVEAU DU JEU");
        System.out.println("- Niveau r�gulier :les joueurs peuvent utiliser toutes les cartes ordres.");
        System.out.println(
                "- Niveau difficile :les joueurs peuvent utiliser toutes les cartes ordres sauf la carte << SO >>.");
        System.out.print(Affichage.border);
        Scanner sc = new Scanner(System.in);
        System.out.print("Choisissez le niveau de difficult� du jeu ( R pour R�gulier / D pour Difficile ) : ");
        String niveau = sc.next();
        int tmp;
        switch (niveau.toUpperCase()) {
            case "R":
                tmp = 1;
                System.out.println("Vous avez choisi le niveau r�gulier");
                break;
            case "D":
                tmp = 2;
                System.out.println("Vous avez choisi le niveau difficile");
                break;
            default:
                tmp = 1;
                System.out.println(
                        "La lettre rentr�e ne correspond � aucun niveau, le niveau r�gulier est choisi par d�faut.");
                break;
        }
        sc.close();
        return tmp;

    }

}