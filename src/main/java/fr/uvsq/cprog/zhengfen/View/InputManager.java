package fr.uvsq.cprog.zhengfen.View;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.View.Affichage.ClearTerminal;
import java.util.List;
import java.util.Scanner;

public class InputManager {
    private static InputManager instance;
    private final Scanner scanner;

    private InputManager() {
        scanner = new Scanner(System.in);
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public String lireLigne() {
        String input = scanner.nextLine();
        if (input.equals("exit")) {
            ClearTerminal.clear();
            System.out.println("Merci d'avoir joué !");
            System.exit(0);
        }
        return input;
    }

    public List<Carte> lireCartes() {
        while (true) {
            System.out.print("Entrez la combinaison : ");
            String input = lireLigne();
            List<Carte> cartes = InputToCartes.inputToCartes(input);
            if (cartes != null) {
                return cartes;
            } else {
                System.out.print("Combinaison invalide, réessayer : ");
            }
        }
    }

    public int lireEntier() {
        while (true) {
            try {
                return Integer.parseInt(lireLigne());
            } catch (NumberFormatException e) {
                System.out.print("Nombre invalide, réessayer : ");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
