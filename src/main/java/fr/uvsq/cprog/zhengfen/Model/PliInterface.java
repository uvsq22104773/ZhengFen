package fr.uvsq.cprog.zhengfen.Model;

import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;

/** Interface permettant d'interagir avec le pli en cours. */
public interface PliInterface {
    void joueurAbandonne(Joueur joueur);

    boolean joueurJoueCombinaison(Joueur joueur, CombinaisonInterface combinaison);

    void joueurPasse(Joueur joueur);

    CombinaisonInterface getCombinaisonEnCours();
}
