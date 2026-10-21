package lesvinassesgouleyantescode;

import java.util.HashMap;
import java.util.HashSet;

public class Joueur {

    /**
     * Coordonnées des cases autour du joueur.
     */
    private static final int[][] AROUND_PLAYER = {
        {1, 1}, {1, 0}, {1, -1},
        {0, 1}, {0, -1},
        {-1, 1}, {-1, 0}, {-1, -1}
    };

    /**
     * Directions possibles du joueur.
     */
    private static final String[] DIRECTION = {"EST", "SUD", "NORD", "WEST"};

    /**
     * Association entre une direction et son déplacement (dx, dy).
     */
    private static final HashMap<String, int[]> DIREC = new HashMap<>();

    static {
        DIREC.put("NORD", new int[]{-1, 0});
        DIREC.put("SUD", new int[]{1, 0});
        DIREC.put("WEST", new int[]{0, -1});
        DIREC.put("EST", new int[]{0, 1});
    }

    /**
     * Association entre les touches clavier et les directions.
     */
    private static final HashMap<Character, String> TOUCHE_DIR = new HashMap<>();

    static {
        TOUCHE_DIR.put('Z', "NORD");
        TOUCHE_DIR.put('S', "SUD");
        TOUCHE_DIR.put('Q', "WEST");
        TOUCHE_DIR.put('D', "EST");

        TOUCHE_DIR.put('z', "NORD");
        TOUCHE_DIR.put('s', "SUD");
        TOUCHE_DIR.put('q', "WEST");
        TOUCHE_DIR.put('d', "EST");
    }

    /**
     * Couleur du joueur.
     */
    private static final String COULEUR = "#fff";

    /**
     * Position actuelle du joueur.
     */
    private Position position;

    /**
     * Direction actuelle du joueur.
     */
    private String direction;

    private static boolean AVANCER = false;

    private static int DISTANCE_DASH = 0;

    private static boolean COUPER = false;

    /**
     * Constructeur de la classe Joueur
     *
     * @param niveau niveau du jeu
     * @param plateau plateau de jeu
     */
    public Joueur(int niveau, Plateau plateau) {
        positionInitial(niveau);
        directionInitial(niveau);
    }

    /**
     * Construit un joueur à une position donnée.
     *
     * @param i coordonnée x
     * @param j coordonnée y
     */
    public Joueur(int i, int j) {
        this.position = new Position(i, j);
        this.direction = "EST";
    }

    /**
     * Retourne la couleur du joueur.
     *
     * @return couleur du joueur
     */
    public static String avoirCouleur() {
        return COULEUR;
    }

    /**
     * Retourne la position du joueur.
     *
     * @return position du joueur
     */
    public Position avoirPosition() {
        return this.position;
    }

    /**
     * Retourne la direction actuelle du joueur.
     *
     * @return direction du joueur
     */
    public String direction() {
        return direction;
    }

    /**
     * Définit la direction du joueur.
     *
     * @param direction nouvelle direction
     */
    public void definirDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Initialise la position du joueur selon le niveau.
     *
     * @param niveau niveau du jeu
     */
    public void positionInitial(int niveau) {
        this.position = switch (niveau) {
            case 1, 2 ->
                new Position(8, 0);
            case 3 ->
                new Position(14, 0);
            case 4 ->
                new Position(0, 1);
            case 5 ->
                new Position(0, 11);
            default ->
                new Position(0, 0);
        };
    }

    /**
     * Retourne la position initiale du joueur pour un niveau donné.
     *
     * @param niveau niveau du jeu
     * @return position initiale
     */
    public Position avoirPositionInitial(int niveau) {
        return switch (niveau) {
            case 1, 2 ->
                new Position(8, 0);
            case 3 ->
                new Position(0, 14);
            case 4 ->
                new Position(0, 1);
            case 5 ->
                new Position(0, 11);
            default ->
                new Position(7, 7);
        };
    }

    /**
     * Initialise la direction du joueur selon le niveau.
     *
     * @param niveau niveau du jeu
     */
    public void directionInitial(int niveau) {
        this.direction = switch (niveau) {
            case 1, 2, 3 ->
                DIRECTION[0];
            case 4, 5 ->
                DIRECTION[1];
            default ->
                "EST";
        };
    }

    /**
     * Définit la position du joueur.
     *
     * @param position nouvelle position
     */
    public void definirPosition(Position position) {
        this.position = position;
    }

    /**
     * Retourne la coordonnée x du joueur.
     *
     * @return coordonnée x
     */
    public int posX() {
        return this.position.avoirX();
    }

    /**
     * Retourne la coordonnée y du joueur.
     *
     * @return coordonnée y
     */
    public int posY() {
        return this.position.avoirY();
    }

    /**
     * Déplace le joueur selon la touche appuyée.
     *
     * @param c touche du clavier
     * @param plateau plateau de jeu
     */
    public void deplacement(char c, Plateau plateau) {
        if (AVANCER) {
            String dir = TOUCHE_DIR.get(c);

            if (dir == null) {
                System.err.println("Touche non gérée");
                return;
            }
            this.direction = dir;
            int[] delta = DIREC.get(dir);
            Position nouvellePos = position.ajouter(delta);
            if (plateau.peutEntiteAllerA(nouvellePos) && plateau.avoirCase(nouvellePos) != Case.BUISSON) {
                this.position = nouvellePos;
            }
            if (plateau.avoirCase(position).estCaseQuiTue() || estSurCaseLaser(plateau)) {
                this.position = avoirPositionInitial(SaveFile.getNiveau());
            }
        }
    }

    /**
     * Indique si le joueur est sur une case laser.
     *
     * @param plateau plateau de jeu
     * @return true si le joueur est sur un laser, false sinon
     */
    public boolean estSurCaseLaser(Plateau plateau) {
        HashSet<Position> posLaser = plateau.getCasesLaser();
        for (Position p : posLaser) {
            if (position.equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indique si une position donnée est sur une case laser.
     *
     * @param plateau plateau de jeu
     * @param pos position à tester
     * @return true si la position est sur un laser, false sinon
     */
    public boolean estSurCaseLaser(Plateau plateau, Position pos) {
        HashSet<Position> posLaser = plateau.getCasesLaser();
        for (Position p : posLaser) {
            if (pos.equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Effectue un dash dans la direction actuelle du joueur.
     *
     * @param plateau plateau de jeu
     * @param distanceDash distance du dash
     */
    public void dash(Plateau plateau) {
        if (DISTANCE_DASH > 0) {
            boolean coherant = true;
            int i = 0;
            Position dernierePoistionCorecte = this.position;

            while (coherant && i < Joueur.DISTANCE_DASH && this.position.estDansPlatea()) {
                Position temp = dernierePoistionCorecte.ajouter(DIREC.get(this.direction));
                if (plateau.avoirCase(temp) == Case.MUR || plateau.avoirCase(temp) == Case.BUISSON) {
                    coherant = false;
                } else if (plateau.avoirCase(temp) == Case.LASER || estSurCaseLaser(plateau, temp)) {
                    return;
                } else {
                    dernierePoistionCorecte = new Position(temp);
                }
                i++;
            }
            plateau.definirCase(this.position.avoirX(), this.position.avoirY(), Case.SOL);
            this.position = new Position(dernierePoistionCorecte);
        }
    }

    /**
     * Coupe les buissons autour du joueur.
     *
     * @param plateau plateau de jeu
     */
    public void couper(Plateau plateau) {
        if (COUPER) {
            int voisinX, voisinY;
            for (int[] coord : AROUND_PLAYER) {
                voisinX = this.posX() + coord[0];
                voisinY = this.posY() + coord[1];
                Position voisin = new Position(voisinX, voisinY);

                if (plateau.avoirCase(voisin).estBuisson()) {
                    plateau.definirCase(voisinX, voisinY, Case.SOL);
                    System.out.println("Buisson couper en " + voisinX + ", " + voisinY);
                }
            }
        }
    }
    
    public static void toggleAvancer(){
        Joueur.AVANCER = !Joueur.AVANCER;
    }

    public static void changeDash(int distDash){
        Joueur.DISTANCE_DASH = distDash;
    }

    public static void toggleCouper(){
        Joueur.COUPER = !Joueur.COUPER;
    }

    /**
     * Indique si le joueur a atteint la case d'arrivée.
     *
     * @param plateau plateau de jeu
     * @return true si le joueur est sur l'arrivée, false sinon
     */
    public boolean reachedCheckpoint(Plateau plateau) {
        return plateau.avoirCase(this.position) == Case.ARRIVEE;
    }
}
