package fr.uvsq.cprog.zhengfen.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaquetDeCartes {
    private final List<Carte> cartes = new ArrayList<>();

    public PaquetDeCartes() {
        for (Carte.Couleur couleur : Carte.Couleur.values()) {
            if (couleur == Carte.Couleur.BIG || couleur == Carte.Couleur.SMALL) {
                continue;
            }
            for (Carte.Valeur valeur : Carte.Valeur.values()) {
                if (valeur != Carte.Valeur.JOKER) {
                    cartes.add(new Carte(couleur, valeur));
                }
            }
        }
        // Jokers : 1 petit, 1 grand
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
    }

    public void melanger() {
        Collections.shuffle(cartes);
    }

    public List<Carte> distribuer(int nb) {
        List<Carte> main = new ArrayList<>();
        for (int i = 0; i < nb && !cartes.isEmpty(); i++) {
            main.add(cartes.remove(0));
        }
        return main;
    }

    public boolean estVide() {
        return cartes.isEmpty();
    }

    public int taille() {
        return cartes.size();
    }
}
