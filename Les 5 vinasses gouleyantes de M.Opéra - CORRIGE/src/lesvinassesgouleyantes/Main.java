package lesvinassesgouleyantes;

import ecranprog.Horloge;
import lesvinassesgouleyantescode.Ecran;
import lesvinassesgouleyantescode.Joueur;
import lesvinassesgouleyantescode.SaveFile;


public class Main {

    /** 
     * Niveau actuel du jeu. 
     */
    static int NIVEAU;
    
    /**
     * L'écran
     */
    static Ecran ecr = new Ecran();

    /** 
     * Nombre de lasers à éteindre sur le plateau (à ajuster selon le niveau).
     */
    final static int NB_LASER = 4;


    public static void main(String[] args) {
        SaveFile.saveInit();
        
        NIVEAU = SaveFile.getNiveau();
        // Charger le niveau correspondant
        lancerNiveau();
        Thread horloge = new Thread(() -> Horloge.getInstance().run());
        horloge.start();
        // Démarrage de l'écran et de l'interface graphique
        ecr.demarrer();
        
    }

    /**
     * Lance le niveau choisi.
     *
     * @param niveau niveau du jeu à lancer
     */
    static void lancerNiveau(){
        switch(NIVEAU){
            case 1 -> niveau1();
            case 2 -> {
                niveau2();
                niveau1();
            }
            case 3 -> {
                niveau3();
                niveau1();
            }
            case 4 -> {
                niveau4();
                niveau1();
                niveau3();
            }
            default -> {
                niveau5();
                niveau1();
                niveau3();
            }
        }
    }

    /**
     * Niveau 1
     */
    static void niveau1() {
        seDeplacer();
    }

    /**
     * Niveau 2
     */
    static void niveau2() {
        dash(2);
    }

    /**
     * Niveau 3
     */
    static void niveau3() {
        String typeCase = "BUISSON";
        couper(typeCase);
    }
    
    /**
     * Niveau 4
     */
    static void niveau4() {
        if(estAllume()){
            eteindreLaser();
        }
        dash(4);
    }

    /**
     * Niveau 5
     */
    static void niveau5() {
        for (int i = 0; i < NB_LASER; i++) {
            eteindreLaser();
        }
        String typeCase = "BUISSON";
        couper(typeCase);
        couper("Buisson");
        dash(5);
    }

    /**
     * Méthode permettant au joueur de se déplacer.
     */
    static void seDeplacer(){
        Joueur.toggleAvancer();
    }
    
    /**
     * Permet d'avancer hyper rapidement dans une direction choisie.
     * @param puissanceDash la puissance du dash en question
     */
    static void dash(int puissanceDash){
        Joueur.changeDash(puissanceDash);
    }
    
    /**
     * Coupe une case du plateau
     */
    static void couper(String typeDeCase){
        if (typeDeCase.toLowerCase().equals("buisson")){
            Joueur.toggleCouper();
        }
    }

    /**
     * Éteint un laser actif sur le plateau.
     */
    static void eteindreLaser() {
        ecr.plateau.eteindreUnLaser();
    }

    /**
     * Vérifie si au moins un laser est encore allumé sur le plateau.
     *
     * @return true si au moins un laser est allumé, false sinon
     */
    static boolean estAllume() {
        return Ecran.plateau.getLasersRestants() > 0;
    }
}
