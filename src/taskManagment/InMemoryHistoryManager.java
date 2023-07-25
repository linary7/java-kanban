package taskManagment;

import taskStorage.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private List<Task> taskWatchHistory = new ArrayList<>();
    @Override
    public void addTask(Task task) {
        taskWatchHistory.add(task);
        if (taskWatchHistory.size() > 10) {
            taskWatchHistory.remove(0);
        }
    }

    @Override
    public List<Task> getHistory() {
        return taskWatchHistory;
    }
}
