package fr.uvsq.cprog.zhengfen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurHumain;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieHumaine;
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageMainCache;

public class AffichageMainCacheTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String GREY = "\u001B[48;5;242m";
    private static final String RESET = "\u001B[0m";

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAffichage3() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        
        Joueur joueur = new JoueurHumain("Joueur 1", new StrategieHumaine(TypeCombinaison.SINGLE));
        joueur.ajouterCartes(cartes);
        
        AffichageMainCache affichage = new AffichageMainCache(joueur);
        affichage.afficher();

        String attendu = String.format("%s ? %s %s ? %s %s ? %s %s ? %s \n", GREY, RESET, GREY, RESET, GREY, RESET, GREY, RESET);
        assertEquals(attendu, outContent.toString());
    }

    @Test
    public void testAffichageMainVide() {
        ArrayList<Carte> cartes = new ArrayList<>();
        Joueur joueur = new JoueurHumain("Joueur 1", new StrategieHumaine(TypeCombinaison.SINGLE));
        joueur.ajouterCartes(cartes);
        
        AffichageMainCache affichage = new AffichageMainCache(joueur);
        affichage.afficher();

        String attendu = "[Main vide]\n";
        assertEquals(attendu, outContent.toString());
    }
}
