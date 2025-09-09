package fr.uvsq.cprog.zhengfen.Model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Carte {

    public enum Couleur {
        COEUR, CARREAU, TREFLE, PIQUE, SMALL, BIG
    }

    public enum Valeur {
        AS, DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI, JOKER
    }

    private final Couleur couleur;
    private final Valeur valeur;
    public static final Map<Valeur, Integer> cardOrder = new LinkedHashMap<>();

    static {
        cardOrder.put(Valeur.AS, 12);
        cardOrder.put(Valeur.DEUX, 13);
        cardOrder.put(Valeur.TROIS, 1);
        cardOrder.put(Valeur.QUATRE, 2);
        cardOrder.put(Valeur.CINQ, 3);
        cardOrder.put(Valeur.SIX, 4);
        cardOrder.put(Valeur.SEPT, 5);
        cardOrder.put(Valeur.HUIT, 6);
        cardOrder.put(Valeur.NEUF, 7);
        cardOrder.put(Valeur.DIX, 8);
        cardOrder.put(Valeur.VALET, 9);
        cardOrder.put(Valeur.DAME, 10);
        cardOrder.put(Valeur.ROI, 11);
        cardOrder.put(Valeur.JOKER, 14);
    }

    public static final Map<Couleur, Integer> colorOrder = new LinkedHashMap<>();

    static {
        colorOrder.put(Couleur.BIG, 0);
        colorOrder.put(Couleur.SMALL, 1);
        colorOrder.put(Couleur.PIQUE, 2);
        colorOrder.put(Couleur.COEUR, 3);
        colorOrder.put(Couleur.CARREAU, 4);
        colorOrder.put(Couleur.TREFLE, 5);
    }

    public Carte(Couleur couleur, Valeur valeur) {
        this.couleur = couleur;
        this.valeur = valeur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public Valeur getValeur() {
        return valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carte)) return false;
        Carte carte = (Carte) o;
        return valeur == carte.valeur && couleur == carte.couleur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur, couleur);
    }
}
