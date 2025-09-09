package fr.uvsq.cprog.zhengfen.Model;

import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contient les données d’une manche.
 * - Joueurs
 * - Premier joueur à finir
 * - Dernier joueur restant
 * - Score temporaire par joueur pour cette manche
 */
public class Manche {
    private final List<Joueur> joueurs;
    private Joueur premierFini;
    private Joueur dernierReste;
    private final Map<Joueur, Integer> scoresTemp = new HashMap<>();

    public Manche(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        joueurs.forEach(j -> scoresTemp.put(j, 0));
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public Joueur getPremierFini() {
        return premierFini;
    }

    public void setPremierFini(Joueur premierFini) {
        this.premierFini = premierFini;
    }

    public Joueur getDernierReste() {
        return dernierReste;
    }

    public void setDernierReste(Joueur dernierReste) {
        this.dernierReste = dernierReste;
    }

    public Map<Joueur, Integer> getScoresTemp() {
        return scoresTemp;
    }

    public void ajouterPoints(Joueur joueur, int points) {
        scoresTemp.put(joueur, scoresTemp.get(joueur) + points);
    }

    public int getScore(Joueur joueur) {
        return scoresTemp.get(joueur);
    }

    public void resetScores(Joueur joueur) {
        scoresTemp.put(joueur, 0);
    }
}
