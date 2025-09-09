package fr.uvsq.cprog.zhengfen.Model.Joueur;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Classe abstraite regroupant les comportements partagés par tous les types de
 * joueurs.
 */
public abstract class JoueurAbstrait implements Joueur {
    protected final String nom;
    protected final List<Carte> main = new ArrayList<>();
    protected int victoires = 0;
    protected int points = 0;
    protected boolean abandon = false;

    public JoueurAbstrait(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public List<Carte> getMain() {
        return List.copyOf(main);
    }

    @Override
    public void ajouterCarte(Carte carte) {
        main.add(carte);
        sortByValue(main);
    }

    @Override
    public void ajouterCartes(List<Carte> cartes) {
        main.addAll(cartes);
        sortByValue(main);
    }

    @Override
    public void retirerCarte(Carte carte) {
        main.remove(carte);
    }

    @Override
    public void retirerCartes(List<Carte> cartes) {
        main.removeAll(cartes);
    }

    @Override
    public void ajoutVictoire() {
        victoires++;
    }

    @Override
    public int getVictoires() {
        return victoires;
    }

    @Override
    public void ajoutPoints(int points) {
        this.points += points;
    }

    @Override
    public void retirerPoints(int points) {
        this.points -= points;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean mainVide() {
        return main.isEmpty();
    }

    @Override
    public void abandon() {
        abandon = true;
    }

    @Override
    public boolean getAbandon() {
        return abandon;
    }

    @Override
    public void afficherMain() {
        System.out.println(nom + " a les cartes :");
        main.forEach(c -> System.out.println(c.getValeur() + " de " + c.getCouleur()));
    }

    private List<Carte> sortByValue(List<Carte> cartes) {
        // Tri par valeur puis par couleur
        cartes.sort(Comparator
            .comparingInt((Carte c) -> Carte.cardOrder.get((c.getValeur()))) // Trier par valeur
            .thenComparingInt(c -> Carte.colorOrder.get((c.getCouleur())))); // Puis par couleur
        return cartes;
    }
}
