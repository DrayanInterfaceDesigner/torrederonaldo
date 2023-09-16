import java.util.ArrayList;

public class Pile {
    private Node head = null;
    public Pile() {}

    public void push(int num) {
        Node n = new Node();
        n.setData(num);

        if(this.head == null) {
            this.head = n;
        }
        else {
            n.setNext(this.head);
        }

        this.head = n;
    }

    public Object pop() {
        int value;
        if(this.isEmpty()) return null;

        value = this.head.getData();

        if(this.head.getNext() == null) {
            this.head = null;
            return value;
        }

        this.head = this.head.getNext();

//        System.out.println(value);
        return value;
    }

    public Object peek() {
        if(this.isEmpty()) return null;
        return this.head.getData();
    }

    public void show() {
        if(this.head == null) {
            System.out.println("[]");
            return;
        }
        Node current = this.head;
        System.out.print("[ ");
        while(current != null) {
            System.out.print( current.getData());
            current = current.getNext();
            if(current != null) System.out.print(" | ");
        }
        System.out.print(" ]\n");
    }

    public Boolean isEmpty() {
        return this.head == null;
    }

    //    CRICA NAO PARSERO
    //    ISSO EH SO PRA AJUDAR NO DISPLAY
    //    PRA FICAR BONITINHO PRA FOTO.
    //    NAO TEM NADA A VER COM A LOGICA
    //    É SÓ PRA FACILITAR O DISPLAY
    //    VERTIKAL
    public ArrayList<Integer> give() {
        ArrayList<Integer> res = new ArrayList<>();
        if(this.head == null) {
            return res;
        }
        Node current = this.head;

        while(current != null) {
            res.add(current.getData());
            current = current.getNext();
        }
        return res;
    }
}
