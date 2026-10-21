package lesvinassesgouleyantescode;


public class Position {

    /**
     * Coordonnée x de la position.
     */
    private final int x;

    /**
     * Coordonnée y de la position.
     */
    private final int y;

    /**
     * Constructeur de la classe position
     *
     * @param x coordonnée x
     * @param y coordonnée y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copie une position.
     *
     * @param pos position à copier
     */
    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    /**
     * Retourne une nouvelle position en ajoutant des déplacements.
     *
     * @param dx déplacement en x
     * @param dy déplacement en y
     * @return nouvelle position après déplacement
     */
    public Position ajouter(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }

    /**
     * Retourne une nouvelle position en ajoutant un déplacement sous forme de
     * tableau. Le tableau doit contenir deux valeurs : {dx, dy}.
     *
     * @param delta tableau contenant le déplacement
     * @return nouvelle position après déplacement
     */
    public Position ajouter(int[] delta) {
        return new Position(x + delta[0], y + delta[1]);
    }

    /**
     * Retourne la coordonnée x.
     *
     * @return coordonnée x
     */
    public int avoirX() {
        return x;
    }

    /**
     * Retourne la coordonnée y.
     *
     * @return coordonnée y
     */
    public int avoirY() {
        return y;
    }

    /**
     * Indique si la position est à l'intérieur du plateau de jeu. Le plateau
     * est supposé être de taille 16x16.
     *
     * @return true si la position est dans le plateau, false sinon
     */
    public boolean estDansPlatea() {
        return this.avoirX() >= 0 && this.avoirX() < 16
                && this.avoirY() >= 0 && this.avoirY() < 16;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return "Position{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
