package fr.uvsq.cprog.zhengfen;

import fr.uvsq.cprog.zhengfen.Controleur.PliController;
import fr.uvsq.cprog.zhengfen.Model.Action;
import fr.uvsq.cprog.zhengfen.Model.ActionHistorique;
import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;
import fr.uvsq.cprog.zhengfen.Model.Carte.Couleur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Joueur;
import fr.uvsq.cprog.zhengfen.Model.Joueur.JoueurHumain;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.StrategieHumaine;
import fr.uvsq.cprog.zhengfen.Model.Joueur.Strategie.HintVisitor.HintVisitor;
import fr.uvsq.cprog.zhengfen.View.InputManager;
import fr.uvsq.cprog.zhengfen.View.MenuController;
import fr.uvsq.cprog.zhengfen.View.ModeAffichage;
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageCombinaison;
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageMainVisible;
import fr.uvsq.cprog.zhengfen.View.Affichage.AffichageTour;
import fr.uvsq.cprog.zhengfen.View.Affichage.OptionsMenu;
import fr.uvsq.cprog.zhengfen.View.Affichage.MainMenu;
import fr.uvsq.cprog.zhengfen.View.Affichage.ClearTerminal;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.ArrayList;
import fr.uvsq.cprog.zhengfen.Model.OptionManager;


public class TestAffichageCarte {
    public static void main(String[] args) {
        /*ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.QUATRE));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.QUATRE));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.QUATRE));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.QUATRE));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.SIX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.SIX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.SIX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.SIX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.SEPT));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.SEPT));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.SEPT));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.SEPT));

        Joueur joueur = new JoueurHumain("Joueur 1");
        joueur.ajouterCartes(cartes);

        ClearTerminal.initialiserAffichage();
        Scanner scanner = new Scanner(System.in);

        AffichageMainVisible affichage = new AffichageMainVisible(joueur);
        affichage.afficher();

        while (joueur.getMain().size() > 0) {
            String entree = scanner.nextLine();
            ClearTerminal.initialiserAffichage();
            joueur.retirerCarte(joueur.getMain().get(0));
            affichage.afficher();
        }*/

        /*OptionManager optionManager = OptionManager.getInstance();
        for (Map.Entry<TypeCombinaison, Boolean> entry : optionManager.getCombinaisonsPossibles().entrySet()) {
            TypeCombinaison combinaison = entry.getKey();
            Boolean estActive = entry.getValue();

            String statut = estActive ? "Activé" : "Désactivé";
            System.out.println(String.format("%-25s : %s", combinaison, statut));
        }*/

        new MenuController().run();

        /*
        ClearTerminal.initialiserAffichage();

        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        Joueur joueur1 = new JoueurHumain("Joueur 1", new StrategieHumaine(new HintVisitor()));
        joueur1.ajouterCartes(cartes);

        ArrayList<Carte> cartes2 = new ArrayList<>();
        cartes2.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.QUATRE));
        Joueur joueur2 = new JoueurHumain("J2", new StrategieHumaine(new HintVisitor()));
        joueur2.ajouterCartes(cartes2);

        ArrayDeque<ActionHistorique> historique = new ArrayDeque<>();
        historique.addLast(new ActionHistorique(joueur1, Action.COMMENCEMANCHE, null));
        ArrayList<Carte> pli1 = new ArrayList<>();
        pli1.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        pli1.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        pli1.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        pli1.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        TypeCombinaison combinaison1 = TypeCombinaison.detecter(pli1);
        historique.addLast(new ActionHistorique(joueur1, Action.JOUE, combinaison1.getCombinaison()));
        ArrayList<Carte> pli2 = new ArrayList<>();
        pli2.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        pli2.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        pli2.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        pli2.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        TypeCombinaison combinaison2 = TypeCombinaison.detecter(pli2);
        historique.addLast(new ActionHistorique(joueur2, Action.JOUE, combinaison2.getCombinaison()));
        historique.addLast(new ActionHistorique(joueur1, Action.PASSE, null));
        
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueur2);

        joueur2.abandon();
        joueur1.ajoutPoints(30);

        ArrayList<Carte> pli = new ArrayList<>();
        pli.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        pli.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        pli.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        pli.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));

        TypeCombinaison combinaison = TypeCombinaison.detecter(pli);
        
        AffichageTour affichageTour = new AffichageTour(joueurs);

        affichageTour.afficher(joueur1, null, ModeAffichage.CACHE, historique);

        InputManager scanner = InputManager.getInstance();

        System.out.println("Cliquez sur Entrée pour continuer...");
        scanner.lireLigne();
        ClearTerminal.initialiserAffichage();
        affichageTour.afficher(joueur1, combinaison.getCombinaison(), ModeAffichage.VISIBLE, historique);
        System.out.println("Entrez les cartes que vous voulez jouer : ");
        scanner.lireLigne();*/

        /*
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        Joueur joueur1 = new JoueurHumain("Joueur 1", new StrategieHumaine(null));
        joueur1.ajouterCartes(cartes);

        ArrayList<Carte> cartes2 = new ArrayList<>();
        cartes2.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.QUATRE));
        cartes2.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.QUATRE));
        Joueur joueur2 = new JoueurHumain("J2", new StrategieHumaine(null));
        joueur2.ajouterCartes(cartes2);

        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueur2);

        PliController pliController = new PliController(joueurs);
        pliController.jouer();*/
    }
}