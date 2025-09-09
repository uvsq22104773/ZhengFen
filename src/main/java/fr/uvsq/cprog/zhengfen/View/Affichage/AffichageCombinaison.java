package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Carte.Couleur;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.View.AffichageCarte;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe pour afficher un pli.
 * Elle utilise des codes ANSI pour colorer les cartes en fonction de leur couleur.
 */
public class AffichageCombinaison implements ComposantAffichage {
    private CombinaisonInterface combinaison;
    private static final String RESET = "\u001B[0m";
    private static final String VERT = "\u001B[42m";
    private static final String BLEU = "\u001B[48;5;26m";
    private static final String ROUGE = "\u001B[41m";
    private static final String VIOLET = "\u001B[45m";
    private static final String JAUNE = "\u001B[48;5;214m";
    private static final String FOND_GRIS = "\u001B[48;5;250m";
    private static final String TEXTE_GRIS = "\u001B[38;5;255m";

    /**
     * Constructeur de la classe AffichagePli.
     *
     * @param pli Le pli à afficher.
     */
    public AffichageCombinaison(CombinaisonInterface combinaison) {
        this.combinaison = combinaison;
    }

    /**
     * Méthode pour afficher le pli.
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

        if (this.combinaison == null) {
            System.out.println(String.format("%s%s -- %s %s%s -- %s %s%s -- %s %s%s -- %s",
                FOND_GRIS, TEXTE_GRIS, RESET, FOND_GRIS, TEXTE_GRIS, RESET, FOND_GRIS, TEXTE_GRIS,
                RESET, FOND_GRIS, TEXTE_GRIS, RESET));
            return;
        }
        List<Carte> cartes = this.combinaison.getCartes();

        for (Carte carte : cartes) {
            String couleur = couleurCartes.get(carte.getCouleur());
            String carteAffichee = String.format("%s %s %s", couleur, AffichageCarte.afficherCarte(carte), RESET);
            System.out.print(carteAffichee + " ");
        }
        System.out.println();
    }


}
