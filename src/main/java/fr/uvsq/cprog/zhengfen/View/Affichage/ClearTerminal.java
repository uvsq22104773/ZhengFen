package fr.uvsq.cprog.zhengfen.View.Affichage;

public class ClearTerminal {
    private static final String GRIS_DISCRET = "\u001B[38;5;244m";
    private static final String RESET = "\u001B[0m";
    private static final String VERSION = "v1.0.0";

    public static void clear() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // Pour Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Pour Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initialiserAffichage() {
        clear();
        System.out.println(String.format("%sZhengfen %s\nTapez 'exit' à tout moment pour quitter le programme.%s\n", GRIS_DISCRET, VERSION, RESET));
    }
}
