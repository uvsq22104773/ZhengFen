package fr.uvsq.cprog.zhengfen.Model;

import java.util.ArrayList;

public class ForceCombinaison {

    private final TypeCombinaison type;
    private final int force;

    public ForceCombinaison(ArrayList<Carte> cartes) {
        TypeCombinaison tc = TypeCombinaison.SINGLE;
        TypeCombinaison detected = tc.detecter(cartes);
        if (detected == null) {
            throw new IllegalArgumentException("Combinaison invalide");
        }
        this.type = detected;
        this.force = detected.getForce();
    }

    public int getForce() {
        return force;
    }

    public TypeCombinaison getType() {
        return type;
    }

    public boolean estPlusForteQue(ForceCombinaison autre) {
        return this.force > autre.force;
    }

    @Override
    public String toString() {
        return "ForceCombinaison{"
                + "type=" + type
                + ", force=" + force
                + '}';
    }
}
