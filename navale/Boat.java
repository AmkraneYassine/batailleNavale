import java.util.Scanner;

public class Boat {
    static Scanner myScan = new Scanner( System.in );

    Point pt1, pt2;
    int id = 0;

    static int nbre = 5;

    Boat( Point pt1, Point pt2, int id) {
        this.pt1 = pt1;
        this.pt2 = pt2;
        this.id = id;
    }

    public Point getPt1()
    {
        return pt1;
    }
    public Point getPt2()
    {
        return pt2;
    }
    public int getId() { return id; }
    public void setPt1(Point pt1)
    {
        this.pt1 = pt1;
    }
    public void setPt2(Point pt2)
    {
        this.pt2 = pt2;
    }
    public void setIndex(int id)
    {
        this.id = id;
    }

    public void nbre_boat() {
        System.out.println( "Do you want to define the number of boats ?  yes/no " );
        String ans1 = myScan.nextLine();  // ignorer le char spé
        String ans = myScan.nextLine();
        if (ans.equals( "yes" )) {
            System.out.println( "Boats number : " );
            nbre = myScan.nextInt();
        } else if (!ans.equals( "no" )) {
            throw new IllegalArgumentException( "C'est yes ou bien, c'est noooo?" );
        }
    }
}
