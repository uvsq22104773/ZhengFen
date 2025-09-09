package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.View.InputManager;

public class MainMenu implements Menu {
    private final OptionManager optionManager;
    private final InputManager inputManager;

    private final String GRAS = "\u001B[1m";
    private final String RESET = "\u001B[0m";

    /**
     * Constructeur de la classe AffichageMenuPrincipal.
     * Initialise l'optionManager.
     */
    public MainMenu(OptionManager optionManager) {
        this.inputManager = InputManager.getInstance();
        this.optionManager = optionManager;     
    }

    /**
     * Méthode pour afficher le menu principal du jeu.
     */
    public MenuType display() {
        System.out.println(String.format("%s=== BIENVENUE DANS LE JEU DE CARTES ZHENGFEN ===%s\n", GRAS, RESET));
        OptionsActives.afficherOptionsActives(optionManager);
        System.out.println(String.format("\n%s=== MENU PRINCIPAL ===%s", GRAS, RESET));
        System.out.println(String.format("  %s1. Jouer%s", GRAS, RESET));
        System.out.println(String.format("  %s2. Options%s", GRAS, RESET));
        System.out.println(String.format("  %s3. Quitter%s", GRAS, RESET));
        System.out.print(String.format("\n%sChoisissez une option :%s ", GRAS, RESET));
        int entree = inputManager.lireEntier();
        while (entree < 1 || entree > 3) {
            System.out.print("Choix invalide. Veuillez réessayer : ");
            entree = inputManager.lireEntier();
        }
        switch (entree) {
          case 1:
              return MenuType.JOUE;
          case 2:
              return MenuType.OPTIONS;
          case 3:
              return MenuType.EXIT;
          default:
              System.out.println("Choix invalide. Veuillez réessayer.");
              return MenuType.MAIN;
        }
    }
}
