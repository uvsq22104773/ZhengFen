package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.List;

public class Special_3 extends AbstractCombinaison {

    Integer rank = 0;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes == null || cartes.size() != 4) {
            return false;
        }

        return cartes.stream()
                .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                .allMatch(c -> c.getValeur() == Carte.Valeur.DEUX);
    }

    @Override
    public String getNom() {
        return "Special_3 (4x Deux)";
    }

    @Override
    public Integer getRank() {
        return rank;
    }
}
