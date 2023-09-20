package taskManagment;


import taskStorage.Task;

public class Node {
    private Task value;
    private Node next;
    private Node previous;

    public Node(Task value) {
        this.value = value;
    }

    public Task getValue() {
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
