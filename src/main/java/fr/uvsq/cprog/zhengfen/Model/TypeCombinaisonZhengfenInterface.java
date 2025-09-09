package fr.uvsq.cprog.zhengfen.Model;

import java.util.List;

/** Interface pour interagir avec les types de combinaisons utilisés dans ZhengFen. */
public interface TypeCombinaisonZhengfenInterface {
    boolean isValid(CombinaisonInterface combination);

    CombinaisonInterface combinationFrom(List<Carte> cartes);

    TypeCombinaisonZhengfenInterface detecter(List<Carte> cartes);
    
    boolean estDuMemeType(CombinaisonInterface comb1, CombinaisonInterface comb2);

    CombinaisonInterface combinationsOfType(CombinaisonInterface comb, List<Carte> cartes);

    CombinaisonInterface generateRandomCombination(List<Carte> main);
}
