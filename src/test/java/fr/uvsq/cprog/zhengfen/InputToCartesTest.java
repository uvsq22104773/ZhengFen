package fr.uvsq.cprog.zhengfen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.View.InputToCartes;

public class InputToCartesTest {
   
    @Test
    public void testInputToCarteSimple() {
        String input = "10(H) 2(D) J(C) 3(S)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        ArrayList<Carte> expectedCartes = new ArrayList<>();
        expectedCartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        expectedCartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        expectedCartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        expectedCartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        assertEquals(expectedCartes.size(), cartes.size());
        for (int i = 0; i < expectedCartes.size(); i++) {
            assertEquals(expectedCartes.get(i).getValeur(), cartes.get(i).getValeur());
            assertEquals(expectedCartes.get(i).getCouleur(), cartes.get(i).getCouleur());
        }
    }

    @Test
    public void testInputToCarteCompose() {
        String input = "10(HD) 2(D) J(C) 3(S)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        ArrayList<Carte> expectedCartes = new ArrayList<>();
        expectedCartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        expectedCartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        expectedCartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        expectedCartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        expectedCartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        assertEquals(expectedCartes.size(), cartes.size());
        for (int i = 0; i < expectedCartes.size(); i++) {
            assertEquals(expectedCartes.get(i).getValeur(), cartes.get(i).getValeur());
            assertEquals(expectedCartes.get(i).getCouleur(), cartes.get(i).getCouleur());
        }
    }

    @Test
    public void testInputToJoker() {
        String input = "JK(G)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        ArrayList<Carte> expectedCartes = new ArrayList<>();
        expectedCartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        assertEquals(expectedCartes.size(), cartes.size());
        for (int i = 0; i < expectedCartes.size(); i++) {
            assertEquals(expectedCartes.get(i).getValeur(), cartes.get(i).getValeur());
            assertEquals(expectedCartes.get(i).getCouleur(), cartes.get(i).getCouleur());
        }
    }

    @Test
    public void testInputToCarteInvalide() {
        String input = "10(H) 2(D) J(C) 3(S) 4";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        assertEquals(null, cartes);
    }

    @Test
    public void testInputToCarteInvalide2() {
        String input = "10(H) 2(D) J(C) 3(S) 4(5)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        assertEquals(null, cartes);
    }

    @Test
    public void testInputToCarteInvalide3() {
        String input = "10(H) 2(D) J(C) 3(S) JK(GS)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        assertEquals(null, cartes);
    }

    @Test
    public void testInputToCarteInvalide4() {
        String input = "10(H) 2(DP) J(C) 3(S) JK(G)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        assertEquals(null, cartes);
    }

    @Test
    public void testInputToCarteInvalide5() {
        String input = "10(H) 2(DD) J(C) 3(S) JK(G)";
        List<Carte> cartes = InputToCartes.inputToCartes(input);
        assertEquals(null, cartes);
    }
}
