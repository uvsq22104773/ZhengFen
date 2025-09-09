package fr.uvsq.cprog.zhengfen.View;

import fr.uvsq.cprog.zhengfen.Model.Carte;

public class VueJeu {

    public void afficherCarte(Carte carte) {
        if (carte != null) {
            System.out.println("Carte tirée : " + carte);
        } else {
            System.out.println("Aucune carte restante dans le paquet.");
        }
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
