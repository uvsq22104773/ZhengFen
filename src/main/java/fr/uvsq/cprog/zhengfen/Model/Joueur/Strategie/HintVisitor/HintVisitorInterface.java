package fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.HintVisitor;

import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;

/** Pattern Visitor pour conseiller le coup à jouer. */
public interface HintVisitorInterface {
    CombinaisonInterface conseillerJoueurHumain(Joueur joueur,
            CombinaisonInterface derniereJouee);

    CombinaisonInterface conseillerJoueurIA(Joueur joueur, CombinaisonInterface derniereJouee);

    CombinaisonInterface conseillerJoueurIAExpert(Joueur joueur,
            CombinaisonInterface derniereJouee);
}
