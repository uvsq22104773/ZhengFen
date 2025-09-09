package fr.uvsq.cprog.zhengfen.Model.Joueur;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieJeu;

public class JoueurIA extends JoueurAbstrait {
    private final StrategieJeu strategie;

    public JoueurIA(String nom, StrategieJeu strategie) {
        super(nom);
        this.strategie = strategie;
    }

    @Override
    public void jouerTour(PliController pli) {
        strategie.jouerTour(this, pli);
    }
}
