package fr.uvsq.cprog.zhengfen;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import fr.uvsq.cprog.zhengfen.Model.Carte;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.BombeCombinaison;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.FullHouseCombination;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.PaireCombinaison;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.SequencePairsCombination;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.SequenceQuartetsCombination;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.SequenceTriplesCombination;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.SingleCombination;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.Special_1;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.Special_2;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.Special_3;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.SuitSequenceCombination;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.TripletCombinaison;

public class CombinaisonTest {

    @Test
    void testSingleValid() {
        Carte c1 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX);
        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c1);
        SingleCombination single = new SingleCombination();
        assertTrue(single.estValide(hand));
    }

    @Test
    void testSingleInvalid() {
        Carte c1 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX);
        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        SingleCombination single = new SingleCombination();
        assertFalse(single.estValide(hand));
    }

    @Test
    void testSingleRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        SingleCombination single = new SingleCombination();
        single.estValide(cartes);
        assertEquals(13, single.getRank());
        assertEquals("Single", single.getNom());
    }

    @Test
    void testPaireValide() {
        ArrayList<Carte> cartes = new ArrayList<>();
        Carte c1 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.SIX);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.SIX);
        cartes.add(c1);
        cartes.add(c2);
        assertTrue(new PaireCombinaison().estValide(cartes));
        cartes.remove(c2);
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        assertTrue(new PaireCombinaison().estValide(cartes));
        cartes.remove(c1);
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        assertTrue(new PaireCombinaison().estValide(cartes));
    }

    @Test
    void testPaireInvalide() {
        Carte c1 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.NEUF);
        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        assertFalse(new PaireCombinaison().estValide(hand));
        hand.add(c2);
        assertFalse(new PaireCombinaison().estValide(hand));
    }

    @Test
    void testPaireRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DEUX));
        PaireCombinaison paire = new PaireCombinaison();
        paire.estValide(cartes);
        assertEquals(13, paire.getRank());
        assertEquals("Pair", paire.getNom());
    }

    @Test
    void testTripletValide() {
        ArrayList<Carte> cartes = new ArrayList<>();
        Carte c1 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.SIX);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.SIX);
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.SIX));
        cartes.add(c1);
        cartes.add(c2);
        assertTrue(new TripletCombinaison().estValide(cartes));
        cartes.remove(c2);
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        assertTrue(new TripletCombinaison().estValide(cartes));
        cartes.remove(c1);
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        assertTrue(new TripletCombinaison().estValide(cartes));
    }

    @Test
    void testTripletInvalide() {
        Carte c1 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI);
        Carte c3 = new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME);
        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        assertFalse(new TripletCombinaison().estValide(hand));
        hand.add(c3);
        assertFalse(new TripletCombinaison().estValide(hand));
    }

    @Test
    void testTripletRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        TripletCombinaison triplet = new TripletCombinaison();
        triplet.estValide(cartes);
        assertEquals(13, triplet.getRank());
        assertEquals("Triplet", triplet.getNom());
    }

    @Test
    void testBombeValide() {
        Carte c1 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS);
        Carte c3 = new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS);
        Carte c4 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.TROIS);
        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        hand.add(c4);
        assertTrue(new BombeCombinaison().estValide(hand));
        hand.remove(c1);
        hand.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        assertTrue(new BombeCombinaison().estValide(hand));
        hand.remove(c2);
        hand.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        assertTrue(new BombeCombinaison().estValide(hand));
    }

    @Test
    void testBombeInvalide() {
        Carte c1 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.TROIS);
        Carte c3 = new Carte(Carte.Couleur.COEUR, Carte.Valeur.TROIS);
        Carte c4 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.QUATRE);
        ArrayList<Carte> hand = new ArrayList<>();
        hand.add(c1);
        hand.add(c2);
        hand.add(c3);
        hand.add(c4);
        assertFalse(new BombeCombinaison().estValide(hand));
        hand.remove(c1);
        assertFalse(new BombeCombinaison().estValide(hand));
    }

    @Test
    void testBombeRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        BombeCombinaison bombe = new BombeCombinaison();
        bombe.estValide(cartes);
        assertEquals(11, bombe.getRank());
        assertEquals("Bombe", bombe.getNom());
    }

    // ========== SPECIAL 1 ==========
    @Test
    void testSpecial3_valideSansJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DEUX));
        assertTrue(new Special_3().estValide(cartes));
    }

    @Test
    void testSpecial3_valideAvecJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(null, Carte.Valeur.JOKER));
        assertTrue(new Special_3().estValide(cartes));
    }

    @Test
    void testSpecial3_invalideValeur() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.TROIS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(null, Carte.Valeur.JOKER));
        assertFalse(new Special_3().estValide(cartes));
    }

    @Test
    void testSpecial3Rank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DEUX));;
        Special_3 special = new Special_3();
        special.estValide(cartes);
        assertEquals(0, special.getRank());
        assertEquals("Special_3 (4x Deux)", special.getNom());
    }

    // ========== SPECIAL 2 ==========
    @Test
    void testSpecial2_valideSansJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        assertTrue(new Special_2().estValide(cartes));
    }

    @Test
    void testSpecial2_valideAvecJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX));
        cartes.add(new Carte(null, Carte.Valeur.JOKER));
        assertTrue(new Special_2().estValide(cartes));
    }

    @Test
    void testSpecial2_invalideCouleurDifferente() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        assertFalse(new Special_2().estValide(cartes));
    }

    @Test
    void testSpecial2_invalideValeur() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.SIX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        assertFalse(new Special_2().estValide(cartes));
    }

    @Test
    void testSpecial2Rank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        Special_2 special = new Special_2();
        special.estValide(cartes);
        assertEquals(0, special.getRank());
        assertEquals("Special_2 (5-10-K même couleur)", special.getNom());
    }

    // ========== SPECIAL 3 ==========
    @Test
    void testSpecial1_valideSansJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        assertTrue(new Special_1().estValide(cartes));
    }

    @Test
    void testSpecial1_valideAvecJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.CINQ));
        cartes.add(new Carte(null, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        assertTrue(new Special_1().estValide(cartes));
    }

    @Test
    void testSpecial1_invalideValeur() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.SIX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        assertFalse(new Special_1().estValide(cartes));
    }

    @Test
    void testSpecial1Rank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.CINQ));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        Special_1 special = new Special_1();
        special.estValide(cartes);
        assertEquals(0, special.getRank());
        assertEquals("Special_1 (5-10-K mixtes)", special.getNom());
    }

    // ========== SequencePairs ==========
    @Test
    void testSequencePairsValid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        assertTrue(new SequencePairsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        assertTrue(new SequencePairsCombination().estValide(cartes));
    }

    @Test
    void testSequencePairsValidUnJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        assertTrue(new SequencePairsCombination().estValide(cartes));
    }

    @Test
    void testSequencePairsValidDeuxJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        assertTrue(new SequencePairsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX));
        assertTrue(new SequencePairsCombination().estValide(cartes));
    }

    @Test
    void testSequencePairsInvalid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        assertFalse(new SequencePairsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        assertFalse(new SequencePairsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.NEUF));
        assertFalse(new SequencePairsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX));
        assertFalse(new SequencePairsCombination().estValide(cartes));
    }

    @Test
    void testSequencePairsRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        SequencePairsCombination seq1 = new SequencePairsCombination();
        seq1.estValide(cartes);
        assertEquals(312, seq1.getRank());
        assertEquals("SequenceOfPairs", seq1.getNom());
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        SequencePairsCombination seq2 = new SequencePairsCombination();
        seq2.estValide(cartes);
        assertEquals(412, seq2.getRank());        
    }
    
    // ========== SequenceTriples ==========
    @Test
    void testSequenceTriplesValid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        assertTrue(new SequenceTriplesCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        assertTrue(new SequenceTriplesCombination().estValide(cartes));
    }

    @Test
    void testSequenceTriplesValidUnJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        assertTrue(new SequenceTriplesCombination().estValide(cartes));
    }

    @Test
    void testSequenceTriplesValidDeuxJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        assertTrue(new SequenceTriplesCombination().estValide(cartes));
    }

    @Test
    void testSequenceTriplesInvalid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        assertFalse(new SequenceTriplesCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        assertFalse(new SequenceTriplesCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX));
        assertFalse(new SequenceTriplesCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        assertFalse(new SequenceTriplesCombination().estValide(cartes));
    }

    @Test
    void testSequenceTriplesRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        SequenceTriplesCombination seq1 = new SequenceTriplesCombination();
        seq1.estValide(cartes);
        assertEquals(312, seq1.getRank());
        assertEquals("SequenceOfTriples", seq1.getNom());
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        SequenceTriplesCombination seq2 = new SequenceTriplesCombination();
        seq2.estValide(cartes);
        assertEquals(412, seq2.getRank());  
    }

    // ========== SequenceQuartets ==========
    @Test
    void testSequenceQuartetsValid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        assertTrue(new SequenceQuartetsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.VALET));
        assertTrue(new SequenceQuartetsCombination().estValide(cartes));
    }

    @Test
    void testSequenceQuartetsValidUnJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        assertTrue(new SequenceQuartetsCombination().estValide(cartes));
    }

    @Test
    void testSequenceQuartetsValidDeuxJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        assertTrue(new SequenceQuartetsCombination().estValide(cartes));
    }

    @Test
    void testSequenceQuartetsInvalid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        assertFalse(new SequenceQuartetsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        assertFalse(new SequenceQuartetsCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DIX));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        assertFalse(new SequenceQuartetsCombination().estValide(cartes));
    }

    @Test
    void testSequenceQuartetsRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME));
        SequenceQuartetsCombination seq1 = new SequenceQuartetsCombination();
        seq1.estValide(cartes);
        assertEquals(312, seq1.getRank());
        assertEquals("SequenceOfQuartets", seq1.getNom());
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.VALET));
        SequenceQuartetsCombination seq2 = new SequenceQuartetsCombination();
        seq2.estValide(cartes);
        assertEquals(412, seq2.getRank());  
    }

    // ========== FullHouse ==========
    @Test
    void testFullHouseValid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        Carte c = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME);
        cartes.add(c);
        assertTrue(new FullHouseCombination().estValide(cartes));
        cartes.remove(c);
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.ROI));
        assertTrue(new FullHouseCombination().estValide(cartes));
    }

    @Test
    void testFullHouseValidUnJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI));
        Carte c = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME);
        cartes.add(c);
        assertTrue(new FullHouseCombination().estValide(cartes));
        cartes.remove(c);
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.ROI));
        assertTrue(new FullHouseCombination().estValide(cartes));
    }

    @Test
    void testFullHouseValidDeuxJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        assertTrue(new FullHouseCombination().estValide(cartes));
    }

    @Test
    void testFullHouseInvalid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        Carte c = new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER);
        Carte c1 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME);
        cartes.add(c1);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI);
        cartes.add(c2);
        Carte c3 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX);
        cartes.add(c3);
        Carte c4 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.VALET);
        cartes.add(c4);
        Carte c5 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.NEUF);
        cartes.add(c5);
        assertFalse(new FullHouseCombination().estValide(cartes));
        cartes.remove(c5);
        cartes.add(c);
        assertFalse(new FullHouseCombination().estValide(cartes));
        cartes.remove(c);
        Carte c6 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.VALET);
        cartes.add(c6);
        assertFalse(new FullHouseCombination().estValide(cartes));
        cartes.remove(c3);
        Carte c7 = new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DAME);
        cartes.add(c7);
        cartes.remove(c6);
        cartes.remove(c2);
        cartes.remove(c4);
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.COEUR, Carte.Valeur.DAME));
        assertFalse(new FullHouseCombination().estValide(cartes));
    }

    @Test
    void testFullHouseRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.PIQUE, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.TREFLE, Carte.Valeur.DEUX));
        FullHouseCombination full = new FullHouseCombination();
        full.estValide(cartes);
        assertEquals(12, full.getRank());
        assertEquals("FullHouse", full.getNom());
    }

    // ========== SuitSequence ==========
    @Test
    void testSuitValid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        assertTrue(new SuitSequenceCombination().estValide(cartes));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        assertTrue(new SuitSequenceCombination().estValide(cartes));
    }

    @Test
    void testSuitValidUnJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        Carte c = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS);
        cartes.add(c);
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        assertTrue(new SuitSequenceCombination().estValide(cartes));
        cartes.remove(c);
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        assertTrue(new SuitSequenceCombination().estValide(cartes));
    }

    @Test
    void testSuitValidDeuxJoker() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.SMALL, Carte.Valeur.JOKER));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        Carte c1 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI);
        cartes.add(c1);
        assertTrue(new SuitSequenceCombination().estValide(cartes));
        cartes.remove(c1);
        Carte c2 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME);
        cartes.add(c2);
        assertTrue(new SuitSequenceCombination().estValide(cartes));
        cartes.remove(c2);
        Carte c3 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET);
        cartes.add(c3);
        assertTrue(new SuitSequenceCombination().estValide(cartes));
        cartes.add(c1);
        cartes.add(c2);
        assertTrue(new SuitSequenceCombination().estValide(cartes));
    }

    @Test
    void testSuitInvalid() {
        ArrayList<Carte> cartes = new ArrayList<>();
        assertFalse(new SuitSequenceCombination().estValide(cartes));
        Carte c = new Carte(Carte.Couleur.BIG, Carte.Valeur.JOKER);
        cartes.add(c);
        Carte c1 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DAME);
        cartes.add(c1);
        Carte c2 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.ROI);
        cartes.add(c2);
        Carte c3 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.DIX);
        cartes.add(c3);
        Carte c4 = new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET);
        cartes.add(c4);
        assertFalse(new SuitSequenceCombination().estValide(cartes));
        cartes.remove(c4);
        Carte c5 = new Carte(Carte.Couleur.PIQUE, Carte.Valeur.SEPT);
        cartes.add(c5);
        assertFalse(new SuitSequenceCombination().estValide(cartes));
        cartes.add(c);
        assertFalse(new SuitSequenceCombination().estValide(cartes));
    }
    
    @Test
    void testSuitRank() {
        ArrayList<Carte> cartes = new ArrayList<>();
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DEUX));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.AS));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.ROI));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DAME));
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.VALET));
        SuitSequenceCombination suit1 = new SuitSequenceCombination();
        suit1.estValide(cartes);
        assertEquals(513, suit1.getRank());
        assertEquals("SuitSequence", suit1.getNom());
        cartes.add(new Carte(Carte.Couleur.CARREAU, Carte.Valeur.DIX));
        SuitSequenceCombination suit2 = new SuitSequenceCombination();
        suit2.estValide(cartes);
        assertEquals(613, suit2.getRank());
    }
}
