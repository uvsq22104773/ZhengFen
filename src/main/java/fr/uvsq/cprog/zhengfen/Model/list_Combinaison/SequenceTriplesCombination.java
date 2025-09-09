package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Carte.Valeur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SequenceTriplesCombination extends AbstractCombinaison {

    Integer rank;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes.isEmpty() || cartes.size() % 3 != 0 || cartes.size() < 9) {
            return false;
        }
        Map<Valeur, Integer> cardMap = new HashMap<>();
        for (Carte carte : cartes) {
            cardMap.put(carte.getValeur(), cardMap.getOrDefault(carte.getValeur(), 0) + 1);
        }
        Integer jokers = cardMap.getOrDefault(Carte.Valeur.JOKER, 0);
        // on rajoute les cartes remplacées par les jokers dans la map
        for (Map.Entry<Valeur, Integer> entry : cardMap.entrySet()) {
            if (entry.getKey() == Carte.Valeur.JOKER) {
                continue;
            }
            if (entry.getValue() > 3) {
                return false;
            }
            while (entry.getValue() < 3 && jokers > 0) {
                jokers--;
                entry.setValue(entry.getValue() + 1);
                cardMap.put(Carte.Valeur.JOKER, cardMap.get(Carte.Valeur.JOKER) - 1);
                
            }
            if (entry.getValue() < 3 && jokers == 0) {
                return false;
            }
        }
        if (cardMap.getOrDefault(Carte.Valeur.JOKER, -1) == 0) {
            cardMap.remove(Carte.Valeur.JOKER);
        }

        ArrayList<Integer> list = new ArrayList<>();
        cardMap.keySet().forEach(val -> {
            list.add(Carte.cardOrder.get(val));
        });
        Collections.sort(list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) != list.get(i + 1) - 1) {
                return false;
            }
        }
        rank = 100 * cartes.size() / 3 + list.get(list.size() - 1);
        return true;
    }

    @Override
    public String getNom() {
        return "SequenceOfTriples";
    }
    
    @Override
    public Integer getRank() {
        return rank;
    }
}
