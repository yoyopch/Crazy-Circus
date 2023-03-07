package Test;

import affichage.Affichage;
import elements.Carte;
import elements.Podium;
import jeu.CrazyCircus;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestPodium {
    @Test
    public void testPodium(){
        //Création d'une carte Objectif
        Podium pBObj = new Podium();
        Podium pRObj = new Podium();
        pBObj.getAnimaux().add("OURS");
        pBObj.getAnimaux().add("ELEPHANT");
        pBObj.getAnimaux().add(Affichage.vide);
        pRObj.getAnimaux().add("LION");
        pRObj.getAnimaux().add(Affichage.vide);
        pRObj.getAnimaux().add(Affichage.vide);
        Carte CObj = new Carte();
        CObj.setBleu(pBObj);
        CObj.setRouge(pRObj);

        //Création de 2 podiums
        Podium pB = new Podium();
        Podium pR = new Podium();
        pR.getAnimaux().add("OURS");
        pR.getAnimaux().add("LION");
        pR.getAnimaux().add("ELEPHANT");
        pB.getAnimaux().add(Affichage.vide);
        pB.getAnimaux().add(Affichage.vide);
        pB.getAnimaux().add(Affichage.vide);
        Carte test = new Carte();
        test.setRouge(pR);;
        test.setBleu(pB);

        CrazyCircus Crz = new CrazyCircus(pB, pR);

        assertFalse(Podium.verifComparaisonPodiums(CObj, Crz));
        String combinaison = "KIMALO";
        assertTrue(CrazyCircus.ordreValide(combinaison));
        Crz.deplacer(test, combinaison);
        assertTrue(Podium.verifComparaisonPodiums(test, Crz));

    }
}
