package Test;
import org.junit.Test;

import static org.junit.Assert.*;
import jeu.Jeu;


public class TestDompteurs {
    @Test
    public void testDompteurPeutJouer(){
        String []args = new String[]{"yoyo", "toto"};
        Jeu j =new Jeu(args);
        assertNotNull(j.getDompteurs("yoyo"));
        assertNotNull(j.getDompteurs("toto"));
        assertNull(j.getDompteurs("popo"));
        assertTrue(j.getDompteurs("yoyo").PeutJouer()&& j.getDompteurs("toto").PeutJouer());
        j.getDompteurs("yoyo").AJoue();
        j.getDompteurs("toto").AJoue();
        assertFalse(j.getDompteurs("yoyo").PeutJouer() && j.getDompteurs("toto").PeutJouer());
        j.getDompteurs("yoyo").setPeutJouerTrue();
        j.getDompteurs("toto").setPeutJouerTrue();
        assertTrue(j.getDompteurs("yoyo").PeutJouer()&& j.getDompteurs("toto").PeutJouer());
        assertEquals(j.getDompteurs("yoyo").getNomDompteur(), "yoyo");
        assertNotEquals(j.getDompteurs("yoyo").getNomDompteur(), "toto");
        assertTrue(j.compareScores(j.getDompteurs("yoyo"), j.getDompteurs("toto"))==0);
        assertFalse(j.comparePseudos(j.getDompteurs("yoyo"), j.getDompteurs("toto"))==0);
        j.getDompteurs("yoyo").AJoue();
        assertNotNull(j.dernierDompteurAJouer());
        j.getDompteurs("toto").AJoue();
        assertNull(j.dernierDompteurAJouer());
        j.getDompteurs("yoyo").AGagne();
        assertTrue(j.compareScores(j.getDompteurs("yoyo"),j.getDompteurs("toto"))==-1);
    }

}
