package taskManagment;

import taskStorage.Task;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    Node first;
    Node last;
    HashMap<Integer, Node> taskHistory = new HashMap<>();


    @Override
    public void addTask(Task task) {
        Node node = new Node(task);
        if (first != null) {
            last.next = node;
            node.previous = last;
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
            if (history.contains(currentNode.getValue())) {
                history.remove(currentNode.getValue());
            }
            history.add(currentNode.getValue());
            currentNode = currentNode.next;
        }
        return history;
    }

    @Override
    public void remove(int id){
        if (taskHistory.get(id) == first && first == last){
            taskHistory.remove(id);
            first = null;
            last = null;
        } else if (taskHistory.get(id) == first){
            taskHistory.get(id).next.previous = null;
            first = taskHistory.get(id).next;
            taskHistory.remove(id);
        } else if (taskHistory.get(id) == last) {
            taskHistory.get(id).previous.next = null;
            last = taskHistory.get(id).previous;
            taskHistory.remove(id);
        } else if (taskHistory.containsKey(id)){
            taskHistory.get(id).previous.next = taskHistory.get(id).next;
            taskHistory.get(id).next.previous = taskHistory.get(id).previous;
            taskHistory.remove(id);
        }
    }

}
