/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lesvinassesgouleyantescode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *
 * @author choco
 */
public class SaveFile {

    /**
     * Le fichier correspondant
     */
    private static final Path FILE = Paths.get("SaveFile.txt");

    private static int niveau;

    private static char[][] plt;

    /**
     * Écrit le niveau donné dans le fichier de sauvegarde.
     *
     * @param value niveau à sauvegarder (entre 1 et 5 inclus)
     */
    public static void writeNumber(int value) {
        try {
            if (value < 6 && value > 0) {
                Files.writeString(FILE, String.valueOf(value));
                System.out.println("Saved your level, you will now start from lvl : " + value);
            }
        } catch (IOException e) {
            System.err.println("Save File got corrupted");
        }
    }

    /**
     * Lit le niveau sauvegardé depuis le fichier.
     *
     * @return le niveau sauvegardé si valide, -1 si le fichier n'existe pas, -2
     * en cas d'erreur de lecture, -3 si la valeur lue est invalide
     */
    public static int readNumber() {
        try {
            if (Files.exists(FILE)) {
                String saveSata = Files.readString(FILE);
                int lvl = Integer.parseInt(saveSata);
                if (lvl < 6 && lvl > 0) {
                    return lvl;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, file : " + FILE.toString() + " doesn't exist.");
            return -1;
        } catch (IOException e) {
            System.err.println("An error occured couldn't read date from : " + FILE.toString());
            return -2;
        }
        return -3;
    }

    /**
     * Vérifie si le fichier de sauvegarde existe.
     *
     * @return true si le fichier existe, false sinon
     */
    public static boolean fileExist() {
        return Files.exists(FILE);
    }

    /**
     * Initialise la sauvegarde au lancement du jeu. Charge le niveau sauvegardé
     * s'il existe, sinon crée une sauvegarde au niveau 1.
     */
    public static void saveInit() {
        if (SaveFile.fileExist()) {
            niveau = SaveFile.readNumber();
        } else {
            System.out.println("Save not found, starting level 1");
            niveau = 1;
            SaveFile.writeNumber(1);
        }
        plt = bonPlateau(niveau);
    }
    
    /**
     * Permet avoir le niveau
     * @return le niveau
     */
    public static int getNiveau() {
        return niveau;
    }
    
    /**
     * Permet d'avoir le plateau
     * @return le plateau
     */
    public static char[][] getPlateau() {
        return plt;
    }

    /**
     * Plateau pour le niveau 1
     */
    private static final char[][] plateauNiv1 = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //0
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //1
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //2
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //3
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //4
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //5
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //6
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //7
        {'D', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'A'}, //8
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //9
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //10
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //11
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //12
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //13
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //14
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}//15
    };

    /**
     * Plateau pour le niveau 2
     */
    private static final char[][] plateauNiv2 = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //1
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'C', 'C', 'C', 'C', 'C', '#', '#'}, //2
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', 'C', '#', '#', '#', 'C', '#', '#'}, //3
        {'#', '#', '#', '#', '#', '#', 'C', 'C', 'C', 'C', '#', '#', '#', 'C', '#', '#'}, //4
        {'#', '#', '#', '#', '#', '#', 'C', '#', '#', '#', '#', '#', '#', 'C', 'C', 'A'}, //5
        {'#', '#', '#', '#', '#', '#', 'C', 'C', 'C', 'C', '#', '#', '#', '#', '#', '#'}, //6
        {'#', '#', 'C', '#', '#', '#', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#'}, //7
        {'#', '#', 'C', '#', '#', '#', '#', 'V', '#', '#', '#', '#', '#', '#', '#', '#'}, //8
        {'D', 'C', 'C', 'C', 'C', '#', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#'}, //9
        {'#', '#', '#', '#', 'C', '#', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#'}, //10
        {'#', '#', '#', '#', 'C', 'C', 'C', 'C', '#', '#', '#', '#', '#', '#', '#', '#'}, //11
        {'#', '#', '#', '#', '#', '#', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#'}, //12
        {'#', '#', '#', '#', '#', '#', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#'}, //13
        {'#', '#', '#', '#', '#', '#', '#', 'C', 'C', '#', '#', '#', '#', '#', '#', '#'}, //14
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //15
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'} //16
    };

    /**
     * Plateau pour le niveau 3
     */
    private static final char[][] plateauNiv3 = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //1
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //2
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //3
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //4
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //5
        {'#', 'C', 'C', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}, //6
        {'#', 'C', '#', '#', 'C', 'C', 'C', 'C', 'F', 'C', '#', '#', '#', '#', '#', '#'}, //7
        {'#', 'C', 'C', '#', '#', '#', 'C', '#', '#', 'C', '#', '#', '#', '#', '#', '#'}, //8
        {'#', '#', 'C', 'C', 'C', 'C', 'C', '#', '#', 'C', 'C', 'F', 'C', 'C', '#', '#'}, //9
        {'#', '#', 'C', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'C', '#', '#'}, //10
        {'#', 'C', 'C', '#', 'C', 'C', 'C', 'C', 'C', '#', '#', '#', '#', 'C', '#', '#'}, //11
        {'#', 'F', '#', '#', '#', '#', 'C', '#', 'C', 'C', 'C', 'C', '#', 'C', 'C', '#'}, //12
        {'#', 'C', 'C', 'C', '#', '#', 'F', '#', '#', '#', '#', 'C', '#', '#', 'C', '#'}, //13
        {'#', 'C', '#', 'C', '#', '#', 'C', 'C', 'C', '#', 'C', 'C', '#', '#', 'F', '#'}, //14
        {'D', 'C', '#', 'C', 'C', 'C', 'C', '#', 'C', '#', '#', '#', '#', '#', 'C', '#'}, //15
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'A', '#'} //16
    };

    /**
     * Plateau pour le niveau 4
     */
    private static final char[][] plateauNiv4 = {
        {'#', 'D', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', 'C', 'C', 'F', 'C', 'C', 'C', 'C', 'C', '#', 'C', 'C', 'C', 'C', '#', '#'},
        {'L', 'C', '#', '#', '#', 'C', '#', '#', 'C', '#', 'C', '#', 'C', '#', '#', '#'},
        {'#', 'C', 'C', 'C', '#', 'C', 'C', '#', 'C', 'C', 'C', '#', '#', '#', '#', '#'},
        {'#', '#', '#', 'C', '#', '#', 'C', '#', '#', '#', 'C', '#', '#', '#', '#', '#'},
        {'#', '#', 'C', 'C', '#', '#', 'C', '#', '#', '#', 'C', 'C', 'V', 'C', '#', '#'},
        {'#', '#', 'C', '#', '#', 'C', 'C', 'C', 'C', '#', '#', '#', '#', 'C', '#', '#'},
        {'#', '#', 'C', 'C', '#', '#', '#', '#', 'C', '#', '#', 'C', 'C', 'C', 'C', '#'},
        {'#', '#', '#', 'C', 'C', 'C', '#', 'C', 'C', '#', '#', 'C', '#', '#', '#', '#'},
        {'#', 'C', 'C', 'C', '#', 'C', '#', 'C', '#', '#', 'C', 'C', 'C', 'C', '#', '#'},
        {'#', '#', '#', '#', '#', 'C', '#', '#', '#', '#', 'C', '#', '#', 'C', '#', '#'},
        {'#', 'C', '#', 'C', 'C', 'C', '#', 'C', 'C', '#', '#', '#', 'C', 'C', 'C', '#'},
        {'#', 'C', 'C', 'C', '#', '#', '#', 'C', '#', '#', '#', '#', 'C', '#', 'C', '#'},
        {'#', '#', '#', 'C', '#', 'C', 'C', 'C', '#', '#', '#', '#', 'C', '#', '#', '#'},
        {'#', '#', 'C', 'C', 'C', 'C', '#', 'C', '#', '#', 'C', 'C', 'C', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'A', '#', '#', '#', '#', '#'}
    };

    /**
     * Plateau pour le niveau 5
     */
    private static final char[][] plateauNiv5 = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'D', '#', '#', '#', '#'},
        {'L', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', 'C', '#', '#'},
        {'#', '#', 'F', '#', 'C', '#', 'L', '#', 'C', '#', 'C', '#', '#', 'C', '#', '#'},
        {'#', 'C', 'C', '#', 'V', '#', 'C', 'C', 'F', '#', 'C', 'C', '#', 'C', 'C', '#'},
        {'#', '#', '#', '#', 'C', '#', 'C', '#', 'C', '#', '#', '#', '#', '#', 'F', '#'},
        {'#', '#', 'C', 'C', 'C', '#', 'C', '#', '#', '#', 'C', '#', '#', '#', 'C', '#'},
        {'#', 'C', 'C', '#', 'C', '#', 'V', 'V', 'V', '#', 'C', '#', 'C', 'C', 'C', '#'},
        {'#', '#', '#', '#', 'C', 'C', 'V', 'V', 'V', 'C', 'C', 'L', '#', '#', 'C', '#'},
        {'#', '#', 'C', '#', '#', '#', 'V', 'V', 'V', '#', 'C', '#', 'C', 'C', 'C', '#'},
        {'#', '#', 'C', 'C', 'C', '#', 'C', '#', '#', '#', 'C', '#', 'C', '#', '#', '#'},
        {'#', '#', '#', '#', 'F', 'C', 'C', '#', 'C', 'C', 'F', '#', '#', '#', 'C', '#'},
        {'#', 'C', 'C', 'C', 'C', '#', '#', '#', '#', '#', 'C', 'C', 'C', 'F', 'C', '#'},
        {'#', '#', 'C', 'L', 'C', '#', 'C', '#', 'C', '#', '#', '#', 'C', '#', 'V', '#'},
        {'#', 'C', 'C', '#', 'C', 'C', 'C', '#', 'C', 'C', 'F', 'C', 'C', '#', 'C', '#'},
        {'#', '#', '#', '#', '#', '#', 'C', '#', 'C', '#', '#', '#', '#', '#', 'C', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 'A', '#'}
    };


    /**
     * Renvoie le bon plateau en fonction du niveau
     * @param niveau le niveau
     * @return le bon plateau
     */
    public static char[][] bonPlateau(int niveau) {
        return switch (niveau) {
            case 1 ->
                plateauNiv1;
            case 2 ->
                plateauNiv2;
            case 3 ->
                plateauNiv3;
            case 4 ->
                plateauNiv4;
            case 5 ->
                plateauNiv5;
            default ->
                plateauWin5;
        };
    }

    /**
     * Plateau pour l'écran de victoire (YOU WIN 1/5) W = White, N = Black, G =
     * Green
     */
    public static char[][] victoirePlateau(int niveau) {
        return switch (niveau) {
            case 1 ->
                plateauWin1;

            case 2 ->
                plateauWin2;

            case 3 ->
                plateauWin3;

            case 4 ->
                plateauWin4;

            case 5 ->
                plateauWin5;

            default ->
                new char[0][0];

        };
    }
    //my bad c'était trops compliquer de debuger l'ecran de win je copie colle car c'est plu simple mais il faudrait faire des header puis des footer avec juste les 5 derniére ligne comme footer qui change mais bon trops la flemme 
    /**
     * Plateau pour l'écran de victoire (YOU WIN 1/5) W = White, N = Black, G =
     * Green
     */
    private static final char[][] plateauWin1 = {
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 1
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 2
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 3
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'W'}, // 4
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 5
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 6
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 7
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'G', 'W', 'W'}, // 8 
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'W'}, // 9
        {'W', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 10
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 11
        {'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'N', 'W', 'G', 'G', 'G', 'W', 'W'}, // 12
        {'W', 'W', 'G', 'G', 'W', 'W', 'W', 'W', 'N', 'N', 'W', 'G', 'W', 'W', 'W', 'W'}, // 13
        {'W', 'W', 'W', 'G', 'W', 'W', 'W', 'N', 'N', 'W', 'W', 'G', 'G', 'G', 'W', 'W'}, // 14
        {'W', 'W', 'W', 'G', 'W', 'W', 'N', 'N', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W'}, // 15
        {'W', 'W', 'G', 'G', 'G', 'W', 'N', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W'} // 16
    };

    /**
     * Plateau pour l'écran de victoire (YOU WIN 2/5) W = White, N = Black, G =
     * Green
     */
    private static final char[][] plateauWin2 = {
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 1
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 2
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 3
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'W'}, // 4
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 5
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 6
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 7
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'G', 'W', 'W'}, // 8 
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'W'}, // 9
        {'W', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 10
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 11
        {'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'N', 'W', 'G', 'G', 'G', 'W', 'W'}, // 12
        {'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'N', 'N', 'W', 'G', 'W', 'W', 'W', 'W'}, // 13
        {'W', 'W', 'G', 'G', 'G', 'W', 'W', 'N', 'N', 'W', 'W', 'G', 'G', 'G', 'W', 'W'}, // 14
        {'W', 'W', 'G', 'W', 'W', 'W', 'N', 'N', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W'}, // 15
        {'W', 'W', 'G', 'G', 'G', 'W', 'N', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W'} // 16
    };

    /**
     * Plateau pour l'écran de victoire (YOU WIN 1/5) W = White, N = Black, G =
     * Green
     */
    private static final char[][] plateauWin3 = {
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 1
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 2
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 3
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'W'}, // 4
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 5
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 6
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 7
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'G', 'W', 'W'}, // 8 
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'W'}, // 9
        {'W', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 10
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 11
        {'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'N', 'W', 'G', 'G', 'G', 'W', 'W'}, // 12
        {'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'N', 'N', 'W', 'G', 'W', 'W', 'W', 'W'}, // 13
        {'W', 'W', 'W', 'G', 'G', 'W', 'W', 'N', 'N', 'W', 'W', 'G', 'G', 'G', 'W', 'W'}, // 14
        {'W', 'W', 'W', 'W', 'G', 'W', 'N', 'N', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W'}, // 15
        {'W', 'W', 'G', 'G', 'G', 'W', 'N', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W'} // 16
    };

    /**
     * Plateau pour l'écran de victoire (YOU WIN 1/5) W = White, N = Black, G =
     * Green
     */
    private static final char[][] plateauWin4 = {
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 1
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 2
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 3
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'W'}, // 4
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 5
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 6
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 7
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'G', 'W', 'W'}, // 8 
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'W'}, // 9
        {'W', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 10
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 11
        {'W', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'W', 'N', 'W', 'G', 'G', 'G', 'W', 'W'}, // 12
        {'W', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'N', 'N', 'W', 'G', 'W', 'W', 'W', 'W'}, // 13
        {'W', 'W', 'G', 'G', 'G', 'W', 'W', 'N', 'N', 'W', 'W', 'G', 'G', 'G', 'W', 'W'}, // 14
        {'W', 'W', 'W', 'W', 'G', 'W', 'N', 'N', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W'}, // 15
        {'W', 'W', 'W', 'W', 'G', 'W', 'N', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W'} // 16
    };

    /**
     * Plateau pour l'écran de victoire (YOU WIN 1/5) W = White, N = Black, G =
     * Green
     */
    private static final char[][] plateauWin5 = {
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 1
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 2
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W'}, // 3
        {'W', 'W', 'W', 'G', 'W', 'W', 'G', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'W'}, // 4
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 5
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 6
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'W', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 7
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'G', 'W', 'W'}, // 8 
        {'W', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'W', 'G', 'G', 'W', 'W'}, // 9
        {'W', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'G', 'W', 'W', 'G', 'W', 'W'}, // 10
        {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}, // 11
        {'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'N', 'W', 'G', 'G', 'G', 'W', 'W'}, // 12
        {'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'N', 'N', 'W', 'G', 'W', 'W', 'W', 'W'}, // 13
        {'W', 'W', 'G', 'G', 'G', 'W', 'W', 'N', 'N', 'W', 'W', 'G', 'G', 'G', 'W', 'W'}, // 14
        {'W', 'W', 'W', 'W', 'G', 'W', 'N', 'N', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W'}, // 15
        {'W', 'W', 'G', 'G', 'G', 'W', 'N', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W'} // 16
    };
}
