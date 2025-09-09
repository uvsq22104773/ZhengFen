package fr.uvsq.cprog.zhengfen.Controleur;

import fr.uvsq.cprog.zhengfen.Model.*;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur de manche :
 * Gère la distribution, les plis successifs, le scoring temporaire.
 */
public class MancheController {
    private final Manche manche;
    private TypeCombinaisonZhengfenInterface typesCombinaisons;
    private final Historique historique = Historique.getInstance();

    public MancheController(List<Joueur> joueurs) {
        this.manche = new Manche(joueurs);
        this.typesCombinaisons = TypeCombinaison.SINGLE;
    }

    public void jouer() {
        historique.addAction(new ActionHistorique(null, Action.COMMENCEMANCHE, null));

        Dealer dealer = new Dealer();
        List<List<Carte>> mains = dealer.distribuerCartes(manche.getJoueurs().size());

        for (int i = 0; i < manche.getJoueurs().size(); i++) {
            manche.getJoueurs().get(i).ajouterCartes(mains.get(i));
        }

        boolean mancheTerminee = false;

        Joueur joueurStarter = manche.getJoueurs().get(0);

        while (!mancheTerminee) {
            PliController pliCtrl = new PliController(manche.getJoueurs());
            pliCtrl.attach(typesCombinaisons);
            Map.Entry<Joueur, Integer> resultat = pliCtrl.jouer(joueurStarter);

            joueurStarter = resultat.getKey();

            if (resultat != null) {
                manche.ajouterPoints(resultat.getKey(), resultat.getValue());
            }

            // Vérifier si tous sauf un ont fini
            long encoreEnJeu = manche.getJoueurs().stream().filter(j -> !j.mainVide()).count();
            if (encoreEnJeu == 1) {
                mancheTerminee = true;
            }

            // Sauvegarder qui a fini en premier (utile pour le transfert)
            if (manche.getPremierFini() == null) {
                manche.setPremierFini(
                        manche.getJoueurs().stream().filter(Joueur::mainVide).findFirst().orElse(null)
                );
            }
        }

        // Dernier joueur donne ses cartes au premier
        Joueur dernier = manche.getJoueurs().stream().filter(j -> !j.mainVide()).findFirst().orElse(null);
        manche.setDernierReste(dernier);

        if (dernier != null && manche.getPremierFini() != null && !dernier.equals(manche.getPremierFini())) {
            for (Carte carte : dernier.getMain()) {
                if (carte.getValeur() == Carte.Valeur.CINQ) {
                    manche.ajouterPoints(manche.getPremierFini(), 5);
                } else if (carte.getValeur() == Carte.Valeur.DIX) {
                    manche.ajouterPoints(manche.getPremierFini(), 10);
                } else if (carte.getValeur() == Carte.Valeur.ROI) {
                    manche.ajouterPoints(manche.getPremierFini(), 10);
                }
            }
        }

        manche.ajouterPoints(manche.getPremierFini(), manche.getScore(dernier));
        manche.getPremierFini().ajoutPoints(manche.getScore(dernier));
        dernier.retirerPoints(manche.getScore(dernier));
        manche.resetScores(dernier);

        historique.addAction(new ActionHistorique(manche.getPremierFini(), Action.REMPORTEMANCHE, null));
        historique.addAction(new ActionHistorique(null, Action.FINMANCHE, null));
    }

    /**
     * Retourne le score temporaire de chaque joueur (à transférer dans Partie).
     */
    public Map<Joueur, Integer> getScores() {
        return manche.getScoresTemp();
    }

    /** Attache une interface pour manipuler les combinaisons. */
    public void attach(TypeCombinaisonZhengfenInterface typesCombinaisons) {
        this.typesCombinaisons = typesCombinaisons;
    }
}
