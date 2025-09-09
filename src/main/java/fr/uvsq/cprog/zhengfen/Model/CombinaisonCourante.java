package fr.uvsq.cprog.zhengfen.Model;

public class CombinaisonCourante {
    private static CombinaisonInterface derniere;

    public static void set(CombinaisonInterface combinaison) {
        derniere = combinaison;
    }

    public static CombinaisonInterface get() {
        return derniere;
    }

    public static void reset() {
        derniere = null;
    }
}
