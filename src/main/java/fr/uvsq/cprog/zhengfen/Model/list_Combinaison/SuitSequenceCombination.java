package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.Carte.Couleur;
import fr.uvsq.cprog.zhengfen.Model.Carte.Valeur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuitSequenceCombination extends AbstractCombinaison {

    Integer rank;

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes.size() < 5) {
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        Set<Valeur> detectDoublon = new HashSet<>();
        Couleur couleur = null;
        Integer jokers = 0;
        for (Carte carte : cartes) {
            if (carte.getValeur() == Carte.Valeur.JOKER) {
                jokers++;
            } else {
                if (!detectDoublon.add(carte.getValeur())) {
                    return false;
                }
                list.add(Carte.cardOrder.get(carte.getValeur())); 
                if (couleur == null) {
                    couleur = carte.getCouleur();
                } else if (!carte.getCouleur().equals(couleur)) {
                    return false;
                }
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size() - 1; i++) {
            int diff = list.get(i + 1) - list.get(i);
            if (diff == 3 && jokers == 2) {
                jokers = 0;
            } else if (diff == 2 && jokers >= 1) {
                jokers--;
            } else if (diff == 1) {
                continue;
            } else {
                return false;
            }
        }
        rank = 100 * cartes.size() + list.get(list.size() - 1);
        return true;
    }

    @Override
    public String getNom() {
        return "SuitSequence";
    }

    @Override
    public Integer getRank() {
        return rank;
    }    
}
