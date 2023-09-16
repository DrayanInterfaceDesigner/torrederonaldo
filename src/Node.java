import java.util.Random;
import java.util.UUID;

public class Node {
    private Node next = null;
    private int data;
    public Node () {}

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
