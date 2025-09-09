package fr.uvsq.cprog.zhengfen.Model;

import java.util.ArrayList;

/**
 * Représente une combinaison jouée par un joueur (ex : paire, suite, etc.).
 * Contient la liste des cartes et leur force/type via ForceCombinaison.
 */
public class Combinaison {
    private final ArrayList<Carte> cartes;
    private final ForceCombinaison forceCombinaison;

    public Combinaison(ArrayList<Carte> cartes) {
        this.cartes = new ArrayList<>(cartes);
        this.forceCombinaison = new ForceCombinaison(cartes); // construit automatiquement type + force
    }

    public boolean estValide() {
        return forceCombinaison != null;
    }

    public ForceCombinaison getForceCombinaison() {
        return forceCombinaison;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    @Override
    public String toString() {
        return forceCombinaison.getType() + " : " + cartes;
    }

    /**
     * Vérifie si deux combinaisons sont du même type (ex : toutes deux "Paire",
     * "Suite", etc.).
     */
    public boolean estDuMemeType(Combinaison autre) {
        if (autre == null) {
            return false;
        }
        return this.forceCombinaison.getType().equals(autre.forceCombinaison.getType());
    }

    /**
     * Vérifie si cette combinaison bat l’autre (même type + force supérieure).
     */
    public boolean bat(Combinaison autre) {
        if (autre == null) {
            return true;
        }
        if (!estDuMemeType(autre)) {
            return false;
        }
        return this.forceCombinaison.estPlusForteQue(autre.getForceCombinaison());
    }
}
