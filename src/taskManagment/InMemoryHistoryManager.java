package taskManagment;

import taskStorage.Task;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager{

    private Node first;
    private Node last;
    private Map<Integer, Node> taskHistory = new HashMap<>();


    @Override
    public void addTask(Task task) {
        Node node = new Node(task);
        if (first != null) {
            last.setNext(node);
            node.setPrevious(last);
        } else {
            first = node;
        }
        last = node;
        taskHistory.put(task.getId(), node);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> history = new LinkedList<>();
        if (first == null){
            return history;
        }
        Node currentNode = first;

        while (currentNode != null){
            history.add(currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        return history;
    }

    @Override
    public void remove(int id){
        Node removedNode = taskHistory.get(id);
        if (removedNode == first && first == last){
            first = null;
            last = null;
        } else if (removedNode == first){
            removedNode.getNext().setPrevious(null);
            first = removedNode.getNext();
        } else if (removedNode == last) {
            removedNode.getPrevious().setNext(null);
            last = removedNode.getPrevious();
        } else if (taskHistory.containsKey(id)){
            removedNode.getPrevious().setNext(removedNode.getNext());
            removedNode.getNext().setPrevious(removedNode.getPrevious());
        }
        taskHistory.remove(id);
    }

}
