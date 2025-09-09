package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.List;

public class BombeCombinaison extends AbstractCombinaison {

    public Integer rank;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes.size() != 4) {
            return false;
        }
        List<Carte> nonJokers = cartes.stream()
                .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                .toList();
        Carte.Valeur valeur = nonJokers.get(0).getValeur();
        rank = Carte.cardOrder.get(valeur);
        return nonJokers.stream().allMatch(c -> c.getValeur() == valeur);
    }

    @Override
    public String getNom() {
        return "Bombe";
    }

    @Override
    public Integer getRank() {
        return rank;
    }
}
