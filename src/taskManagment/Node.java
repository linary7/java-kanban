package taskManagment;


import taskStorage.Task;

public class Node {
    Task value;
    Node next;
    Node previous;

    public Node(Task value) {
        this.value = value;
    }

    public taskStorage.Task getValue() {
        return value;
    }

    public void setValue(Task value) {
        this.value = value;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
