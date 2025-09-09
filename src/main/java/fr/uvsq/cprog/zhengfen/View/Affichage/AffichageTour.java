package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.ActionHistorique;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import fr.uvsq.cprog.zhengfen.Model.Historique;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.View.ModeAffichage;
import java.util.HashMap;
import java.util.List;

public class AffichageTour {
    private final List<Joueur> joueurs;
    private final OptionManager optionManager;
    private final HashMap<Joueur, String> couleursJoueurs;
    private final String RESET;
    public final String GRAS;
    private Historique historique;;

    public AffichageTour(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.optionManager = OptionManager.getInstance();
        this.historique = Historique.getInstance();

        this.RESET = "\u001B[0m"; // Reset ANSI color
        this.GRAS= "\u001B[1m"; // Bold ANSI color
        List<String> couleurs = List.of("\u001B[31m", "\u001B[32m",
            "\u001B[33m", "\u001B[34m"); // rouge, vert, jaune, bleu

        this.couleursJoueurs = new HashMap<>();
        for (int i = 0; i < joueurs.size(); i++) {
            couleursJoueurs.put(joueurs.get(i), couleurs.get(i));
        }
    }

    public void afficher(Joueur joueurActuel, CombinaisonInterface pli, ModeAffichage mode) {
        ClearTerminal.initialiserAffichage();
        System.out.println(String.format("=== ACTIONS PASSÉES ==="));
        List<ActionHistorique> lastActions = historique.getLastAction(6);
        for (ActionHistorique action : lastActions) {
            switch (action.getAction()) {
              case JOUE:
                  System.out.print(String.format("- %s%s%s a joué ", couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET));
                  new AffichageCombinaison(action.getCombinaison()).afficher();
                  break;
              case COMMENCEPARTIE:
                  System.out.println(String.format("- La partie a commencé"));
                  break;
              case FINPARTIE:
                    System.out.println(String.format("- La partie est terminée"));
                    break;
              case PASSE:
                  System.out.println(String.format("- %s%s%s a passé son tour", couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET));
                  break;
              case ABANDON:
                  System.out.println(String.format("- %s%s%s a abandonné", couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET));
                  break;
              case COMMENCEMANCHE:
                  System.out.println(String.format("- Une nouvelle manche a commencé"));
                  break;
              case FINMANCHE:
                  System.out.println(String.format("- La manche est terminée"));
                  break;
              case REMPORTEPLI:
                  System.out.println(String.format("- %s%s%s a remporté le pli", couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET));
                  break;
              case COMMENCEPLI:
                  System.out.println(String.format("- %s%s%s a commencé le pli", couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET));
                  break;
              case REMPORTEMANCHE:
                  System.out.println(String.format("- %s%s%s a récupéré les plis du dernier joueur à avoir terminé la manche", couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET));
                  break;
              case GAGNE:
                  System.out.println(String.format("- %s%s%s%s %sa gagné la partie%s", GRAS, couleursJoueurs.get(action.getJoueur()), 
                      action.getJoueur().getNom(), RESET, GRAS, RESET));
                  break;
              default:
                  System.out.println(String.format("- %s%s%s a effectué une action inconnue", couleursJoueurs.get(action.getJoueur()),
                      action.getJoueur().getNom(), RESET));
                  break;
            }
        }
        for (int i = 0; i < 6 - lastActions.size(); i++) {
            System.out.println("");
        }
        System.out.println("");
        System.out.println(String.format("=== ÉTAT DES JOUEURS ==="));
        int max = 0;
        for (Joueur j : joueurs) {
            if (j.getNom().length() > max) {
                max = j.getNom().length();
            }
        }
        for (Joueur joueur : joueurs) {
            String couleur = couleursJoueurs.get(joueur);
            if (joueur.getAbandon()) {
                System.out.println(String.format("%s%-" + max + "s%s : %4d pts   | Abandon", couleur, 
                    joueur.getNom(), RESET, joueur.getPoints()));
            } else {
                System.out.print(String.format("%s%-" + max + "s%s : %4d pts   | Main : ", 
                    couleur, joueur.getNom(), RESET, joueur.getPoints()));
                (optionManager.isDebogage() ? new AffichageMainVisible(joueur)
                : new AffichageMainCache(joueur)).afficher();
            }
        }
        System.out.println("");
        System.out.println(String.format("%s=== PLIS EN COURS ===%s", GRAS, RESET));
        new AffichageCombinaison(pli).afficher();
        System.out.println();

        System.out.println(String.format("%s=== À %s%s%s %sDE JOUER ===%s", GRAS,
                couleursJoueurs.get(joueurActuel), joueurActuel.getNom(), RESET, GRAS, RESET));
        System.out.print(String.format("%sMain : %s", GRAS, RESET));
        switch (mode) {
          case CACHE:
              new AffichageMainCache(joueurActuel).afficher();
              break;
          default:
              new AffichageMainVisible(joueurActuel).afficher();
              break;
        }
        System.out.println();
    }
}