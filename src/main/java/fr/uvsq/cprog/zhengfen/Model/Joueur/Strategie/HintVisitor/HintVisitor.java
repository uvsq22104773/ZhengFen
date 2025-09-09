package fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.HintVisitor;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaisonZhengfenInterface;
import java.util.List;

/** Classe implémentant HintVisitorInterface pour le jeu zhengfen. */
public class HintVisitor implements HintVisitorInterface {

    private final int NB_TRIALS = 30;
    private final TypeCombinaisonZhengfenInterface combinationTypes;

    /** Constructor. */
    public HintVisitor() {
        this.combinationTypes = null;
    }

    /** Constructor linking combinationType interface. */
    public HintVisitor(TypeCombinaisonZhengfenInterface combinationTypes) {
        this.combinationTypes = combinationTypes;
    }

    /** Conseille une combinaison pour un joueur humain pour le jeu zheng fen. */
    public CombinaisonInterface conseillerJoueurHumain(Joueur joueur,
            CombinaisonInterface derniereJouee) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /** Conseille une combinaison pour un joueur IA classique. */
    @Override
    public CombinaisonInterface conseillerJoueurIA(Joueur joueur, CombinaisonInterface derniereJouee) {
        if (joueur.mainVide()) {
            return null;
        }

        List<Carte> main = joueur.getMain();
        CombinaisonInterface meilleure = null;

        for (Carte carte : main) {
            List<Carte> candidate = List.of(carte);
            CombinaisonInterface combinaison = combinationTypes.combinationFrom(candidate);

            if (combinaison == null || combinaison.getRank() == null) {
                continue;
            }

            Integer rankComb = combinaison.getRank();

            if (derniereJouee == null) {
                if (meilleure == null || meilleure.getRank() == null || rankComb < meilleure.getRank()) {
                    meilleure = combinaison;
                }
            } else {
                Integer rankDerniere = derniereJouee.getRank();
                if (rankDerniere != null
                        && combinationTypes.estDuMemeType(combinaison, derniereJouee)
                        && rankComb >= rankDerniere) { 
                    if (meilleure == null || meilleure.getRank() == null || rankComb < meilleure.getRank()) {
                        meilleure = combinaison;
                    }
                }
            }
        }

        return meilleure;
    }

    /** Conseille une combinaison pour un joueur IA expert. */
    public CombinaisonInterface conseillerJoueurIAExpert(Joueur joueur,
            CombinaisonInterface derniereJouee) {
        CombinaisonInterface comb = null;
        if (derniereJouee == null) {
            for (int trial = 0; trial < NB_TRIALS && comb == null; trial++) {
                comb = combinationTypes.generateRandomCombination(joueur.getMain());
            }
            return comb;
        }
        else {
            int trial_no = 0;
            while (comb == null && trial_no < this.NB_TRIALS) {
                comb = combinationTypes.combinationsOfType(derniereJouee, joueur.getMain());
                trial_no++;
            }
            return comb;
        } 
    }
}
