package lesvinassesgouleyantescode;

import ecranprog.EcranProgFactory;
import ecranprog.Horloge;
import ecranprog.ICouleurCase;
import ecranprog.IDormeur;
import ecranprog.IEcranProg;
import ecranprog.IObs;
import java.util.HashSet;

public class Ecran implements IObs, IDormeur {

    /**
     * Écran
     */
    private IEcranProg ecran;

    /**
     * Indique si il est allumé.
     */
    private boolean allume;

    /**
     * Plateau de jeu.
     */
    public static Plateau plateau = new Plateau();

    /**
     * Joueur du jeu.
     */
    public static Joueur joueur;

    /**
     * Indique si le joueur a gagné la partie.
     */
    public static boolean victoire = false;

    /**
     * Compteur avant la fermeture du jeu après la victoire.
     */
    private int finDuTimer = -1;

    /**
     * Constructeur de la classe Ecran.
     */
    public Ecran() {
        this.ecran = EcranProgFactory.instance();
        this.ecran.ajouterObservateur(this);
        allume = false;
    }

    /**
     * Actions exécutées après le chargement de la fenêtre. Initialise la
     * sauvegarde, le plateau, le joueur et affiche le premier écran.
     */
    @Override
    public void actionsInitiales() {
        SaveFile.saveInit();
        if (SaveFile.getNiveau() >= 6) {
            victoire = true;
            ecranDeVictoire();
            return;
        }

        plateau.initPlateau(SaveFile.getNiveau());
        joueur = new Joueur(SaveFile.getNiveau(), plateau);

        fondEcran(SaveFile.getPlateau());
        ecran.couleur(joueur.posX(), joueur.posY(), Joueur.avoirCouleur());
        ecran.rafraichir();
    }

    /**
     * Gère un clic de souris.
     *
     * @param ligne ligne cliquée
     * @param colonne colonne cliquée
     */
    @Override
    public void clic(int ligne, int colonne) {
        if (victoire) {
            return;
        }

        ecran.couleur(joueur.posX(), joueur.posY(), "#A3B57D");
        joueur.dash(plateau);

        if (joueur.reachedCheckpoint(plateau)) {
            partieGagnee();
            return;
        }

        ecran.couleur(joueur.posX(), joueur.posY(), Joueur.avoirCouleur());
        ecran.rafraichir();
    }

    /**
     * Gère le survol de la souris sur une case.
     *
     * @param ligne ligne survolée
     * @param colonne colonne survolée
     */
    @Override
    public void survol(int ligne, int colonne) {
        // Non utilisé
    }

    /**
     * Gère les entrées clavier.
     *
     * @param c touche pressée
     */
    @Override
    public void clavier(char c) {
        if (victoire) {
            return;
        }

        switch (c) {
            case 'C', 'c' ->
                joueur.couper(plateau);
            default ->
                joueur.deplacement(c, plateau);
        }

        if (joueur.reachedCheckpoint(plateau)) {
            partieGagnee();
            return;
        }

        fondEcranDepuisPlateau(plateau);
        ecran.couleur(joueur.posX(), joueur.posY(), Joueur.avoirCouleur());
        ecran.rafraichir();
    }

    /**
     * Affiche le fond d'écran à partir d'un plateau de caractères.
     *
     * @param charPlateau plateau sous forme de caractères
     */
    void fondEcran(char[][] charPlateau) {
        String couleur = "#75635F";

        HashSet<Position> posLaser = plateau.getCasesLaser();

        if (plateau.getLasersRestants() > 0) {
            if (SaveFile.getNiveau() == 4) {
                niveau4Laser();
            } else {
                niveau5Laser();
            }
        }

        for (int lig = 0; lig < 16; lig++) {
            for (int col = 0; col < 16; col++) {
                switch (charPlateau[lig][col]) {
                    case 'C' ->
                        couleur = "#A3B57D";
                    case 'D' ->
                        couleur = "#2A6F80";
                    case 'A' ->
                        couleur = "#FFDC00";
                    case 'V' ->
                        couleur = "#000000";
                    case 'F' ->
                        couleur = "#1A570B";
                    case 'L' ->
                        couleur = "#5102bf";
                    default ->
                        couleur = "#75635F";
                }
                ecran.couleur(lig, col, couleur);
            }
        }
        ecran.rafraichir();
    }

    /**
     * Affiche les lasers du niveau 4.
     */
    void niveau4Laser() {
        int dureeMs = 100;
        ICouleurCase[] sequence = new ICouleurCase[15];
        for (int col = 0; col < 15; col++) {
            sequence[col] = EcranProgFactory.couleurCase(2, col + 1, "#ff1500", dureeMs);
        }
        ecran.couleurs(sequence);
    }

    /**
     * Affiche les lasers du niveau 5.
     */
    void niveau5Laser() {
        int dureeMs = 100;

        if (plateau.getLasersRestants() >= 1) {
            ICouleurCase[] seq = new ICouleurCase[15];
            for (int col = 0; col < 15; col++) {
                seq[col] = EcranProgFactory.couleurCase(1, col + 1, "#ff1500", dureeMs);
            }
            ecran.couleurs(seq);
        }

        if (plateau.getLasersRestants() >= 2) {
            ICouleurCase[] seq = new ICouleurCase[13];
            for (int li = 0; li < 13; li++) {
                seq[li] = EcranProgFactory.couleurCase(3 + li, 6, "#ff1500", dureeMs);
            }
            ecran.couleurs(seq);
        }

        if (plateau.getLasersRestants() >= 3) {
            ICouleurCase[] seq = new ICouleurCase[11];
            for (int col = 0; col < 11; col++) {
                seq[col] = EcranProgFactory.couleurCase(7, 10 - col, "#ff1500", dureeMs);
            }
            ecran.couleurs(seq);
        }

        if (plateau.getLasersRestants() >= 4) {
            ICouleurCase[] seq = new ICouleurCase[12];
            for (int li = 0; li < 12; li++) {
                seq[li] = EcranProgFactory.couleurCase(11 - li, 3, "#ff1500", dureeMs);
            }
            ecran.couleurs(seq);
        }
    }

    /**
     * Démarre l'écran programmable.
     */
    public void demarrer() {
        ecran = EcranProgFactory.instance();
        ecran.ajouterObservateur(this);
        Horloge.getInstance().enregistrer(this);
        Horloge.getInstance().setDelai(.4);
        ecran.demarrer();
    }

    /**
     * Méthode appelée régulièrement par l'horloge.
     */
    @Override
    public void reveiller() {
        if (victoire && finDuTimer > 0) {
            finDuTimer--;
            if (finDuTimer == 0) {
                System.exit(0);
            }
        }
    }

    /**
     * Indique si l'horloge doit continuer à réveiller l'objet.
     *
     * @return true pour continuer
     */
    @Override
    public boolean continuer() {
        return true;
    }

    /**
     * Gère la victoire du joueur.
     */
    public void partieGagnee() {
        victoire = true;
        ecranDeVictoire();
        SaveFile.writeNumber(SaveFile.getNiveau() + 1);
        finDuTimer = 15;
    }

    /**
     * Affiche l'écran de victoire.
     */
    public void ecranDeVictoire() {
        char[][] winData = SaveFile.victoirePlateau(SaveFile.getNiveau());

        for (int lig = 0; lig < 16; lig++) {
            for (int col = 0; col < 16; col++) {
                String couleur = switch (winData[lig][col]) {
                    case 'G' ->
                        "#00FF00";
                    case 'N' ->
                        "#000000";
                    case 'W' ->
                        "#FFFFFF";
                    default ->
                        "#FFFFFF";
                };
                ecran.couleur(lig, col, couleur);
            }
        }
        ecran.rafraichir();
    }

    /**
     * Affiche le fond d'écran à partir du plateau logique.
     *
     * @param plateau plateau de jeu
     */
    void fondEcranDepuisPlateau(Plateau plateau) {
        HashSet<Position> posLaser = plateau.getCasesLaser();

        if (posLaser.size() > 0) {
            if (SaveFile.getNiveau() == 4) {
                niveau4Laser();
            } else {
                niveau5Laser();
            }
        }

        for (int lig = 0; lig < 16; lig++) {
            for (int col = 0; col < 16; col++) {
                Position pos = new Position(lig, col);
                Case type = plateau.avoirCase(pos);

                String couleur = switch (type) {
                    case SOL ->
                        "#A3B57D";
                    case JOUEUR ->
                        "#2A6F80";
                    case ARRIVEE ->
                        "#FFDC00";
                    case TROU ->
                        "#000000";
                    case BUISSON ->
                        "#1A570B";
                    case BLOC_LASER ->
                        "#5102bf";
                    case LASER ->
                        "#FF0000";
                    case MUR ->
                        "#75635F";
                };

                ecran.couleur(lig, col, couleur);
            }
        }
        ecran.rafraichir();
    }
}
