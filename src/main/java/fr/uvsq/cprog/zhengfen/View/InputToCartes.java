package fr.uvsq.cprog.zhengfen.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Carte.Couleur;
import fr.uvsq.cprog.zhengfen.Model.Carte.Valeur;

public class InputToCartes {

    private static boolean estCarteValide(String s) {
        // Match général : quelque chose comme "VAL(COULEURS)"
        Matcher m = Pattern.compile("^(10|[2-9JQKA]|JK)\\(([A-Z]+)\\)$").matcher(s);
        if (!m.matches()) {
            return false;
        }

        String valeur = m.group(1);
        String couleurs = m.group(2);

        Set<Character> uniques = new HashSet<>();
        for (char c : couleurs.toCharArray()) {
            if (!uniques.add(c)) {
                return false; // doublon détecté
            }
        }

        if (valeur.equals("JK")) {
            return couleurs.chars().allMatch(c -> c == 'G' || c == 'P');
        } else {
            return couleurs.chars().allMatch(c -> c == 'H' || c == 'D' || c == 'C' || c == 'S');
        }
    }

    private static Valeur convertirValeur(String symbole) {
        return switch (symbole) {
          case "A" -> Valeur.AS;
          case "K" -> Valeur.ROI;
          case "Q" -> Valeur.DAME;
          case "J" -> Valeur.VALET;
          case "2" -> Valeur.DEUX;
          case "3" -> Valeur.TROIS;
          case "4" -> Valeur.QUATRE;
          case "5" -> Valeur.CINQ;
          case "6" -> Valeur.SIX;
          case "7" -> Valeur.SEPT;
          case "8" -> Valeur.HUIT;
          case "9" -> Valeur.NEUF;
          case "10" -> Valeur.DIX;
          case "JK" -> Valeur.JOKER;
          case "JOKER" -> Valeur.JOKER;
          default -> throw new IllegalArgumentException("Symbole de valeur inconnu : " + symbole);
        };
    }

    private static Couleur convertirCouleur(char c) {
        return switch (c) {
          case 'H' -> Couleur.COEUR;
          case 'D' -> Couleur.CARREAU;
          case 'C' -> Couleur.TREFLE;
          case 'S' -> Couleur.PIQUE;
          case 'P' -> Couleur.SMALL;
          case 'G' -> Couleur.BIG;
          default -> throw new IllegalArgumentException("Lettre de couleur inconnue : " + c);
        };
    }

    private static List<String> decouperCartes(String input) {
        return Arrays.stream(input.trim().split("[,\\s]+"))
                    .filter(s -> !s.isEmpty())
                    .toList(); // Java 16+ ; sinon utilisez .collect(Collectors.toList())
    }

    public static List<Carte> inputToCartes(String input) {
        List<String> cartesStr = decouperCartes(input);
        if (cartesStr.size() < 1) {
            return null;
        }
        ArrayList<Carte> cartes = new ArrayList<>();
        for (String carte : cartesStr) {
            if (!estCarteValide(carte)) {
                return null;
            }
            // 1. Extraire la valeur (avant '(')
            String symboleValeur = carte.substring(0, carte.indexOf('('));
            // 2. Extraire les lettres de couleur (entre '(' et ')')
            String couleursCode = carte.substring(carte.indexOf('(') + 1, carte.indexOf(')'));
            Valeur valeur = convertirValeur(symboleValeur);
            for (char c : couleursCode.toCharArray()) {
                cartes.add(new Carte(convertirCouleur(c), valeur));
            }
        }
        return cartes;
    }
}
