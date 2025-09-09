package fr.uvsq.cprog.zhengfen;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurHumain;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieHumaine;

public class DistributionTest {

    @Test
    void shouldSortColor() {
        Carte c1 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX);
        Carte c2 = new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX);
        Carte c3 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX);
        Carte c4 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX);

        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c4);
        hand.add(c3);
        hand.add(c2);
        hand.add(c1);

        JoueurHumain joueur = new JoueurHumain("Test", new StrategieHumaine(TypeCombinaison.SINGLE));
        joueur.ajouterCartes(hand);

        ArrayList<Carte> sorted = new ArrayList<>(joueur.getMain());
        sorted.sort(
                (a, b) -> Integer.compare(Carte.colorOrder.get(a.getCouleur()), Carte.colorOrder.get(b.getCouleur())));

        ArrayList<Carte.Couleur> attendu = new ArrayList<>();
        attendu.add(Carte.Couleur.PIQUE);
        attendu.add(Carte.Couleur.COEUR);
        attendu.add(Carte.Couleur.CARREAU);
        attendu.add(Carte.Couleur.TREFLE);

        for (int i = 0; i < attendu.size(); i++) {
            assertEquals(attendu.get(i), sorted.get(i).getCouleur());
        }
    }
}
