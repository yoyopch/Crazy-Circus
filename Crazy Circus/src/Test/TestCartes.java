package Test;

import elements.Carte;
import jeu.Jeu;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCartes {
    @Test
    public void TestCartes(){
        String []args = {"yoyo"};
        Jeu j = new Jeu(args);
        Carte C = new Carte();
        C.generateCarteObjectif();
        assertFalse(Carte.getCartes().size()==23);
        assertTrue(Carte.getCartes().size()==24);
        Carte C1 = Jeu.CarteAleat();
        Carte C2 = j.piocheAutreCarte(C1);
        assertFalse(Carte.getCartes().size()==24);
        assertTrue(Carte.getCartes().size()<24);

    }
}
