package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Special_1 extends AbstractCombinaison {
    
    Integer rank = 0;

    private static final Set<Carte.Valeur> CIBLES = Set.of(
            Carte.Valeur.CINQ, Carte.Valeur.DIX, Carte.Valeur.ROI
    );

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes == null || cartes.size() != 3) {
            return false;
        }

        ArrayList<Carte> sansJoker = new ArrayList<>(
                cartes.stream()
                        .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                        .toList()
        );

        if (sansJoker.isEmpty()) {
            return false;
        }

        Set<Carte.Valeur> valeurs = sansJoker.stream()
                .map(Carte::getValeur)
                .collect(Collectors.toSet());

        return CIBLES.containsAll(valeurs);
    }

    @Override
    public String getNom() {
        return "Special_1 (5-10-K mixtes)";
    }

    @Override
    public Integer getRank() {
        return rank;
    }
}
