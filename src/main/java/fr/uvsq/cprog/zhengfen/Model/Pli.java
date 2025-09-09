package fr.uvsq.cprog.zhengfen.Model;

import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import java.util.List;

/**
 * Représente un pli dans une manche du jeu.
 */
public class Pli {
    private final List<Joueur> joueurs;
    private CombinaisonInterface combinaisonEnCours;
    private Joueur gagnant;

    /**
     * Constructeur du pli.
     * 
     * 
     * @param joueurs la liste des joueurs participant à la manche
     */
    public Pli(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    /**
     * Récupère la liste des joueurs dans l’ordre du pli.
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Définit la combinaison actuellement sur la table (la plus forte jouée).
     */
    public void setCombinaisonEnCours(CombinaisonInterface combinaison1) {
        this.combinaisonEnCours = combinaison1;
    }

    /**
     * Récupère la combinaison en cours (à battre).
     */
    public CombinaisonInterface getCombinaisonEnCours() {
        return combinaisonEnCours;
    }

    /**
     * Définit le gagnant du pli (le dernier joueur à avoir posé une combinaison valide).
     */
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * Récupère le gagnant du pli.
     */
    public Joueur getGagnant() {
        return gagnant;
    }
}
