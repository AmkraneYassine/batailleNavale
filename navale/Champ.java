import java.util.Random;
import java.util.Scanner;

public class Grid {

    protected int[][] userChamp;
    protected int[][] init;
    protected int lon = 10;
    protected int lar = 10;


    static Scanner myScan = new Scanner( System.in );
    static Random rdm = new Random();
    static boolean endGame;
    static int index = 0;

    public boolean inUserCh(int x, int y) {
        // dans userChamp ?
        return (x >= 0 && x < lon && y >= 0 && y < lar);
    }

    public boolean veriPos(int x, int y) {
        // Vérifier si (x,y) appartient à la matrice
        if (!inUserCh( x, y ))
            return false;

        if (userChamp[x][y] == 0)
            return true;

        return false;
    }

    public boolean pointBaot(int i, int j) {
        // dans userChamp ?
        if (inUserCh( i, j ))
            if (userChamp[i][j] != 0)
                return true;
        return false;
    }

    public boolean touchBoat(int x, int y) {
        // Vérifier si (x,y) appartient à la matrice
        if (!veriPos( x, y ))
            return true;

        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (pointBaot( i, j ))  // verif NESO
                    return true;
        return false;
    }

    public boolean possiBat(int x, int y) {
        if (touchBoat( x, y ))
            return false;

        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (i != x && j != y && !touchBoat( x, y ))  // verif NESO
                    return true;

        return false;
    }


    public void changeSize() {
        // donne le choix des dimensions  (problème d'affichage quand x > 10)
        System.out.println( "Do you want to change the size of grid ?  yes/no " );
        String ans = myScan.nextLine();

        if (ans.equals( "yes" )) {
            System.out.println( "Longueur : " );
            lon = myScan.nextInt() + 1;
            System.out.println( "Largeur : " );
            lar = myScan.nextInt() + 1;

            if (lon < 5 && lar < 5)
                throw new IllegalArgumentException( "Het is klein om te spelen !" ); // langue

        } else if (!ans.equals( "no" ))
            throw new IllegalArgumentException( "C'est yes ou bien, c'est noooo?" );  // relancer changeSize() ?

        userChamp = init = new int[lon][lar];  // dimension par défaut
    }

    public void initialization() {
        //initialisation du jeux
        endGame = false;   //fin du jeu si true

        System.out.println( "Welcome to the battle ship !" );

        changeSize();


        for (int i = 0; i < lon; i++) {
            for (int j = 0; j < lar; j++) // 0 partout
                userChamp[i][j] = init[i][j] = 0;
        }
        System.out.println();
    }
}
