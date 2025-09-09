package fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.View.InputManager;
import fr.uvsq.cprog.zhengfen.View.InputToCartes;
import fr.uvsq.cprog.zhengfen.View.Affichage.ClearTerminal;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaisonZhengfenInterface;
import java.util.List;

/** Human strategy for zheng fen. */
public class StrategieHumaine implements StrategieJeu {
    private final InputManager inputManager = InputManager.getInstance();
    private final TypeCombinaisonZhengfenInterface typesCombinaison;

    public StrategieHumaine(TypeCombinaisonZhengfenInterface types) {
        this.typesCombinaison = types;
    }

    @Override
    public void jouerTour(Joueur joueur, PliController pli) {
        boolean tourTermine = false;

        while (!tourTermine) {
            String choix = inputManager.lireLigne().trim().toUpperCase();
            switch (choix) {
              case "PASS" -> {
                  pli.joueurPasse(joueur);
                  tourTermine = true;
              }
              case "ABANDON" -> {
                  pli.joueurAbandonne(joueur);
                  tourTermine = true;
              }
              case "STOP" -> {
                ClearTerminal.clear();
                System.out.println("Merci d'avoir joué !"); 
                System.exit(0);
              }
              case "?" -> System.out.println("Non implémenté");
              default -> {
                    List<Carte> cartesChoisies = InputToCartes.inputToCartes(choix);
                    if (cartesChoisies != null) {
                        if (joueur.getMain().containsAll(cartesChoisies)) {
                            CombinaisonInterface combinaison = typesCombinaison.combinationFrom(cartesChoisies);;
                            if (combinaison != null && pli.joueurJoueCombinaison(joueur, combinaison)) {
                                tourTermine = true;
                            } else {
                                System.out.println("Combinaison invalide ou non permise.");
                            }
                        } else {
                            System.out.println("Vous ne possédez pas ces cartes.");
                        }
                    }
                }
            }
        }
    }
}