package fr.uvsq.cprog.zhengfen.View;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import java.util.List;

/**
 * Affiche toutes les informations liées à un pli.
 * - main du joueur
 * - combinaison en cours
 * - instructions
 * - gagnant du pli
 */
public class VuePli {

    public static void afficherMain(Joueur joueur) {
        System.out.println("\nMain de " + joueur.getNom() + " :");

        List<Carte> main = joueur.getMain();
        for (int i = 0; i < main.size(); i++) {
            Carte c = main.get(i);
            String symbole = switch (c.getCouleur()) {
              case COEUR -> "♥";
              case CARREAU -> "♦";
              case TREFLE -> "♣";
              case PIQUE -> "♠";
              case SMALL -> "𝓙↓";
              case BIG -> "𝓙↑";
            };

            String valeur = (c.getValeur() == Carte.Valeur.JOKER)
                    ? symbole
                    : c.getValeur().name().charAt(0) + symbole;

            System.out.print((i + 1) + ": \u001B[41m " + valeur + " \u001B[0m  ");
        }
        System.out.println();
    }

    public static void afficherCombinaisonEnCours(CombinaisonInterface combinaison) {
        if (combinaison == null) {
            System.out.println("Aucune combinaison n’a encore été jouée.");
        } else {
            System.out.println("Combinaison à battre : " + combinaison);
        }
    }

    public static void afficherInstructions() {
        System.out.println("""
            Que voulez-vous faire ?
            - Saisir une combinaison de cartes à jouer 
                (ex: 3(HCDS) → 3♥ + 3♣ + 3♦ + 3♠, JK(GP) → 𝓙↑ + 𝓙↓)
            - 'pass'    pour passer
            - '?'       pour l'aide
            - 'abandon' pour abandonner le tour
            - 'stop'    pour quitter le jeu
            """);
    }

    public static void afficherAide() {
        System.out.println("""
            Aide :
            - Vous devez battre la combinaison actuelle avec une du même type.
            - Si vous ne pouvez pas, tapez 'pass'.
            """);
    }

    public static void annoncerPliGagne(Joueur gagnant, int points) {
        System.out.println("→ " + gagnant.getNom() + " remporte le pli et marque " + points + " points !");
    }

    public static void afficherAttente() {
        System.out.println("Appuyez sur 'Entrée' pour continuer...");
    }

    public static void afficherFin() {
        System.out.println("Fin de la partie, faite 'entrer' pour revenir au menu principal...");
    }
}
