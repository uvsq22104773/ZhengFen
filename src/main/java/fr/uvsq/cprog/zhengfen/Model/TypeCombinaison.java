package fr.uvsq.cprog.zhengfen.Model;

import fr.uvsq.cprog.zhengfen.Model.Carte.Valeur;
import fr.uvsq.cprog.zhengfen.Model.list_Combinaison.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/** Représente les combinaisons utilisées dans Zhengfen. */
public enum TypeCombinaison implements TypeCombinaisonZhengfenInterface {

    SPECIAL_3(Special_3::new, 12),
    SPECIAL_2(Special_2::new, 11),
    SPECIAL_1(Special_1::new, 10),
    SUIT_SEQUENCE(SuitSequenceCombination::new, 9),
    SEQUENCE_OF_QUARTETS(SequenceQuartetsCombination::new, 8),
    SEQUENCE_OF_TRIPLES(SequenceTriplesCombination::new, 7),
    SEQUENCE_OF_PAIRS(SequencePairsCombination::new, 6),
    FULL_HOUSE(FullHouseCombination::new, 5),
    BOMBE(BombeCombinaison::new, 4),
    TRIPLET(TripletCombinaison::new, 3),
    PAIR(PaireCombinaison::new, 2),
    SINGLE(SingleCombination::new, 1);

    private final Supplier<CombinaisonInterface> fournisseur;
    private final int force;

    TypeCombinaison(Supplier<CombinaisonInterface> fournisseur, int force) {
        this.fournisseur = fournisseur;
        this.force = force;
    }

    public CombinaisonInterface nouvelleCombinaison() {
        return fournisseur.get();
    }

    public int getForce() {
        return force;
    }

    @Override
    public TypeCombinaison detecter(List<Carte> cartes) {
        for (TypeCombinaison type : values()) {
            CombinaisonInterface c = type.nouvelleCombinaison();
            if (c.estValide(cartes)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public CombinaisonInterface combinationFrom(List<Carte> cartes) {
        TypeCombinaison tc = detecter(cartes);
        if (tc == null) {
            return null;
        }

        CombinaisonInterface combinaison = tc.nouvelleCombinaison();
        if (combinaison.estValide(cartes)) {
            combinaison.setCartes(cartes);
            return combinaison;
        }
        return null;
    }

    @Override
    public boolean isValid(CombinaisonInterface combination) {
        return detecter(combination.getCartes()) != null;
    }

    @Override
    public boolean estDuMemeType(CombinaisonInterface comb1, CombinaisonInterface comb2) {
        return detecter(comb1.getCartes()) == detecter(comb2.getCartes());
    }

    /**
     * Générateurs de combinaisons pour IA (facultatif, à améliorer selon la
     * stratégie)
     */
    public CombinaisonInterface combinationsOfType(CombinaisonInterface comb, List<Carte> main) {
        if (comb == null) {
            return generateSingle(main);
        }
        TypeCombinaison tc = detecter(comb.getCartes());
        switch (tc) {
            case SINGLE:
                return generateSingle(main);
            case PAIR:
                return generatePair(main);
            case TRIPLET:
                return generateTriplet(main);
            case BOMBE:
                return generateQuartet(main);
            case SEQUENCE_OF_PAIRS:
                return generatePairSequence(main);
            case FULL_HOUSE:
                return generateFullHouse(main);
            case SEQUENCE_OF_TRIPLES:
                return generateTripletSequence(main);
            default:
                return null;
        }
    }

    public CombinaisonInterface generateRandomCombination(List<Carte> main) {
        Random rng = new Random();
        int combi = rng.nextInt(7);
        switch (combi) {
            case 0:
                return generateSingle(main);
            case 1:
                return generatePair(main);
            case 2:
                return generateTriplet(main);
            case 3:
                return generateQuartet(main);
            case 4:
                return generatePairSequence(main);
            case 5:
                return generateFullHouse(main);
            case 6:
                return generateTripletSequence(main);
            default:
                return generateSingle(main);
        }
    }

    private CombinaisonInterface generateSingle(List<Carte> main) {
        return combinationFrom(List.of(randomFrom(main)));
    }

    private CombinaisonInterface generatePair(List<Carte> main) {
        Carte pair1 = randomFrom(main.stream()
                .filter(c -> main.stream().filter(otherc -> otherc.getValeur() == c.getValeur())
                        .count() > 1)
                .toList());
        if (pair1 != null) {
            Carte pair2 = randomFrom(main.stream()
                    .filter(card -> ((card.getValeur() == pair1.getValeur() && card != pair1)
                            || card.getValeur() == Valeur.JOKER))
                    .toList());
            if (pair2 != null) {
                return combinationFrom(List.of(pair1, pair2));
            }
        }
        return null;
    }

    private CombinaisonInterface generateTriplet(List<Carte> main) {
        Carte triplet1 = randomFrom(main.stream()
                .filter(c -> main.stream().filter(otherc -> otherc.getValeur() == c.getValeur())
                        .count() > 2)
                .toList());
        if (triplet1 != null) {
            List<Carte> candidates = main.stream()
                    .filter(card -> card.getValeur() == triplet1.getValeur())
                    .toList();
            if (candidates.size() >= 3) {
                return combinationFrom(candidates.subList(0, 3));
            }
        }
        return null;
    }

    private CombinaisonInterface generateQuartet(List<Carte> main) {
        Carte quartet1 = randomFrom(main.stream()
                .filter(c -> main.stream().filter(otherc -> otherc.getValeur() == c.getValeur())
                        .count() > 3)
                .toList());
        if (quartet1 != null) {
            Carte q = quartet1;
            List<Carte> candidates = main.stream()
                    .filter(card -> card.getValeur() == q.getValeur())
                    .toList();
            if (candidates.size() >= 4) {
                return combinationFrom(candidates);
            }
        }
        return null;
    }

    private CombinaisonInterface generatePairSequence(List<Carte> main) {
        List<Carte.Valeur> candidates = main.stream()
                .map(c -> c.getValeur()).collect(Collectors.toList()).stream().distinct().toList();
        for (int i = 0; i < pow(candidates.size(), 3); i++) {
            CombinaisonInterface pair1 = generatePair(main);
            CombinaisonInterface pair2 = generatePair(main);
            CombinaisonInterface pair3 = generatePair(main);
            ArrayList<Carte> cards = new ArrayList<>();
            if (pair1 == null || pair2 == null || pair3 == null) {
                continue;
            }
            cards.addAll(pair1.getCartes());
            cards.addAll(pair2.getCartes());
            cards.addAll(pair3.getCartes());
            List<Carte> finalcards = cards.stream().distinct().toList();
            if (detecter(finalcards) != null) {
                return combinationFrom(finalcards);
            }
        }
        return null;
    }

    private CombinaisonInterface generateFullHouse(List<Carte> main) {
        Map<Carte.Valeur, List<Carte>> grouped = main.stream()
                .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                .collect(Collectors.groupingBy(Carte::getValeur));

        List<Carte> jokers = main.stream()
                .filter(c -> c.getValeur() == Carte.Valeur.JOKER)
                .toList();

        for (Map.Entry<Carte.Valeur, List<Carte>> tripleCandidate : grouped.entrySet()) {
            List<Carte> tripleList = tripleCandidate.getValue();
            if (tripleList.size() + jokers.size() >= 3) {
                for (Map.Entry<Carte.Valeur, List<Carte>> pairCandidate : grouped.entrySet()) {
                    if (!pairCandidate.getKey().equals(tripleCandidate.getKey())) {
                        List<Carte> pairList = pairCandidate.getValue();
                        if (pairList.size() + jokers.size() - Math.max(0, 3 - tripleList.size()) >= 2) {
                            List<Carte> fullHouse = new ArrayList<>();
                            int jokersLeft = jokers.size();

                            // Ajouter les cartes du triplet
                            fullHouse.addAll(tripleList.subList(0, Math.min(3, tripleList.size())));
                            for (int i = tripleList.size(); i < 3 && jokersLeft > 0; i++, jokersLeft--) {
                                fullHouse.add(jokers.get(jokers.size() - jokersLeft));
                            }

                            // Ajouter les cartes de la paire
                            fullHouse.addAll(pairList.subList(0, Math.min(2, pairList.size())));
                            for (int i = pairList.size(); i < 2 && jokersLeft > 0; i++, jokersLeft--) {
                                fullHouse.add(jokers.get(jokers.size() - jokersLeft));
                            }

                            if (fullHouse.size() == 5) {
                                return combinationFrom(fullHouse);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private CombinaisonInterface generateTripletSequence(List<Carte> main) {
        // Grouper les cartes par valeur
        Map<Carte.Valeur, List<Carte>> grouped = main.stream()
                .filter(c -> c.getValeur() != Carte.Valeur.JOKER)
                .collect(Collectors.groupingBy(Carte::getValeur));

        // Obtenir les valeurs ayant au moins 3 cartes (triplet)
        List<Carte.Valeur> tripletValeurs = grouped.entrySet().stream()
                .filter(e -> e.getValue().size() >= 3)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Carte.cardOrder::get))
                .toList();

        // Essayer de trouver des suites consécutives de triplets (au moins deux
        // triplets consécutifs)
        for (int i = 0; i <= tripletValeurs.size() - 2; i++) {
            List<Carte> combinaison = new ArrayList<>();
            int streak = 1;
            Carte.Valeur current = tripletValeurs.get(i);

            for (int j = i + 1; j < tripletValeurs.size(); j++) {
                Carte.Valeur next = tripletValeurs.get(j);
                if (Carte.cardOrder.get(next) == Carte.cardOrder.get(current) + 1) {
                    streak++;
                    current = next;
                    if (streak >= 2) {
                        // Ajouter les triplets à la combinaison
                        for (int k = i; k <= j; k++) {
                            combinaison.addAll(grouped.get(tripletValeurs.get(k)).subList(0, 3));
                        }
                        // Créer la combinaison si possible
                        CombinaisonInterface resultat = combinationFrom(combinaison);
                        if (resultat != null) {
                            return resultat;
                        }
                        combinaison.clear();
                    }
                } else {
                    break;
                }
            }
        }

        return null;
    }

    private int pow(int a, int b) {
        int acopy = a;
        for (int i = 0; i < b; i++) {
            a *= acopy;
        }
        return acopy;
    }

    private Carte randomFrom(List<Carte> cartes) {
        if (cartes.size() == 0) {
            return null;
        }
        Random rng = new Random();
        return cartes.get(rng.nextInt(cartes.size()));
    }
}
