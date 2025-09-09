package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.View.InputManager;

public class OptionsMenu implements Menu {
    private final OptionManager optionManager;
    private final InputManager inputManager;

    private final String GRAS = "\u001B[1m";
    private final String RESET = "\u001B[0m";
    private final String GREY = "\u001B[38;5;244m";

    /**
     * Constructeur de la classe AffichageMenuOption.
     * Initialise l'optionManager.
     */
    public OptionsMenu(OptionManager optionManager) {
        this.optionManager = optionManager;
        this.inputManager = InputManager.getInstance();
    }

    /**
     * Méthode pour afficher les options du joueur.
     */
    @Override
    public MenuType display() {
        System.out.println(String.format("%s=== OPTIONS DU JEU ===%s\n", GRAS, RESET));
        OptionsActives.afficherOptionsActives(optionManager);
        System.out.println(String.format("\n%s=== MENU OPTIONS ===%s", GRAS, RESET));
        System.out.println(String.format("  %s1. Modifier le nombre de joueurs%s", GRAS, RESET));
        System.out.println(String.format("  %s2. Modifier le nombre d'IA%s", GRAS, RESET));
        boolean iaSup0 = optionManager.getNombreIa() > 0;
        System.out.println(String.format("  %s3. Modifier la difficulté des IAs%s", iaSup0 ? GRAS : GREY, RESET));
        System.out.println(String.format("  %s4. Modifier le nombre de points de victoire%s", GRAS, RESET));
        System.out.println(String.format("  %s5. Activer/Désactiver le Joker%s", GRAS, RESET));
        System.out.println(String.format("  %s6. Modifier les combinaisons activées%s", GRAS, RESET));
        System.out.println(String.format("  %s7. Activer/Désactiver le mode débogage%s", GRAS, RESET));
        System.out.println(String.format("  %s8. Retour au menu principal%s", GRAS, RESET));
        System.out.print(String.format("\n%sChoisissez une option :%s ", GRAS, RESET));
        int entree = inputManager.lireEntier();
        while ((entree == 3 && !iaSup0) || entree < 1 || entree > 8) {
            if (entree == 3 && !iaSup0) {
                System.out.print("Choix invalide (aucune IA). Veuillez réessayer : ");
            } else {
                System.out.print("Choix invalide. Veuillez réessayer : ");
            }
            entree = inputManager.lireEntier();
        }
        switch (entree) {
          case 1:
              System.out.print("Entrez le nombre de joueurs (2-4) : ");
              int nombreDeJoueurs = inputManager.lireEntier();
              while (true) {
                  try {
                      optionManager.setNombreDeJoueurs(nombreDeJoueurs);
                      break; // Sortir de la boucle si la valeur est valide
                  } catch (IllegalArgumentException e) {
                      System.out.print(e.getMessage() + " Veuillez réessayer : ");
                      nombreDeJoueurs = inputManager.lireEntier();
                  }
              }
              return MenuType.OPTIONS;
          case 2:
              System.out.print(String.format("Entrez le nombre d'IA (0-%s) : ", optionManager.getNombreDeJoueurs()));
              int nombreIa = inputManager.lireEntier();
              while (true) {
                  try {
                      optionManager.setNombreIa(nombreIa);
                      break; // Sortir de la boucle si la valeur est valide
                  } catch (IllegalArgumentException e) {
                      System.out.print(e.getMessage() + " Veuillez réessayer : ");
                      nombreIa = inputManager.lireEntier();
                  }
              }
              return MenuType.OPTIONS;
          case 3:
              return MenuType.DIFFICULTE;
          case 4:
              System.out.print("Entrez le nombre de points de victoire : ");
              int pointsVictoire = inputManager.lireEntier();
              while (true) {
                  try {
                      optionManager.setNombreDePointsVictoire(pointsVictoire);
                      break; // Sortir de la boucle si la valeur est valide
                  } catch (IllegalArgumentException e) {
                      System.out.print(e.getMessage() + " Veuillez réessayer : ");
                      pointsVictoire = inputManager.lireEntier();
                  }
              }
              return MenuType.OPTIONS;
          case 5:
              optionManager.setJoker(!optionManager.isJoker());
              return MenuType.OPTIONS;
          case 6:
              return MenuType.COMBINAISONS;
          case 7:
              optionManager.setDebogage(!optionManager.isDebogage());
              return MenuType.OPTIONS;
          case 8:
              return MenuType.MAIN;
          default:
              System.out.println("Choix invalide. Veuillez réessayer.");
              return MenuType.OPTIONS; // Afficher à nouveau le menu principal
        }
    }
}
