package fr.uvsq.cprog.zhengfen.Model;

import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurHumain;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurIA;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieExpert;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieHumaine;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieNormale;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.HintVisitor.HintVisitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente une partie complète du jeu ZhengFen.
 * Gère les scores cumulés des joueurs et la condition de victoire.
 */
public class Partie {
    private final List<Joueur> joueurs;
    private final int scoreCible;
    private final Map<Joueur, Integer> scores;

    /**
     * Initialise la partie avec une liste de joueurs et un score cible à atteindre.
     */
    public Partie(List<Joueur> joueurs, int scoreCible) {
        this.joueurs = joueurs;
        this.scoreCible = scoreCible;
        this.scores = new HashMap<>();
        joueurs.forEach(j -> scores.put(j, 0));
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public int getScoreCible() {
        return scoreCible;
    }

    public Map<Joueur, Integer> getScores() {
        return scores;
    }

    public int getScoreJoueur(Joueur joueur) {
        return scores.get(joueur);
    }

    /**
     * Ajoute des points au score global du joueur.
     */
    public void ajouterPoints(Joueur joueur, int points) {
        scores.put(joueur, scores.get(joueur) + points);
    }

    /**
     * Vérifie si au moins un joueur a atteint ou dépassé le score cible.
     */
    public boolean partieTerminee() {
        return scores.values().stream().anyMatch(score -> score >= scoreCible);
    }

    /**
     * Retourne le premier joueur ayant atteint le score cible.
     */
    public Joueur getGagnant() {
        return scores.entrySet().stream()
                .filter(e -> e.getValue() >= scoreCible)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static Partie creer() {
        OptionManager options = OptionManager.getInstance();
        int nbJoueurs = options.getNombreDeJoueurs();
        int nbIa = options.getNombreIa();
        HashMap<String, Integer> difficulte = options.getDifficulte();
        ArrayList<Joueur> joueurs = new ArrayList<>();
        for (int i = 1; i <= nbJoueurs-nbIa; i++) {
            joueurs.add(new JoueurHumain("J" + i, new StrategieHumaine(TypeCombinaison.SINGLE)));
        }
        difficulte.keySet().stream()
        .sorted(Comparator.comparingInt(cle -> Integer.parseInt(cle.replaceAll("\\D", ""))))
        .forEach(cle -> {
            int valeur = difficulte.get(cle);
            if (valeur == 1) {
                joueurs.add(new JoueurIA(cle, new StrategieNormale(new HintVisitor(TypeCombinaison.SINGLE))));
            } else {
                joueurs.add(new JoueurIA(cle, new StrategieExpert(new HintVisitor(TypeCombinaison.SINGLE))));
            }
        });
        int scoreCible = options.getNombreDePointsVictoire();

        return new Partie(joueurs, scoreCible);
    }
}
