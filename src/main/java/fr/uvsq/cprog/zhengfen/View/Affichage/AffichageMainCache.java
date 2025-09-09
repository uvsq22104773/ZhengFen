package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import java.util.List;

/**
 * Classe pour afficher la main d'un joueur avec les cartes cachées.
 */
public class AffichageMainCache implements ComposantAffichage {
    private Joueur joueur;

    /**
     * Constructeur de la classe AffichageMainCache.
     *
     * @param joueur Le joueur dont on veut afficher la main.
     */
    public AffichageMainCache(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Méthode pour afficher la main du joueur avec les cartes cachées.
     * Elle utilise des codes ANSI pour colorer les cartes en fonction de leur couleur.
     */
    @Override
    public void afficher() {
        // Codes ANSI pour les couleurs
        String RESET = "\u001B[0m";
        String GREY = "\u001B[48;5;242m";
        List<Carte> main = joueur.getMain();

        if (main.isEmpty()) {
            System.out.println("[Main vide]");
            return;
        }
        for (int i = 0; i < main.size(); i++) {
            String carteAffichee = String.format("%s ? %s", GREY, RESET);
            System.out.print(carteAffichee + " ");
        }
        System.out.println();
    }
}
