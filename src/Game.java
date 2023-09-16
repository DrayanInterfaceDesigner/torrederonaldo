import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int pileSize = 0;
    private Pile A;
    private Pile B;
    private Pile C;
    private Boolean win;
    private int gameOrder = -1;
    private int moves = 0;
    private Pile[] rods;
    public Game() {
        this.A = new Pile();
        this.B = new Pile();
        this.C = new Pile();

        this.rods = new Pile[3];
        this.rods[0] = this.A;
        this.rods[1] = this.B;
        this.rods[2] = this.C;
    }

    public void start() {
        this.GameLoop();
    }

    private void GameLoop() {
        int end = 1;
        while(end != 0){
            switch (this.mainMenu()) {
                case 1 -> {
                    //loop -> moveMenu()
                    end = this.moveLoop();
                }
                case 2 -> {
                    end = autoResolve();
                }
                default -> end = 0;
            }
        }
    }

    private int moveLoop() {
        int end = 1;
        while(end != 0){
            end = this.moveMenu();
        }
        return end;
    }

    private int mainMenu() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Torre da Rabiola Grossa: O JOGO ");

        System.out.println("Qal tamanho da pilha seu safpeka?");
        this.pileSize = scan.nextInt();

        System.out.println("Escolhe bem gostoso, do jeito que deLs gosta:");
        System.out.println("[0] Kressente");
        System.out.println("[1] DKressente");
        this.setGameOrder(scan.nextInt());

        this.populatePile();
        this.printRods();

        System.out.println("Escolhe bem gostoso, do jeito que manuelgome gosta:");
        System.out.println("[0] Paia (sai do gayme)");
        System.out.println("[1] Movimenta (chama o seu vulgo malvadao)");
        System.out.println("[2] Resolve (sou burro)");

        System.out.print("\n");

        return scan.nextInt();
    }

    private int moveMenu() {
        int origin, destination, destinationValue;
        Object poped;
        Scanner scan = new Scanner(System.in);

        System.out.println("Escolhe bem gostoso, do jeito que o dyabo gosta:");

        System.out.println("Oryjem");
        System.out.println("[A] Pylya Ah");
        System.out.println("[B] Pilha Bê");
        System.out.println("[C] Pylha Cê");
        origin = charToInt(scan.nextLine());

        poped = this.rods[origin].pop();

        while(poped == null) {
            System.out.println("Ta vasya rapais");
            origin = charToInt(scan.nextLine());
            poped = this.rods[origin].pop();
        }

        System.out.println("Orydestynoem");
        System.out.println("[A] Pylya Ah");
        System.out.println("[B] Pilha Bê");
        System.out.println("[C] Pylha Cê");
        destination = charToInt(scan.nextLine());

        Object holder = this.rods[destination].peek();
        destinationValue = holder == null ? 0 : (int)holder;

        while(!this.isMoveValid((int)poped, destinationValue)) {
            System.out.println("Nao vai ta podendo... scole otrah");

            destination = charToInt(scan.nextLine());
            holder = this.rods[destination].peek();
            destinationValue = holder == null ? 0 : (int)holder;
        }
        this.rods[destination].push((int)poped);

        this.moves++;
        this.win = this.checkWin();
        this.printRods();

        if(this.win) {
            System.out.println("Resulvido ein [ " + this.moves + "] jogadahs;" );
            System.out.println("Boa bb, mas kalma tah? ta nos bff...");
            return 0;
        }
        else return 1;
    }

    private int autoResolve() {

        int currIndex = 0;
        Integer lastMoved = null;

        Pile current = this.rods[currIndex];

        int[] ni = new int[]{1, 2, 0};
        int[] nni = new int[]{2, 0, 1};

        int nextIndex = ni[currIndex];
        int nextNextIndex = nni[currIndex];

        Integer cPeek = (Integer) current.peek();
        Integer nPeek = (Integer) rods[nextIndex].peek();
        Integer nnPeek = (Integer) rods[nextNextIndex].peek();

        while(!this.checkWin()) {
            nextIndex = ni[currIndex];
            nextNextIndex = nni[currIndex];

//            System.out.println("Resolved in " + this.moves + " moves.");
//            printRods();
            if(current.isEmpty()) {
                currIndex = nextIndex;
                current = rods[currIndex];
                continue;
            }

            cPeek = (Integer) current.peek();
            nPeek = (Integer) rods[nextIndex].peek();
            nnPeek = (Integer) rods[nextNextIndex].peek();

            if(areTheSame(cPeek, nPeek, lastMoved)) {
                currIndex = nextIndex;
                current = rods[currIndex];
                continue;
            }

            if(isMoveValid(cPeek, nPeek)) {
                rods[nextIndex].push((int)current.pop());
                lastMoved = currIndex;
            } else if (isMoveValid(cPeek, nnPeek)) {
                //next next do c tem q ser engual a B nao A
                rods[nextNextIndex].push((int)current.pop());
                lastMoved = currIndex;
            }

            currIndex = nextIndex;
            current = rods[currIndex];
            this.moves++;
        }

        System.out.println("Resolved in " + this.moves + " moves.");
        System.out.println("Resolved ;)");
        this.win = this.checkWin();
        this.printRods();
        return 0;
    }

    private void setGameOrder(int x) {
        this.gameOrder = x < 1 ? -1 : 1;
    }

    private int charToInt(String str) {
        char letter = str.toUpperCase().charAt(0);
        return (letter - 'A');
    }

    private Boolean checkWin() {
        return this.A.isEmpty() && this.B.isEmpty();
    }

    private void populatePile() {
        for (int x = 0; x < this.pileSize; x++) {
            this.A.push(new Random().nextInt(100 + 1));
        }
    }

    private void printRods() {
        System.out.println("LIFO [ A: " + this.A.peek() + ", B: " + this.B.peek() + ", C: " + this.C.peek() + "]");
        System.out.print("A");
        this.A.show();
        System.out.print("\n");

        System.out.print("B");
        this.B.show();
        System.out.print("\n");

        System.out.print("C");
        this.C.show();
        System.out.print("\n");

//        ArrayList<Integer> A = this.A.give();
//        ArrayList<Integer> B = this.B.give();
//        ArrayList<Integer> C = this.C.give();
//
//        System.out.println("―――    ―――    ―――");
//        for(int y = 0; y < this.pileSize; y++) {
//            String a = y > A.size()-1 ? " " : " " + Integer.toString(A.get(y)) + "  ";
//            String b = y > B.size()-1 ? " " : " " + Integer.toString(B.get((B.size() - 1)- y)) + "  ";
//            String c = y > C.size()-1 ? " " : " " + Integer.toString(C.get((C.size() - 1)- y)) + "  ";
//            System.out.println(b + " > " + c);
//            System.out.print(a + "    "  + b + "    " + c + "\n");
//
//        }
//        System.out.println("―――    ―――    ―――");
    }

    public Boolean isMoveValid(Integer value, Integer destination) {
        System.out.println("PORRA:" + (destination == null) + " : " + destination);
        if((destination == null)) return true;
        if(destination == 0) return true;
        return this.gameOrder == -1 ? (value < destination) : (value > destination);
    }
    private int[] symmetricalIDS() {
        int[] res = new int[this.pileSize];
        for(int x = 0; x < this.pileSize; x++) {
            res[x] = 0;
        }
        return res;
    }

    private Boolean areTheSame(Integer x, Integer y, Integer last) {
        if(last == null) return false;
        x = x == null ? 0 : x;
        y = y == null ? 1 : y;
//        last = last == null ? 0 : last;
        // x é quem eu to mexendo no momento
        // y é a cabeca da pilha pra quem eu to enviando x
        // last é o valor da ultima peca mexida
//        if(!x.equals(last)) return false;
        if(y.equals(last)) {
            return false;
        }
        else {
            return true;
        }
//        ! (x == last && y == last)
//        x !== last && y == last
//        x == last && y == last
//        x == last && y !== last

    }
}
