package fr.uvsq.cprog.zhengfen.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * OptionManager est une classe singleton qui gère les options du jeu.
 * Elle permet de définir le nombre de joueurs, le nombre d'IA, l'activation des jokers,
 * et les combinaisons possibles dans le jeu.
 */
public class OptionManager {
    private static OptionManager instance;
    private int nombreDeJoueurs;
    private int nombreIa;
    private boolean joker;
    private Map<TypeCombinaison, Boolean> combinaisonsPossibles;
    private HashMap<String, Integer> difficulte;
    private int nombreDePointsVictoire;
    private boolean debogage;

    /**
     * Constructeur privé pour empêcher l'instanciation externe.
     */
    private OptionManager() {
        this.nombreDeJoueurs = 4; // Valeur par défaut
        this.nombreIa = 3; // Valeur par défaut
        this.joker = true; // Valeur par défaut
        this.difficulte = new HashMap<>(); // Valeur par défaut
        for (int i = 1; i < nombreIa + 1; i++) {
            this.difficulte.put("IA" + i, 2); // Ajout des niveaux de difficulté
        }
        this.nombreDePointsVictoire = 500; // Valeur par défaut
        this.debogage = false; // Valeur par défaut
        this.combinaisonsPossibles = new HashMap<>();
        for (TypeCombinaison type : TypeCombinaison.values()) {
            combinaisonsPossibles.put(type, true); // Initialisation de toutes les combinaisons à true
        }
    }   

    /**
     * Méthode pour obtenir l'instance unique de OptionManager.
     *
     * @return instance de OptionManager
     */
    public static OptionManager getInstance() {
        if (instance == null) {
            instance = new OptionManager();
        }
        return instance;
    }

    /** 
     * Méthode pour obtenir le nombre de joueurs.
     *
     * @return nombre de joueurs
     */
    public int getNombreDeJoueurs() {
        return nombreDeJoueurs;
    }

    /**
     * Méthode pour définir le nombre de joueurs.
     *
     * @param nombreDeJoueurs le nombre de joueurs
     */
    public void setNombreDeJoueurs(int nombreDeJoueurs) {
        if (nombreDeJoueurs < 2 || nombreDeJoueurs > 4) {
            throw new IllegalArgumentException("Le nombre de joueurs doit être entre 2 et 4.");
        }
        this.nombreDeJoueurs = nombreDeJoueurs;
        if (this.nombreIa > nombreDeJoueurs) {
            // Ajuster le nombre d'IA si nécessaire en retirant les IA en trop
            for (int nIA = this.nombreIa; nIA > this.nombreDeJoueurs; nIA--) {
                difficulte.remove("IA" + nIA);
            }
            this.nombreIa = nombreDeJoueurs; 
        }
    }

    /**
     * Méthode pour obtenir le nombre d'IA.
     *
     * @return nombre d'IA
     */
    public int getNombreIa() {
        return nombreIa;
    }

    /**
     * Méthode pour définir le nombre d'IA.
     *
     * @param nombreIa le nombre d'IA
     */
    public void setNombreIa(int nombreIa) {
        if (nombreIa < 0 || nombreIa > nombreDeJoueurs) {
            throw new IllegalArgumentException(String.format("Le nombre d'IA doit être entre 0 et %s.", nombreDeJoueurs));
        }
        if (this.nombreIa > nombreIa) {
            for (int i = nombreIa + 1; i <= this.nombreIa; i++) {
                this.difficulte.remove("IA" + i);
            }
        } else {
            for (int i = this.nombreIa; i < nombreIa + 1; i++) {
                this.difficulte.put("IA" + i, 2); // Ajout des niveaux de difficulté
            }
        }
        this.nombreIa = nombreIa;
    }

    /**
     * Méthode pour savoir si le joker est activé.
     *
     * @return true si le joker est activé, false sinon
     */
    public boolean isJoker() {
        return joker;
    }

    /**
     * Méthode pour définir si le joker est activé.
     *
     * @param joker true pour activer le joker, false pour le désactiver
     */
    public void setJoker(boolean joker) {
        this.joker = joker;
    }

    /**
     * Méthode pour obtenir les combinaisons possibles.
     *
     * @return une map des combinaisons possibles
     */
    public Map<TypeCombinaison, Boolean> getCombinaisonsPossibles() {
        return combinaisonsPossibles;
    }

    /**
     * Méthode pour définir si une combinaison est activée ou non.
     *
     * @param combinaison la combinaison à définir
     * @param estActive true pour activer la combinaison, false pour la désactiver
     */
    public void setCombinaison(TypeCombinaison combinaison, boolean estActive) {
        if (combinaison == null) {
            throw new IllegalArgumentException("La combinaison ne peut pas être nulle.");
        } else if (!this.combinaisonsPossibles.containsKey(combinaison)) {
            throw new IllegalArgumentException("Combinaison inconnue : " + combinaison);
        }
        this.combinaisonsPossibles.put(combinaison, estActive);
    }

    /**
     * Méthode pour obtenir la difficulté de toutes les IA.
     *
     * @return une map des difficultés des IA
     */
    public HashMap<String, Integer> getDifficulte() {
        return difficulte;
    }
    
    /**
     * Méthode pour obtenir la difficulté d'une IA spécifique.
     *
     * @param i l'index de l'IA (1-indexé)
     * @return la difficulté de l'IA
     */
    public int getDifficulte(int i) {
        if (!this.difficulte.containsKey("IA" + i)) {
            throw new IllegalArgumentException(String.format("L'index de l'IA doit être entre 1 et %s.", nombreIa));
        }
        return this.difficulte.get("IA" + i);
    }
    
    /**
     * Méthode pour obtenir la difficulté d'une IA spécifique.
     *
     * @param i l'index de l'IA (1-indexé)
     * @return la difficulté de l'IA
     */
    public void setDifficulte(int i, int difficulte) {
        if (i - 1 < 0 || i - 1 >= nombreIa) {
            throw new IllegalArgumentException(String.format("L'index de l'IA doit être entre 1 et %s.", nombreIa));
        }
        if (difficulte < 1 || difficulte > 2) {
            throw new IllegalArgumentException(String.format("La difficulté doit être entre 1 (facile) et 2 (normale)."));
        }
        this.difficulte.put("IA" + i, difficulte);
    }

    /**
     * Méthode pour définir la difficulté de toutes les IA.
     *
     * @param difficulte la difficulté à définir
     */
    public void setAllDifficultes(int difficulte) {
        if (difficulte < 1 || difficulte > 2) {
            throw new IllegalArgumentException(String.format("La difficulté doit être entre 1 (facile) et 2 (normale)."));
        }
        for (int i = 1; i < nombreIa + 1; i++) {
            this.difficulte.put("IA" + i, difficulte);
        }
    }

    /**
     * Méthode pour obtenir le nombre de points de victoire.
     *
     * @return le nombre de points de victoire
     */
    public int getNombreDePointsVictoire() {
        return nombreDePointsVictoire;
    }

    /**
     * Méthode pour définir le nombre de points de victoire.
     *
     * @param nombreDePointsVictoire le nombre de points de victoire
     */
    public void setNombreDePointsVictoire(int nombreDePointsVictoire) {
        if (nombreDePointsVictoire < 1) {
            throw new IllegalArgumentException("Le nombre de points de victoire doit être supérieur à 0.");
        }
        this.nombreDePointsVictoire = nombreDePointsVictoire;
    }

    /**
     * Méthode pour savoir si le mode débogage est activé.
     *
     * @return true si le mode débogage est activé, false sinon
     */
    public boolean isDebogage() {
        return debogage;
    }

    /**
     * Méthode pour définir si le mode débogage est activé.
     *
     * @param debogage true pour activer le mode débogage, false pour le désactiver
     */
    public void setDebogage(boolean debogage) {
        this.debogage = debogage;
    }
}
