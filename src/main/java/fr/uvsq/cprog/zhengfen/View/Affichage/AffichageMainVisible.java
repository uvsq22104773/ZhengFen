package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Carte.Couleur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.View.AffichageCarte;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe pour afficher la main visible d'un joueur.
 * Elle utilise des codes ANSI pour colorer les cartes en fonction de leur couleur.
 */
public class AffichageMainVisible implements ComposantAffichage {
    protected Joueur joueur;
    private static final String RESET = "\u001B[0m";
    private static final String VERT = "\u001B[42m";
    private static final String BLEU = "\u001B[48;5;26m";
    private static final String ROUGE = "\u001B[41m";
    private static final String VIOLET = "\u001B[45m";
    public static final String JAUNE = "\u001B[48;5;214m";

    /**
     * Constructeur de la classe AffichageMainVisible.
     * 
     * 
     * @param joueur Le joueur dont on veut afficher la main.
     */
    public AffichageMainVisible(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Méthode pour afficher la main visible du joueur.
     * Elle utilise des codes ANSI pour colorer les cartes en fonction de leur couleur.
     */
    @Override
    public void afficher() {
        Map<Carte.Couleur, String> couleurCartes = new HashMap<>();
        couleurCartes.put(Couleur.TREFLE, VERT);
        couleurCartes.put(Couleur.PIQUE, BLEU);
        couleurCartes.put(Couleur.CARREAU, ROUGE);
        couleurCartes.put(Couleur.COEUR, VIOLET);
        couleurCartes.put(Couleur.SMALL, JAUNE);
        couleurCartes.put(Couleur.BIG, JAUNE);

        List<Carte> main = joueur.getMain();
        if (main.isEmpty()) {
            System.out.println("[Main vide]");
            return;
        }
        for (Carte carte : main) {
            String couleur = couleurCartes.get(carte.getCouleur());
            String carteAffichee = String.format("%s %s %s", couleur, AffichageCarte.afficherCarte(carte), RESET);
            System.out.print(carteAffichee + " ");
        }
        System.out.println();
    }
}
