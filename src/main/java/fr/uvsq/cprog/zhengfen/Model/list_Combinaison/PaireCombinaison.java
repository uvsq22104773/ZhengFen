package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.List;

public class PaireCombinaison extends AbstractCombinaison {

    public Integer rank;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes.size() != 2) {
            return false;
        }
        List<Carte> nonJokers = cartes.stream()
                .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                .toList();
        if (nonJokers.isEmpty()) {
            rank = Carte.cardOrder.get(Carte.Valeur.JOKER);
            return true;
        }
        Carte.Valeur valeur = nonJokers.get(0).getValeur();
        rank = Carte.cardOrder.get(valeur);
        return nonJokers.stream().allMatch(c -> c.getValeur() == valeur);
    }

    @Override
    public String getNom() {
        return "Pair";
    }

    @Override
    public Integer getRank() {
        return rank;
    }
}
