package fr.uvsq.cprog.zhengfen.Model;

import java.util.List;

/** Interface pour une combinaison de cartes. */
public interface CombinaisonInterface {
    boolean estValide(List<Carte> cartes);
    
    List<Carte> getCartes();

    String getNom();

    void setCartes(List<Carte> cartes);

    Integer getRank();

    String toString();
}

