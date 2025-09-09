package fr.uvsq.cprog.zhengfen.View.Affichage;

import fr.uvsq.cprog.zhengfen.Model.OptionManager;

public class OptionsActives {
    public static void afficherOptionsActives(OptionManager optionManager) {
        System.out.println("--- OPTIONS ACTUELLES ---");
        System.out.println(String.format("  %-28s : %s", "Nombre de joueurs (avec IA)", optionManager.getNombreDeJoueurs()));
        long nombreIaFacile = optionManager.getDifficulte().values().stream()
            .filter(v -> v == 1)
            .count();
        long nombreIaNormale = optionManager.getDifficulte().values().stream()
            .filter(v -> v == 2)
            .count();
        System.out.println(String.format("  %-28s : %s (%d normale%s, %d expert%s)", "Nombre d'IA", optionManager.getNombreIa(), 
            nombreIaFacile, nombreIaFacile > 1 ? "s" : "", nombreIaNormale, nombreIaNormale > 1 ? "s" : ""));
        System.out.println(String.format("  %-28s : %s", "Nombre de points pour gagner", optionManager.getNombreDePointsVictoire()));
        System.out.println(String.format("  %-28s : %s", "Joker", (optionManager.isJoker() ? "Activé" : "Désactivé")));
        int total = optionManager.getCombinaisonsPossibles().size();
        int actives = (int) optionManager.getCombinaisonsPossibles()
            .values()
            .stream()
            .filter(Boolean::booleanValue)
            .count();
        System.out.println(String.format("  %-28s : %s/%s", "Combinaisons activées", actives, total));
        System.out.println(String.format("  %-28s : %s", "Mode débogage", (optionManager.isDebogage() ? "Activé" : "Désactivé")));
    }
}
