package fr.uvsq.cprog.zhengfen.Model;

import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;

public class ActionHistorique {
    private final Joueur joueur;
    private final Action action;
    private final CombinaisonInterface combinaison;

    public ActionHistorique(Joueur joueur, Action action, CombinaisonInterface combinaison) {
        this.joueur = joueur;
        this.action = action;
        this.combinaison = combinaison;

    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Action getAction() {
        return action;
    }

    public CombinaisonInterface getCombinaison() {
        return combinaison;
    }
}