public class Game {

    public static void fini() {
        Champ C = new Champ();
        C.initialization();
        int b = Boat.nbre; //nbre_boat()+1
        C.iniList();

        while (C.index < b && !C.myList.isEmpty()) {
            C.drawBoat();
        }

        C.display( );
    }
}
