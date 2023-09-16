public class Main {
    public static void main (String[] args) {

        Pile p = new Pile();
        Pile p2 = new Pile();
        p.push(1);
        p.push(2);
        p.push(3);
        p.show();


//        p2.show();
//        p2.pop();
//        p2.show();

//        p.pop();
//        p.show();
        Game gayme = new Game();
        gayme.start();
    }
}
