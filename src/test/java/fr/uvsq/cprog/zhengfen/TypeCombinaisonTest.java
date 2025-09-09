package fr.uvsq.cprog.zhengfen;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.TypeCombinaison;
import fr.uvsq.cprog.zhengfen.Model.Carte.Couleur;
import fr.uvsq.cprog.zhengfen.Model.Carte.Valeur;

public class TypeCombinaisonTest {
    
    @Test
    public void testDetecter101() {
        TypeCombinaison tc = TypeCombinaison.SINGLE;
        Carte c1 = new Carte(Couleur.COEUR, Valeur.TROIS);
        assert(tc.detecter(List.of(c1)) == TypeCombinaison.SINGLE);

        Carte c2 = new Carte(Couleur.CARREAU, Valeur.TROIS);
        TypeCombinaison t = TypeCombinaison.SINGLE;
        tc = t.detecter(List.of(c1, c2));
        assert(tc == TypeCombinaison.PAIR);

        Carte c3 = new Carte(Couleur.PIQUE, Valeur.TROIS);
        tc = t.detecter(List.of(c1, c2, c3));
        assert(tc == TypeCombinaison.TRIPLET);

        Carte c4 = new Carte(Couleur.TREFLE, Valeur.TROIS);
        assert(tc.detecter(List.of(c1,c2,c3,c4)) == TypeCombinaison.BOMBE);
    }

}
