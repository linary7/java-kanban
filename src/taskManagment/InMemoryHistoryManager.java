package taskManagment;

import taskStorage.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private List<Task> taskWatchHistory = new ArrayList<>();
    @Override
    public void addTask(Task task) {
        taskWatchHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        if (taskWatchHistory.size() < 10) {
            return taskWatchHistory;
        } else {
            List<Task> lastTasksHistory = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                lastTasksHistory.add(taskWatchHistory.get(i));
            }
            return lastTasksHistory;
        }
    }
}
