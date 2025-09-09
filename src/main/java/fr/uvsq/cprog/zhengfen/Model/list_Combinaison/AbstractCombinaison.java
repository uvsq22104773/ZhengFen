package fr.uvsq.cprog.zhengfen.Model.list_Combinaison;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.CombinaisonInterface;
import java.util.List;

/** Generic class representing a card combination. */
public abstract class AbstractCombinaison implements CombinaisonInterface {
    private List<Carte> cartes;
    private boolean alreadyAssigned = false; // Cannot change cards once the combination is created

    /** Return the cards inside the combination. */
    public List<Carte> getCartes() {
        return List.copyOf(cartes);
    }

    public void setCartes(List<Carte> nouvellesCartes) {
        if (alreadyAssigned) {
            return;
        }
        if (cartes != null) {
            if (!cartes.isEmpty()) {
                return;
            }
        } else {
            this.cartes = nouvellesCartes;
            this.alreadyAssigned = true;
        }
    }

    public String toString() {
        return getNom() + ":" + getCartes();
    }
}
