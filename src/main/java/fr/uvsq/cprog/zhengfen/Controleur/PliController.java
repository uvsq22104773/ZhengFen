package fr.uvsq.cprog.zhengfen.Controleur;

import fr.uvsq.cprog.zhengfen.Model.*;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurHumain;
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageTour;
import fr.uvsq.cprog.zhengfen.View.InputManager;
import fr.uvsq.cprog.zhengfen.View.ModeAffichage;
import fr.uvsq.cprog.zhengfen.View.VuePli;
import java.util.*;

/**
 * Gère un pli (séquence jusqu'à ce que tous sauf un aient passé).
 * Implémente l'interface PliInterface utilisée par les stratégies.
 */
public class PliController implements PliInterface {
    private final Pli pli;
    private final Map<Joueur, Boolean> aPasse;
    private Joueur dernierJoueurActif;
    private final List<Carte> cartesDuPli = new ArrayList<>();
    private TypeCombinaisonZhengfenInterface typesCombinaison;
    private final AffichageTour affichageTour;
    private final Historique historique = Historique.getInstance();
    private final InputManager inputManager = InputManager.getInstance();

    public PliController(List<Joueur> joueurs) {
        this.pli = new Pli(joueurs);
        this.aPasse = new HashMap<>();
        this.affichageTour = new AffichageTour(joueurs);
        for (Joueur j : joueurs) {
            aPasse.put(j, false);
        }
    }

    /**
     * Lance un pli jusqu'à ce que tous les joueurs sauf un passent.
     * 
     * 
     * @return (gagnant, points gagnés)
     */
    public Map.Entry<Joueur, Integer> jouer(Joueur joueurStarter) {
        CombinaisonCourante.reset();
        int index = pli.getJoueurs().indexOf(joueurStarter);
        while (pli.getJoueurs().get(index).getMain().isEmpty()) {
            index = (index + 1) % pli.getJoueurs().size();
        }
        boolean premierCoup = true;

        while (true) {
            Joueur joueur = pli.getJoueurs().get(index);

            if (!joueur.getAbandon() && !joueur.mainVide()) {
                affichageTour.afficher(joueur, pli.getCombinaisonEnCours(), ModeAffichage.CACHE);
                VuePli.afficherAttente();
                inputManager.lireLigne();

                if (joueur instanceof JoueurHumain) {
                    affichageTour.afficher(joueur, pli.getCombinaisonEnCours(), ModeAffichage.VISIBLE);
                    VuePli.afficherInstructions();
                }

                joueur.jouerTour(this); // Appelle stratégie

                CombinaisonInterface tentative = CombinaisonCourante.get();

                if (tentative != null) {
                    boolean estValide = premierCoup
                            || (typesCombinaison.estDuMemeType(tentative, pli.getCombinaisonEnCours())
                                    && tentative.getRank() > pli.getCombinaisonEnCours().getRank());

                    if (estValide) {
                        pli.setCombinaisonEnCours(tentative);
                        historique.addAction(new ActionHistorique(joueur, Action.JOUE, tentative));
                        pli.setGagnant(joueur);
                        dernierJoueurActif = joueur;
                        aPasse.replaceAll((j, b) -> false);
                        cartesDuPli.addAll(tentative.getCartes());
                        joueur.retirerCartes(tentative.getCartes());
                        premierCoup = false;
                    } else {
                        aPasse.put(joueur, true);
                        System.out.println("Combinaison invalide. Vous passez.");
                    }
                }

                if (seulJoueurActifAvecCartesRestantes()
                    || joueur.equals(dernierJoueurActif) && tousOntPasseSauf(dernierJoueurActif)) {
                    break;
                }
            } else {
                aPasse.put(joueur, true);
            }

            index = (index + 1) % pli.getJoueurs().size();
        }

        int points = calculerPointsDuPli();
        historique.addAction(new ActionHistorique(pli.getGagnant(), Action.REMPORTEPLI, null));

        pli.getGagnant().ajoutPoints(points);

        cartesDuPli.clear();
        CombinaisonCourante.reset();
        
        return Map.entry(pli.getGagnant(), points);
    }

    private int calculerPointsDuPli() {
        int total = 0;
        for (Carte c : cartesDuPli) {
            switch (c.getValeur()) {
                case CINQ -> total += 5;
                case DIX, ROI -> total += 10;
                default -> {
                }
            }
        }
        return total;
    }

    private boolean tousOntPasseSauf(Joueur actif) {
        return aPasse.entrySet().stream()
                .filter(e -> !e.getKey().equals(actif))
                .filter(e -> !e.getKey().getAbandon())
                .allMatch(Map.Entry::getValue);
    }

    /** Indique qu’un joueur passe. */
    // @Override
    public void marquerPasse(Joueur joueur) {
        aPasse.put(joueur, true);
    }

    /** Le joueur propose une combinaison. Elle sera traitée dans la boucle. */
    // @Override
    public void proposerCombinaison(Joueur joueur, List<Carte> cartes) {
        CombinaisonInterface combinaison = typesCombinaison.combinationFrom(cartes);
        if (combinaison != null) {
            CombinaisonCourante.set(combinaison);
        }
    }

    /** Un joueur passe. */
    public void joueurPasse(Joueur joueur) {
        historique.addAction(new ActionHistorique(joueur, Action.PASSE, null));
        marquerPasse(joueur);
    }

    @Override
    public boolean joueurJoueCombinaison(Joueur joueur, CombinaisonInterface combinaison) {
        if (combinaison == null) {
            marquerPasse(joueur);
            return false;
        }

        CombinaisonInterface combinaisonEnCours = pli.getCombinaisonEnCours();
        Integer rank1 = combinaison.getRank();
        Integer rank2 = (combinaisonEnCours != null) ? combinaisonEnCours.getRank() : null;

        boolean estValide = (combinaisonEnCours == null)
                || (typesCombinaison.estDuMemeType(combinaison, combinaisonEnCours)
                        && rank1 != null && rank2 != null && rank1 > rank2);

        if (estValide) {
            pli.setCombinaisonEnCours(combinaison);
            pli.setGagnant(joueur);
            historique.addAction(new ActionHistorique(joueur, Action.JOUE, combinaison));
            dernierJoueurActif = joueur;
            aPasse.replaceAll((j, b) -> false);
            cartesDuPli.addAll(combinaison.getCartes());
            joueur.retirerCartes(combinaison.getCartes());
            return true;
        } else {
            marquerPasse(joueur);
            return false;
        }
    }

    /** Un joueur abandonne. */
    public void joueurAbandonne(Joueur joueur) {
        joueur.abandon();
        historique.addAction(new ActionHistorique(joueur, Action.ABANDON, null));
    }

    /** Dernière combinaison posée. */
    @Override
    public CombinaisonInterface getCombinaisonEnCours() {
        return pli.getCombinaisonEnCours();
    }

    /** Attache une interface vers typesCombinaisonZhengfen. */
    public void attach(TypeCombinaisonZhengfenInterface typesCombinaisons) {
        this.typesCombinaison = typesCombinaisons;
    }

    private boolean seulJoueurActifAvecCartesRestantes() {
        return pli.getJoueurs().stream()
            .filter(j -> !j.getAbandon() && !j.mainVide() && !aPasse.get(j))
            .count() <= 1;
    }
}