package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Special_2 extends AbstractCombinaison {

    Integer rank = 0;

    private static final Set<Carte.Valeur> CIBLES = Set.of(
            Carte.Valeur.CINQ, Carte.Valeur.DIX, Carte.Valeur.ROI
    );

    @Override
    public boolean estValide(List<Carte> cartes) {
        if (cartes == null || cartes.size() != 3) {
            return false;
        }

        List<Carte> sansJoker = cartes.stream()
                        .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                        .toList();

        if (sansJoker.isEmpty()) {
            return false;
        }

        Set<Carte.Valeur> valeurs = sansJoker.stream()
                .map(Carte::getValeur)
                .collect(Collectors.toSet());

        if (!CIBLES.containsAll(valeurs)) {
            return false;
        }

        Carte.Couleur couleurRef = sansJoker.get(0).getCouleur();
        return sansJoker.stream()
                .allMatch(c -> c.getCouleur() == couleurRef);
    }

    @Override
    public String getNom() {
        return "Special_2 (5-10-K même couleur)";
    }

    @Override
    public Integer getRank() {
        return rank;
    }
}
