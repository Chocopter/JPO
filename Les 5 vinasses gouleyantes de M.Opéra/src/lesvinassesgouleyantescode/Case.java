package lesvinassesgouleyantescode;


public enum Case {
    /** Case normale traversable. */
    SOL("brown"),

    /** Case où se trouve le joueur. */
    JOUEUR("grey"),

    /** Mur infranchissable. */
    MUR("MidnightBlue"),

    /** Trou mortel pour le joueur. */
    TROU("LightSlateGrey"),

    /** Buisson qui bloque le joueur. */
    BUISSON("MediumSeaGreen"),

    /** Bloc qui lance des lasers et bloque le joueur. */
    BLOC_LASER("MediumSeaGreen"),

    /** Case d'arrivée (checkpoint). */
    ARRIVEE("MediumSeaGreen"),

    /** Laser mortel. */
    LASER("Red");

    /** Couleur de la case (nom CSS ou hexadécimal). */
    private final String couleur;

    /**
     * Constructeur de l'énumération.
     *
     * @param couleur Couleur associée à la case
     */
    Case(String couleur) {
        this.couleur = couleur;
    }

    /**
     * Indique si la case peut être traversée par le joueur.
     *
     * @return true si la case est traversable, false sinon
     */
    public boolean estCaseTraversable() {
        return this != Case.MUR && this != Case.BLOC_LASER && this != Case.BUISSON;
    }

    /**
     * Indique si la case est mortelle pour le joueur.
     *
     * @return true si la case tue le joueur (laser ou trou), false sinon
     */
    public boolean estCaseQuiTue() {
        return this == Case.LASER || this == Case.TROU;
    }

    /**
     * Indique si la case est un buisson.
     *
     * @return true si la case est un buisson, false sinon
     */
    public boolean estBuisson() {
        return this == Case.BUISSON;
    }

    /**
     * Retourne la couleur de la case.
     *
     * @return Couleur de la case sous forme de chaîne
     */
    @Override
    public String toString() {
        return this.couleur;
    }
}
