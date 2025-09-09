package fr.uvsq.cprog.zhengfen.View;

import fr.uvsq.cprog.zhengfen.Model.Carte;

public class AffichageCarte {

    public static String afficherCarte(Carte carte) {
        if (carte == null) {
            return "[Carte nulle]";
        }

        String symbole = getSymboleCouleur(carte.getCouleur());
        String valeur = getSymboleValeur(carte.getValeur());

        return valeur + symbole;
    }

    private static String getSymboleCouleur(Carte.Couleur couleur) {
        if (couleur == null) {
            return "𝓙";
        }
        return switch (couleur) {
          case COEUR -> "♥";
          case CARREAU -> "♦";
          case TREFLE -> "♣";
          case PIQUE -> "♠";
          case SMALL -> "↓";
          case BIG -> "↑";
        };
    }

    private static String getSymboleValeur(Carte.Valeur valeur) {
        return switch (valeur) {
          case AS -> "A";
          case DEUX -> "2";
          case TROIS -> "3";
          case QUATRE -> "4";
          case CINQ -> "5";
          case SIX -> "6";
          case SEPT -> "7";
          case HUIT -> "8";
          case NEUF -> "9";
          case DIX -> "10";
          case VALET -> "J";
          case DAME -> "Q";
          case ROI -> "K";
          case JOKER -> "𝓙";
        };
    }

    public static void afficherMain(java.util.List<Carte> cartes) {
        if (cartes == null || cartes.isEmpty()) {
            System.out.println("[Main vide]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Carte carte : cartes) {
            sb.append("[").append(afficherCarte(carte)).append("] ");
        }
        System.out.println(sb);
    }
}
