package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.List;

public class SingleCombination extends AbstractCombinaison {

    public Integer rank;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes.size() == 1) {
            rank = Carte.cardOrder.get(cartes.get(0).getValeur());
            return true;
        }
        return false;
    }

    @Override
    public String getNom() {
        return "Single";
    }

    @Override
    public Integer getRank() {
        return rank;
    }
    
}
