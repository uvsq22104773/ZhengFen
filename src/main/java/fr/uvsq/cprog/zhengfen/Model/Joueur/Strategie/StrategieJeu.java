package fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;

public interface StrategieJeu {
    void jouerTour(Joueur joueur, PliController pli);
}
