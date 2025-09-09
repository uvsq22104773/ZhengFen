package fr.uvsq.cprog.zhengfen.Controleur;

import fr.uvsq.cprog.zhengfen.Model.*;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.View.InputManager;
import fr.uvsq.cprog.zhengfen.View.MenuController;
import fr.uvsq.cprog.zhengfen.View.VuePli;

import java.util.Map;

/**
 * Contrôleur principal de la partie.
 * - Gère la boucle de manches
 * - Met à jour les scores
 * - Affiche le gagnant
 */
public class PartieController {
    private final Partie partie;
    private TypeCombinaisonZhengfenInterface typesCombinaisons = null;
    private Historique historique = Historique.getInstance();

    /**
     * Initialise le contrôleur avec une instance de Partie.
     */
    public PartieController(Partie partie) {
        this.partie = partie;

    }

    /**
     * Lance la partie complète.
     * Joue des manches jusqu'à atteindre un vainqueur.
     */
    public void lancer() {
        historique.addAction(new ActionHistorique(null, Action.COMMENCEPARTIE, null));
        //VuePartie.afficherDebut();

        while (!partie.partieTerminee()) {
            //historique.addAction(new ActionHistorique(null, Action.COMMENCEMANCHE, null));
            //System.out.println("➡️ Manche " + numeroManche);

            // Lancer une nouvelle manche
            MancheController mancheCtrl = new MancheController(partie.getJoueurs());
            if (typesCombinaisons != null) {
                mancheCtrl.attach(typesCombinaisons);
            }
            mancheCtrl.jouer();

            // Récupérer les scores temporaires
            Map<Joueur, Integer> scoresManche = mancheCtrl.getScores();

            // Mettre à jour les scores globaux
            for (Map.Entry<Joueur, Integer> entry : scoresManche.entrySet()) {
                partie.ajouterPoints(entry.getKey(), entry.getValue());
            }
        }

        //VuePartie.afficherFin(partie.getGagnant(), partie.getScores());
        historique.addAction(new ActionHistorique(partie.getGagnant(), Action.GAGNE, null));
        historique.addAction(new ActionHistorique(null, Action.FINPARTIE, null));
        VuePli.afficherFin();
        InputManager.getInstance().lireLigne();
        new MenuController().run();
    }

    /** Attache une interface pour manipuler les combinaisons. */
    public void attach(TypeCombinaisonZhengfenInterface typesCombinaisons) {
        this.typesCombinaisons = typesCombinaisons;
    }
}
