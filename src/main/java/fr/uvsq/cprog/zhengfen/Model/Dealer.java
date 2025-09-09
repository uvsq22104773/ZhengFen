package fr.uvsq.cprog.zhengfen.Model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final PaquetDeCartes paquet;

    public Dealer() {
        paquet = new PaquetDeCartes();
        paquet.melanger();
    }

    public List<List<Carte>> distribuerCartes(int nbJoueurs) {
        List<List<Carte>> mains = new ArrayList<>();
        int cartesParJoueur = paquet.taille() / nbJoueurs;

        for (int i = 0; i < nbJoueurs; i++) {
            mains.add(paquet.distribuer(cartesParJoueur));
        }

        // Distribuer les cartes restantes (1 ou 2) au hasard aux premiers joueurs
        int i = 0;
        while (!paquet.estVide()) {
            mains.get(i % nbJoueurs).addAll(paquet.distribuer(1));
            i++;
        }

        return mains;
    }
}
