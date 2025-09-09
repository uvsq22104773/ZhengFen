package fr.uvsq.cprog.zhengfen.Model.Joueur;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieJeu;

public class JoueurHumain extends JoueurAbstrait {
    private final StrategieJeu strategie;

    public JoueurHumain(String nom, StrategieJeu strategie) {
        super(nom);
        this.strategie = strategie;
    }

    @Override
    public void jouerTour(PliController pli) {
        strategie.jouerTour(this, pli);
    }
}
