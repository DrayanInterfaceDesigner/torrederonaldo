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
                    end = autoResolve(this.A, this.B, this.C);
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

    private int autoResolve(Pile resolveIn, Pile auxiliary, Pile resolveTo) {
//        Pile resolveInBetween = parameter
        while(!resolveIn.isEmpty()) {
            mergeSort(resolveTo, (int)resolveIn.pop(), auxiliary);
        }

//        while(!resolveInBetween.isEmpty()) {
//            mergeSort(resolveTo, (int)resolveInBetween.pop());
//        }

        this.win = this.checkWin();

        System.out.println("\n===========RESULTS AUTORESOLVE=============");
        this.printRods();

        System.out.println("Resolved ;)");
        System.out.println("Resolved in " + (this.moves + 1) + " moves.");

        return 0;
    }

    private void mergeSort(Pile resolveTo, Integer element, Pile auxiliary) {

        while (!resolveTo.isEmpty() && matchesByGameOrder((int)resolveTo.peek(), element)) {
            auxiliary.push((int)resolveTo.pop());
            this.printRods();
        }
        resolveTo.push(element);
        while(!auxiliary.isEmpty()) {
            resolveTo.push((int)auxiliary.pop());
        }

        this.moves++;
    }

    private void setGameOrder(int x) {
        this.gameOrder = x < 1 ? -1 : 1;
    }

    private int charToInt(String str) {
        char letter = str.toUpperCase().charAt(0);
        return (letter - 'A');
    }

    private Boolean checkWin() {
        return ((this.A.isEmpty() && this.B.isEmpty()) || (this.A.isEmpty() && this.C.isEmpty()));
    }

    private void populatePile() {
        for (int x = this.pileSize; x > 0; x--) {
            this.A.push(new Random().nextInt(100 + 1));
        }
        this.printRods();
    }

    private void printRods() {
        System.out.println("\n============GAME STATE=============");
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
    }

    public Boolean isMoveValid(Integer value, Integer destination) {
        if((destination == null)) return true;
        if(destination == 0) return true;
        return this.gameOrder == -1 ? (value < destination) : (value > destination);
    }

    public Boolean matchesByGameOrder(Integer x, Integer y) {
        return this.gameOrder == -1 ? (x < y) : (x > y);
    }
}
