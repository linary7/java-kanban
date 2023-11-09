package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;
import taskStorage.TaskStatus;

import java.util.*;

public class InMemoryTaskManager implements TaskManager{

    private int idCount = 1;
    protected Map<Integer, Task> taskList = new HashMap<>();
    protected Map<Integer, EpicTask> epicTaskList = new HashMap<>();
    protected Map<Integer, Subtask> subtasksList = new HashMap<>();
    protected HistoryManager historyManager = Managers.getDefaultHistoryManager();


    @Override
    public void createEpicTask(EpicTask epicTask) {
        epicTask.setId(idCount);
        epicTaskList.put(epicTask.getId(), epicTask);
        idCount++;
    }

    @Override
    public void createTask(Task task) {
        task.setId(idCount);
        taskList.put(task.getId(), task);
        idCount++;
    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setId(idCount);
        subtasksList.put(subtask.getId(), subtask);
        idCount++;
        updateEpicStatus(subtask.getEpicId());
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasksList.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    @Override
    public void updateEpicTask(EpicTask epicTask) {
        epicTaskList.put(epicTask.getId(), epicTask);
    }

    @Override
    public void updateTask(Task task) {
        taskList.put(task.getId(), task);
    }

    @Override
    public void removeAllTasks() {
        taskList.clear();
        subtasksList.clear();
        epicTaskList.clear();
    }

    @Override
    public Task getTaskById(Integer id) {
        historyManager.addTask(taskList.get(id));
        return taskList.get(id);
    }

    @Override
    public EpicTask getEpicTaskById(Integer id) {
        historyManager.addTask(epicTaskList.get(id));
        return epicTaskList.get(id);
    }

    @Override
    public List<Subtask> getEpicTaskSubtasksById(Integer id) {
        List<Subtask> subtaskList = new ArrayList<>();
        for (Subtask subtask : subtasksList.values()) {
            if (subtask.getEpicId() == id) {
                historyManager.addTask(subtaskList.get(id));
                subtaskList.add(subtask);
            }
        }
        return subtaskList;
    }

    @Override
    public void deleteTaskById(Integer id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    @Override
    public void deleteEpicTaskById(Integer id) {
        if (epicTaskList.containsKey(id)) {
            epicTaskList.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    @Override
    public void deleteSubtaskById(int id) {
        subtasksList.remove(id);
        historyManager.remove(id);
        updateEpicStatus(subtasksList.get(id).getEpicId());
    }


    @Override
    public void deleteSubtasksByEpicId(int id) {
        List<Integer> subtasksKeysToDelete = new ArrayList<>();
        for (int key : subtasksList.keySet()) {
            if (subtasksList.get(key).getEpicId() == id) {
                subtasksKeysToDelete.add(key);
            }
        }
        for (int key : subtasksKeysToDelete) {
            subtasksList.remove(key);
        }
        updateEpicStatus(id);
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskList.values()) {
            tasks.add(task);
        }
        for (EpicTask epicTask : epicTaskList.values()) {
            tasks.add(epicTask);
            for (Subtask subtask : subtasksList.values()) {
                if (subtask.getEpicId() == epicTask.getId()) {
                    tasks.add(subtask);
                }
            }
        }
        return tasks;
    }

    @Override
    public void updateEpicStatus(int id) {
        List<Subtask> subtasksByEpic = new ArrayList<>();
        for (Subtask subtask : subtasksList.values()) {
            if (subtask.getEpicId() == id) {
                subtasksByEpic.add(subtask);
            }
        }
        for (Subtask subtask : subtasksByEpic) {
            if (subtask.getStatus().equals(TaskStatus.IN_PROGRESS)) {
                epicTaskList.get(id).setStatus(TaskStatus.IN_PROGRESS);
                return;
            }
        }
        for (Subtask subtask : subtasksByEpic) {
            if (subtask.getStatus().equals("DONE")) {
                for (Subtask doneSubtask : subtasksByEpic) {
                    if (!doneSubtask.getStatus().equals(TaskStatus.DONE)) {
                        epicTaskList.get(id).setStatus(TaskStatus.IN_PROGRESS);
                        return;
                    }
                }
                epicTaskList.get(id).setStatus(TaskStatus.DONE);
            }
        }
    }

    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();
    }

}

