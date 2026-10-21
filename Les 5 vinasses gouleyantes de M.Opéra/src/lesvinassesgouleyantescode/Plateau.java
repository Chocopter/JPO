package lesvinassesgouleyantescode;

import java.util.ArrayList;
import java.util.HashSet;

public class Plateau {

    /**
     * Tableau représentant le plateau de jeu.
     */
    private final Case[][] plateau;

    /**
     * Liste des positions des émetteurs de lasers.
     */
    private ArrayList<Position> emetteurLaser = new ArrayList<>();

    /**
     * Nombre de lasers encore actifs sur le plateau.
     */
    private int laserRestants;

    /**
     * Constructeur de la classe Plateau
     */
    public Plateau() {
        SaveFile.saveInit();
        plateau = new Case[16][16];
        switch (SaveFile.getNiveau()) {
            case 4 ->
                laserRestants = 1;
            case 5 ->
                laserRestants = 4;
            default ->
                laserRestants = 0;
        }
    }

    /**
     * Instance du joueur présent sur le plateau.
     */
    private Joueur j1;

    /**
     * Retourne le nombre de lasers encore actifs.
     *
     * @return nombre de lasers restants
     */
    public int getLasersRestants() {
        if(laserRestants < 0)
            return 0;
        return laserRestants;
    }

    /**
     * Éteint un laser en diminuant le nombre de lasers restants.
     */
    public void eteindreUnLaser() {
        laserRestants--;
    }

    /**
     * Définit le type d'une case du plateau.
     *
     * @param i indice de la ligne
     * @param j indice de la colonne
     * @param c type de la case
     */
    public void definirCase(int i, int j, Case c) {
        plateau[i][j] = c;
    }

    /**
     * Initialise le plateau à partir du niveau donné. Charge le plateau depuis
     * le fichier de sauvegarde et repère les émetteurs de lasers.
     *
     * @param niveau niveau à charger
     */
    public void initPlateau(int niveau) {
        char[][] charPlateau = SaveFile.bonPlateau(niveau);
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                plateau[i][j] = switch (charPlateau[i][j]) {
                    case 'C' ->
                        Case.SOL;
                    case 'D' ->
                        Case.JOUEUR;
                    case 'L' ->
                        Case.BLOC_LASER;
                    case 'F' ->
                        Case.BUISSON;
                    case 'V' ->
                        Case.TROU;
                    case 'A' ->
                        Case.ARRIVEE;
                    case 'R' ->
                        Case.LASER;
                    default ->
                        Case.MUR;
                };
                if (charPlateau[i][j] == 'L') {
                    emetteurLaser.add(new Position(i, j));
                }
            }
        }
    }

    /**
     * Retourne l'ensemble des positions occupées par les lasers. Les positions
     * dépendent du niveau et des émetteurs présents.
     *
     * @return ensemble des positions des cases lasers
     */
    public HashSet<Position> getCasesLaser() {
        HashSet<Position> cases = new HashSet<>();
        if (SaveFile.getNiveau() == 4 && getLasersRestants() > 0) {
            for (Position source : emetteurLaser) {
                int lig = source.avoirX();
                int col = source.avoirY();
                for (int c = col + 1; c < 16; c++) {
                    cases.add(new Position(lig, c));
                }
            }
        } else if (SaveFile.getNiveau() == 5) {
            if (getLasersRestants() > 0) {
                for (int i = emetteurLaser.getFirst().avoirY() + 1; i < 16; i++) {
                    cases.add(new Position(emetteurLaser.getFirst().avoirX(), i));
                }
            }
            if (getLasersRestants() > 2) {
                for (int i = emetteurLaser.get(1).avoirX() + 1; i < 16; i++) {
                    cases.add(new Position(i, emetteurLaser.get(1).avoirX()));
                }
            }
            if (getLasersRestants() > 3) {
                for (int i = 0; i < 16 - emetteurLaser.get(2).avoirY(); i++) {
                    cases.add(new Position(emetteurLaser.get(2).avoirX(), i));
                }
            }
            if (getLasersRestants() == 4) {
                for (int i = emetteurLaser.get(3).avoirY() + 1; i < 16; i++) {
                    cases.add(new Position(emetteurLaser.get(1).avoirX(), i));
                }
            }
        }

        return cases;
    }

    /**
     * Définit le joueur sur le plateau.
     *
     * @param i indice de la ligne
     * @param j indice de la colonne
     */
    public void definirJoueur(int i, int j) {
        this.j1 = new Joueur(i, j);
    }

    /**
     * Retourne la case située à une position donnée.
     *
     * @param position position demandée
     * @return case correspondante
     */
    public Case avoirCase(Position position) {
        if (position.estDansPlatea()) {
            return plateau[position.avoirX()][position.avoirY()];
        } else {
            return plateau[15][15];
        }
    }

    /**
     * Indique si une position contient une case mortelle.
     *
     * @param pos position à tester
     * @return true si la position est mortelle, false sinon
     */
    public boolean contientMort(Position pos) {
        return false;
    }

    /**
     * Retire un buisson du plateau à la position donnée.
     *
     * @param position position du buisson
     */
    public void retirerBuisson(Position position) {
        if (this.avoirCase(position) == Case.BUISSON) {
            this.definirCase(position.avoirX(), position.avoirY(), Case.SOL);
        }
    }

    /**
     * Retourne le joueur présent sur le plateau.
     *
     * @return joueur
     */
    public Joueur avoirJoueur() {
        return j1;
    }

    /**
     * Indique si une entité peut se déplacer vers une position donnée.
     *
     * @param nouvellePosition position cible
     * @return true si le déplacement est possible, false sinon
     */
    public boolean peutEntiteAllerA(Position nouvellePosition) {
        boolean estDansPlateau
                = nouvellePosition.avoirX() >= 0 && nouvellePosition.avoirX() < 16
                && nouvellePosition.avoirY() >= 0 && nouvellePosition.avoirY() < 16;
        return estDansPlateau && this.avoirCase(nouvellePosition).estCaseTraversable();
    }

    /**
     * Retourne la position de la case d'arrivée.
     *
     * @return position de l'arrivée, ou null si absente
     */
    public Position posArrivee() {
        boolean estFini = true;
        int i = 0;
        Position pos = null;
        while (estFini && i < plateau.length) {
            int j = 0;
            while (estFini && j < plateau[i].length) {
                if (plateau[i][j] == Case.ARRIVEE) {
                    pos = new Position(i, j);
                    estFini = false;
                }
                j++;
            }
            i++;
        }
        return pos;
    }
}
