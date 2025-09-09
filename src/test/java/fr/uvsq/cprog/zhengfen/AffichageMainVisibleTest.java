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
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageMainVisible;

public class AffichageMainVisibleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String RESET = "\u001B[0m";
    private static final String VERT = "\u001B[42m";
    private static final String BLEU = "\u001B[48;5;26m";
    private static final String ROUGE = "\u001B[41m";
    private static final String VIOLET = "\u001B[45m";
    public static final String JAUNE = "\u001B[48;5;214m";

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

        AffichageMainVisible affichage = new AffichageMainVisible(joueur);
        affichage.afficher();

        String attendu = String.format("%s 3♠ %s %s 3♥ %s %s 3♦ %s %s 3♣ %s \n", BLEU, RESET, VIOLET, RESET, ROUGE, RESET, VERT, RESET);
        assertEquals(attendu, outContent.toString());
    }

    @Test
    public void testAffichageJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));

        Joueur joueur = new JoueurHumain("Joueur 1", new StrategieHumaine(TypeCombinaison.SINGLE));
        joueur.ajouterCartes(cartes);

        AffichageMainVisible affichage = new AffichageMainVisible(joueur);
        affichage.afficher();

        String attendu = String.format("%s 𝓙↑ %s %s 𝓙↓ %s \n", JAUNE, RESET, JAUNE, RESET);
        assertEquals(attendu, outContent.toString());
    }

    @Test
    public void testAffichageTouteCouleur() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));

        Joueur joueur = new JoueurHumain("Joueur 1", new StrategieHumaine(TypeCombinaison.SINGLE));
        joueur.ajouterCartes(cartes);

        AffichageMainVisible affichage = new AffichageMainVisible(joueur);
        affichage.afficher();

        String attendu = String.format("%s 3♠ %s %s 3♥ %s %s 3♦ %s %s 3♣ %s %s 𝓙↑ %s %s 𝓙↓ %s \n", BLEU, RESET, VIOLET, RESET, ROUGE, RESET, VERT, RESET, JAUNE, RESET, JAUNE, RESET);
        assertEquals(attendu, outContent.toString());
    }

    @Test
    public void testAffichageMainVide() {
        ArrayList<Carte> cartes = new ArrayList<>();
        Joueur joueur = new JoueurHumain("Joueur 1", new StrategieHumaine(TypeCombinaison.SINGLE));
        joueur.ajouterCartes(cartes);

        AffichageMainVisible affichage = new AffichageMainVisible(joueur);
        affichage.afficher();

        String attendu = "[Main vide]\n";
        assertEquals(attendu, outContent.toString());
    }
}
