package fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.HintVisitor.HintVisitorInterface;

public class StrategieExpert implements StrategieJeu {
    private HintVisitorInterface conseiller;

    public StrategieExpert(HintVisitorInterface conseiller) {
        this.conseiller = conseiller;
    }

    @Override
    public void jouerTour(Joueur joueur, PliController pli) {
        for (int i = 0; i < 300; i++) {
            CombinaisonInterface combinaison = conseiller.conseillerJoueurIAExpert(joueur, pli.getCombinaisonEnCours());

            if (combinaison == null) {
                pli.joueurPasse(joueur);
                return;
            } else {
                if (pli.joueurJoueCombinaison(joueur, combinaison) == false) {
                    continue;
                } else {
                    return;
                }
            }
        }
        pli.joueurPasse(joueur);
    }
}
