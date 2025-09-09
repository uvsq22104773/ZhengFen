package fr.uvsq.cprog.zhengfen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;

public class OptionManagerTest {
    @Test
    public void testSingleton() {
        OptionManager optionManager1 = OptionManager.getInstance();
        OptionManager optionManager2 = OptionManager.getInstance();

        // Vérifie que les deux instances sont identiques
        assertSame(optionManager1, optionManager2);
    }

    @Test
    public void testSetNombreDeJoueurs() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDeJoueurs(2);
        assertEquals(2, optionManager.getNombreDeJoueurs());
    }

    @Test
    public void testSetNombreDeJoueursInvalid() {
        OptionManager optionManager = OptionManager.getInstance();
        try {
            optionManager.setNombreDeJoueurs(7);
        } catch (IllegalArgumentException e) {
            assertEquals("Le nombre de joueurs doit être entre 2 et 4.", e.getMessage());
        }
    }

    @Test
    public void testSetNombreIa() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDeJoueurs(2);
        optionManager.setNombreIa(1);
        assertEquals(1, optionManager.getNombreIa());
    }

    @Test
    public void testSetNombreIaInvalid() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDeJoueurs(4);
        try {
            optionManager.setNombreIa(5);
        } catch (IllegalArgumentException e) {
            assertEquals("Le nombre d'IA doit être entre 0 et 4.", e.getMessage());
        }
    }

    @Test
    public void testSetJoker() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setJoker(false);
        assertEquals(false, optionManager.isJoker());
    }

    @Test
    public void testSetCombinaisonsPossibles() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setCombinaison(TypeCombinaison.PAIR, false);
        assertEquals(false, optionManager.getCombinaisonsPossibles().get(TypeCombinaison.PAIR));
    }

    @Test
    public void testSetCombinaisonsPossiblesInvalid() {
        OptionManager optionManager = OptionManager.getInstance();
        try {
            optionManager.setCombinaison(null, true);
        } catch (IllegalArgumentException e) {
            assertEquals("La combinaison ne peut pas être nulle.", e.getMessage());
        }
    }

    @Test
    public void testSetDifficulte() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setDifficulte(1, 1);
        assertEquals(1, optionManager.getDifficulte(1));
    }

    @Test
    public void testSetDifficulteInvalid() {
        OptionManager optionManager = OptionManager.getInstance();
        try {
            optionManager.setDifficulte(1, 3);
        } catch (IllegalArgumentException e) {
            assertEquals("La difficulté doit être entre 1 (facile) et 2 (normale).", e.getMessage());
        }
    }

    @Test
    public void testSetNombreIaSetDifficulteToo() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDeJoueurs(2);
        optionManager.setNombreIa(1);
        assertEquals(1, optionManager.getDifficulte().size());
    }

    @Test
    public void testSetAllDifficultes() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDeJoueurs(3);
        optionManager.setNombreIa(3);
        optionManager.setAllDifficultes(1);
        assertEquals(1, optionManager.getDifficulte(1));
        assertEquals(1, optionManager.getDifficulte(2));
        assertEquals(1, optionManager.getDifficulte(3));
    }

    @Test
    public void testSetAllDifficultesInvalid() {
        OptionManager optionManager = OptionManager.getInstance();
        try {
            optionManager.setAllDifficultes(3);
        } catch (IllegalArgumentException e) {
            assertEquals("La difficulté doit être entre 1 (facile) et 2 (normale).", e.getMessage());
        }
    }

    @Test
    public void testGetHashMapDifficulte() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDeJoueurs(2);
        optionManager.setNombreIa(1);
        optionManager.setDifficulte(1, 1);
        assertEquals(1, optionManager.getDifficulte().get("IA1"));
    }

    @Test
    public void testSetNombreDePointsVictoire() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setNombreDePointsVictoire(10);
        assertEquals(10, optionManager.getNombreDePointsVictoire());
    }

    @Test
    public void testSetNombreDePointsVictoireInvalid() {
        OptionManager optionManager = OptionManager.getInstance();
        try {
            optionManager.setNombreDePointsVictoire(0);
        } catch (IllegalArgumentException e) {
            assertEquals("Le nombre de points de victoire doit être supérieur à 0.", e.getMessage());
        }
    }

    @Test
    public void testSetDebogage() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setDebogage(true);
        assertEquals(true, optionManager.isDebogage());
    }

    @Test
    public void testGetCombinaisonsPossibles() {
        OptionManager optionManager = OptionManager.getInstance();
        optionManager.setCombinaison(TypeCombinaison.PAIR, true);
        assertEquals(true, optionManager.getCombinaisonsPossibles().get(TypeCombinaison.PAIR));
    }
}
