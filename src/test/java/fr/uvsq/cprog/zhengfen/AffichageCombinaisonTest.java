package fr.uvsq.cprog.zhengfen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Pli;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurHumain;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieHumaine;
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageCombinaison;

public class AffichageCombinaisonTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String RESET = "\u001B[0m";
    private static final String VERT = "\u001B[42m";
    private static final String BLEU = "\u001B[48;5;26m";
    private static final String ROUGE = "\u001B[41m";
    private static final String VIOLET = "\u001B[45m";
    public static final String JAUNE = "\u001B[48;5;214m";
    private static final TypeCombinaison tc = TypeCombinaison.SINGLE;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAffichageCombinaison() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));

        CombinaisonInterface combinaison = tc.combinationFrom(cartes);
        AffichageCombinaison affichage = new AffichageCombinaison(combinaison);
        affichage.afficher();

        String attendu = String.format("%s 3♠ %s %s 3♦ %s %s 3♥ %s %s 3♣ %s \n", BLEU, RESET, ROUGE, RESET, VIOLET, RESET, VERT, RESET);
        assertEquals(attendu, outContent.toString());
    }

    @Test
    public void testAffichagePliDerniereCombi() {
        ArrayList<Carte> cartes1 = new ArrayList<>();
        cartes1.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes1.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes1.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes1.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        CombinaisonInterface combinaison1 = tc.combinationFrom(cartes1);

        ArrayList<Carte> cartes2 = new ArrayList<>();
        cartes2.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.QUATRE));
        CombinaisonInterface combinaison2 = tc.combinationFrom(cartes2);

        ArrayList<Carte> cartes3 = new ArrayList<>();
        cartes3.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.CINQ));
        cartes3.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.CINQ));
        cartes3.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.CINQ));
        cartes3.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.CINQ));
        CombinaisonInterface combinaison3 = tc.combinationFrom(cartes3);

        Joueur joueur1 = new JoueurHumain("Joueur 1", new StrategieHumaine(TypeCombinaison.SINGLE));
        Joueur joueur2 = new JoueurHumain("Joueur 2", new StrategieHumaine(TypeCombinaison.SINGLE));
        ArrayList<Joueur> list_joueur = new ArrayList<>();
        list_joueur.add(joueur1);
        list_joueur.add(joueur2);

        Pli pli = new Pli(list_joueur);
        pli.setCombinaisonEnCours(combinaison1);
        pli.setCombinaisonEnCours(combinaison2);
        pli.setCombinaisonEnCours(combinaison3);

        AffichageCombinaison affichage = new AffichageCombinaison(pli.getCombinaisonEnCours());
        affichage.afficher();

        String attendu = String.format("%s 5♠ %s %s 5♦ %s %s 5♥ %s %s 5♣ %s \n", BLEU, RESET, ROUGE, RESET, VIOLET, RESET, VERT, RESET);
        assertEquals(attendu, outContent.toString());
    }

}
