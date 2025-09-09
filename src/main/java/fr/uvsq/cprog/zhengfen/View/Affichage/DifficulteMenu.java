package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.View.InputManager;
import java.util.HashMap;

public class DifficulteMenu implements Menu {
    private final OptionManager optionManager;
    private final InputManager inputManager;

    private final String GRAS = "\u001B[1m";
    private final String RESET = "\u001B[0m";

    public DifficulteMenu(OptionManager optionManager) {
        this.inputManager = InputManager.getInstance();
        this.optionManager = optionManager;
    }

    @Override
    public MenuType display() {
        System.out.println(String.format("%s=== NIVEAU DE DIFFICULTÉ DES IAs ===%s\n", GRAS, RESET));
        HashMap<String, Integer> difficulte = optionManager.getDifficulte();
        for (int i = 1; i <= optionManager.getNombreIa(); i++) {
            System.out.println(String.format("  %s%d. %-5s%s : %s", GRAS, i, "IA" + i, RESET,
                    optionManager.getDifficulte(i) == 1 ? " Normale" : " Expert"));
        }
        System.out.println(String.format("  %s%d. Mettre toutes les IAs en normale%s", GRAS, difficulte.size() + 1, RESET));
        System.out.println(String.format("  %s%d. Mettre toutes les IAs en expert%s", GRAS, difficulte.size() + 2, RESET));
        System.out.println(String.format("\n  %s%d. Retour au menu des options%s", GRAS, difficulte.size() + 3, RESET));

        System.out.print(String.format("\n%sChoisissez une difficulté à modifier :%s ", GRAS, RESET));
        int entree = InputManager.getInstance().lireEntier();
        while (entree < 1 || entree > difficulte.size() + 3) {
            System.out.print(String.format("Choix invalide (nombre entre 1 et %s). Veuillez réessayer : ", difficulte.size() + 1));
            entree = inputManager.lireEntier();
        }
        if (entree == difficulte.size() + 1) {
            optionManager.setAllDifficultes(1);
            return MenuType.DIFFICULTE;
        } else if (entree == difficulte.size() + 2) {
            optionManager.setAllDifficultes(2);
            return MenuType.DIFFICULTE;
        } else if (entree == difficulte.size() + 3) {
            return MenuType.OPTIONS;
        } else {
            if (difficulte.get("IA" + entree) == 1) {
                optionManager.setDifficulte(entree, 2);
            } else {
                optionManager.setDifficulte(entree, 1);
            }
            return MenuType.DIFFICULTE;
        }
    }
    
}
