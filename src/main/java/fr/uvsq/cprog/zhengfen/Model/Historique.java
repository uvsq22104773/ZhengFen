package fr.uvsq.cprog.zhengfen.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Historique {
    private static Historique instance;
    private ArrayList<ActionHistorique> historique;

    private Historique() {
        historique = new ArrayList<>();
    }

    public static Historique getInstance() {
        if (instance == null) {
            instance = new Historique();
        }
        return instance;
    }

    public List<ActionHistorique> getLastAction(int n) {
        List<ActionHistorique> derniers = historique.stream()
                    .skip(Math.max(0, historique.size() - n))
                    .collect(Collectors.toList());
        return derniers;
    }

    public void addAction(ActionHistorique action) {
        historique.add(action);
    }

    public void clear() {
        historique = new ArrayList<>();
    }

    public int size() {
        return historique.size();
    }

    public List<ActionHistorique> getHistorique() {
        return new ArrayList<>(historique);
    }
}
