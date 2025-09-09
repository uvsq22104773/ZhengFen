package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Carte.Valeur;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullHouseCombination extends AbstractCombinaison {

    Integer rank;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes.size() != 5) {
            return false;
        }
        Map<Valeur, Integer> cardMap = new HashMap<>();
        for (Carte carte : cartes) {
            cardMap.put(carte.getValeur(), cardMap.getOrDefault(carte.getValeur(), 0) + 1);
        }
        Integer jokers = cardMap.getOrDefault(Carte.Valeur.JOKER, 0);
        if (jokers == 2) {
            rank = cartes.stream()
                .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                .max(Comparator.comparingInt(c -> Carte.cardOrder.get(c.getValeur())))
                .map(c -> Carte.cardOrder.get(c.getValeur()))
                .get();
            return true;
        } else if (jokers == 1) {
            if (cardMap.size() == 2 || cardMap.size() == 5) {
                return false;
            } else {
                Integer temp = 0;
                for (Map.Entry<Valeur, Integer> entry : cardMap.entrySet()) {
                    if (entry.getValue() == 3) {
                        rank = Carte.cardOrder.get(entry.getKey());
                    } else if (entry.getValue() == 2) {
                        if (Carte.cardOrder.get(entry.getKey()) > temp) {
                            temp = Carte.cardOrder.get(entry.getKey());
                        }
                    }
                }
                if (temp != 0) {
                    rank = temp;
                }
                return true;
            }
        } else {
            if (cardMap.size() == 3) {
                for (Map.Entry<Valeur, Integer> entry : cardMap.entrySet()) {
                    if (entry.getValue() == 3) {
                        rank = Carte.cardOrder.get(entry.getKey());
                        return true;
                    }
                }
                return false;
            } else if (cardMap.size() == 2) {
                rank = Carte.cardOrder.get(
                    cardMap.keySet().stream()
                        .max(Comparator.comparingInt(valeur -> Carte.cardOrder.get(valeur)))
                        .get()
                );
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String getNom() {
        return "FullHouse";
    }
    
    @Override
    public Integer getRank() {
        return rank;
    }
}
