package fr.uvsq.cprog.zhengfen.View;

import fr.uvsq.cprog.zhengfen.Controleur.PartieController;
import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.Model.Partie;
import fr.uvsq.cprog.zhengfen.View.Affichage.ClearTerminal;
import fr.uvsq.cprog.zhengfen.View.Affichage.CombinaisonsMenu;
import fr.uvsq.cprog.zhengfen.View.Affichage.DifficulteMenu;
import fr.uvsq.cprog.zhengfen.View.Affichage.MainMenu;
import fr.uvsq.cprog.zhengfen.View.Affichage.Menu;
import fr.uvsq.cprog.zhengfen.View.Affichage.MenuType;
import fr.uvsq.cprog.zhengfen.View.Affichage.OptionsMenu;

public class MenuController {
    private final OptionManager optionManager = OptionManager.getInstance();
    private final InputManager inputManager = InputManager.getInstance();

    public void run() {
        MenuType current = MenuType.MAIN;
        while (current != MenuType.EXIT) {
            Menu menu;
            switch (current) {
              case MAIN:
                  ClearTerminal.initialiserAffichage();
                  menu = new MainMenu(optionManager);
                  break;
                case JOUE:
                  ClearTerminal.initialiserAffichage();
                  Partie partie = Partie.creer();
                  PartieController partieController = new PartieController(partie);
                  partieController.lancer();
                  menu = new MainMenu(optionManager);
                  break;
              case OPTIONS:
                  ClearTerminal.initialiserAffichage();
                  menu = new OptionsMenu(optionManager);
                  break;
              case COMBINAISONS:
                  ClearTerminal.initialiserAffichage();
                  menu = new CombinaisonsMenu(optionManager);
                  break;
              case DIFFICULTE:
                  ClearTerminal.initialiserAffichage();
                  menu = new DifficulteMenu(optionManager);
                  break;
              default:
                  return;
            }
            current = menu.display();
        }

        ClearTerminal.clear();
        System.out.println("Merci d'avoir joué !");
        inputManager.close();
    }
}