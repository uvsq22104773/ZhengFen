package fr.uvsq.cprog.zhengfen.Model.Joueur;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.Carte;
import java.util.List;

public interface Joueur {
    String getNom();

    List<Carte> getMain();

    void ajouterCarte(Carte carte);

    void ajouterCartes(List<Carte> cartes);

    void retirerCarte(Carte carte);

    void retirerCartes(List<Carte> cartes);

    void ajoutVictoire();

    int getVictoires();

    void ajoutPoints(int points);

    void retirerPoints(int points);

    int getPoints();

    void setPoints(int points);

    boolean mainVide();

    void abandon();

    boolean getAbandon();

    void afficherMain();
    
    void jouerTour(PliController pli);
}
