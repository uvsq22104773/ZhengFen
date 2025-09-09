package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.OptionManager;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;
import fr.uvsq.cprog.zhengfen.View.InputManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CombinaisonsMenu implements Menu {
    private final OptionManager optionManager;
    private final InputManager inputManager;
    private List<TypeCombinaison> combinaisons = new ArrayList<>();

    private final String GRAS = "\u001B[1m";
    private final String RESET = "\u001B[0m";

    /**
     * Constructeur de la classe CombinaisonsOptionMenu.
     * Initialise l'optionManager.
     */
    public CombinaisonsMenu(OptionManager optionManager) {
        this.inputManager = InputManager.getInstance();
        this.optionManager = optionManager;
        this.combinaisons = optionManager.getCombinaisonsPossibles().keySet().stream()
            .sorted(Comparator.comparingInt(TypeCombinaison::getForce))
            .collect(Collectors.toList());
    }

    /**
     * Méthode pour afficher les combinaisons activées.
     */
    @Override
    public MenuType display() {
        System.out.println(String.format("%s=== COMBINAISONS ===%s\n", GRAS, RESET));
        for (int i = 0; i < combinaisons.size(); i++) {
            TypeCombinaison combinaison = combinaisons.get(i);
            System.out.println(String.format("  %s%2d. %-25s%s : %s", GRAS, i + 1, combinaison, RESET,
                    optionManager.getCombinaisonsPossibles().get(combinaison) ? " Activée" : " Désactivée"));
        }
        System.out.println(String.format("\n  %s%d. Retour au menu des options%s", GRAS, combinaisons.size() + 1, RESET));
        System.out.print(String.format("\n%sChoisissez une combinaison à activer/désactiver :%s ", GRAS, RESET));
        int entree = inputManager.lireEntier();
        while (entree < 1 || entree > combinaisons.size() + 1) {
            System.out.print(String.format("Choix invalide (nombre entre 1 et %s). Veuillez réessayer : ", combinaisons.size() + 1));
            entree = inputManager.lireEntier();
        }
        if (entree == combinaisons.size() + 1) {
            return MenuType.OPTIONS;
        }
        TypeCombinaison combinaison = combinaisons.get(entree - 1);
        boolean etatActuel = optionManager.getCombinaisonsPossibles().get(combinaison);
        optionManager.getCombinaisonsPossibles().put(combinaison, !etatActuel);

        return MenuType.COMBINAISONS;
    }
}
