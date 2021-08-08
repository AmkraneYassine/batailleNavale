import java.util.ArrayList;
import java.util.List;

public class Champ extends Grid{

    public List<Point> myList = new ArrayList<>();
    public List<Boat> boats = new ArrayList<>();

    public void display() { //affichage
        char coX = 'A';
        int coY = 1;

        System.out.print( "  " );
        for (int j = 0; j < lar; j++) { //affichage des coordonnées en Y (0 -> lar)
            if (coY > 8) {
                System.out.print( String.valueOf( coY ) + " " );
                coY++;
            } else {
                System.out.print( String.valueOf( coY ) + "  " );
                coY++;
            }
        }

        System.out.println();
        for (int i = 0; i < lon; i++) { // design
            System.out.print( String.valueOf( coX ) + " " );
            coX++;
            for (int j = 0; j < lar; j++)
                if (userChamp[i][j] == 0)  // mer ou frontière d'un bateau
                    System.out.print( "~  " );
                else {
                    System.out.print( "#  " ); //Boat
                }
            System.out.println();
        }
    }

    public void iniList() { // liste contenant les cases pouvant être un bateau
        String str;
        for (int i = 0; i < lon; i++)
            for (int j = 0; j < lar; j++)
                if (possiBat( i, j ))
                    myList.add( Point.element( i, j ) );  // ajout des cases pouvant être bateau
    }

    public void drawBoat() {

        int repeat = 0, m = 0, n = 0;

        index++;

        do {
            if (myList.isEmpty())
                return;

            int s = myList.size();
            int r = rdm.nextInt( s );  // choix random dans userChamp (la liste myList)
            m = myList.get( r ).getX();
            n = myList.get( r ).getY();
            myList.remove( Point.element( m, n ) );
        } while (!possiBat( m, n ));

        Point pt1 = new Point( m, n );
        List<Point> tempList = Point.init4( m, n );

        int x = 0;
        int y = 0;

        while (true) {
            if (myList.isEmpty() || tempList.isEmpty())
                return;

            int c = tempList.size();
            c = rdm.nextInt( c );  // choix random entre NESO
            x = tempList.get( c ).getX();
            y = tempList.get( c ).getY();
            tempList.remove( Point.element( x, y ) );

            if (possiBat( x, y )) { // dans myList
                myList.remove( Point.element( x, y ) );  // bateau => remove de myList
                break;
            }
        }

        int K = x;
        int L = y;

        while (true) {
            if (inUserCh( K, L )) {
                if (K == m) {
                    L = Point.getKL( n, L );
                } else {
                    K = Point.getKL( m, K );
                }
            }
            repeat = rdm.nextInt( 2 );
            if (!possiBat( K, L )) {
                myList.remove( Point.element( K, L ) );
                repeat = 0;
            }

            userChamp[m][n] = index;
            userChamp[x][y] = index;


            if (repeat == 0) // REPEAT
            {
                Point pt2 = new Point(x, y);
                Boat B = new Boat( pt1, pt2, index);
                boats.add(B);
                return;
            }

            m = x;
            n = y;

            x = K;
            y = L;
        }
    }

    public Point userTir() {
        int x = 0, y = 0;
        do{
            System.out.println( "TEST DE TIR" );
            System.out.println( "Choisir les coordonnées doivent ne pas dépasser (" + lar + ", " + lon + ")" );
            System.out.print( "x = " );
            x = myScan.nextInt();
            System.out.print( "y = " );
            y = myScan.nextInt();
        } while (inUserCh(x, y));

        return new Point( x, y );
    }

    /**
    public String calculDist(String[][] userChamp, int x, int y){
        int d = 2;
        String dist = "";
        do{
            userChamp[x][y] = userChamp[x-1][y-1];
            d++;

            for(int i = 0; i<d; i++){
                for(int j = 0; j<d; j++){
                    if(userChamp[x][y+i]=="#  "){
                        dist = Integer.toString(x) + ", " + Integer.toString(y+i);
                    }
                    else if(userChamp[x+i][y]=="#  "){
                        dist = Integer.toString(x+i) + ", " + Integer.toString(y);
                    }
                    else if(userChamp[x+d][y+i]=="#  "){
                        dist = Integer.toString(x+d) + ", " + Integer.toString(y+i);
                    }
                    else if(userChamp[x+i][y+d]=="#  "){
                        dist = Integer.toString(x+i) + ", " + Integer.toString(y+d);
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        while(userChamp[x][y]!="#  ");

        return "Distance : " + dist;
    }
    */

    public void tir(){

        Point pt = userTir();
        int x = pt.getX();
        int y = pt.getY();

        if (userChamp[x][y] != 0){
            System.out.println("TOUCHER");
            // Delete_Boat
        }
        else{
            // calculDist(userChamp, x, y);
            System.out.println("Calcul du bateau le plus proche");
            // Affichage
        }
    }

    public void deleteBoat(int index) {
        Boat temp = null;
        for (Boat i : boats)
            if (i.getId() == index) {
                temp = i;
                boats.remove( i );
            }

        int n = temp.getPt2().getX(), m = temp.getPt2().getY();

        for (int i = temp.getPt1().getX(); i < n; i++)
            userChamp[i][m] = 0;

       for(int i = temp.getPt1().getY(); i < m; i++)
            userChamp[n][i] = 0;

    }
}
