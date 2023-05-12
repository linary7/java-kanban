package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;

import java.util.*;

public class Manager {

    private int idCount = 1;
    private Map<Integer, Task> taskList = new HashMap<>();
    private Map<Integer, EpicTask> epicTaskList = new HashMap<>();
    private Map<Integer, Subtask> subtasksList = new HashMap<>();

    public void createEpicTask(EpicTask epicTask) {
        epicTask.setId(idCount);
        epicTaskList.put(epicTask.getId(), epicTask);
        idCount++;
    }

    public void createTask(Task task) {
        task.setId(idCount);
        taskList.put(task.getId(), task);
        idCount++;
    }

    public void addSubtask(Subtask subtask) {
        subtask.setId(idCount);
        subtasksList.put(subtask.getId(), subtask);
        idCount++;
        updateEpicStatus(subtask.getEpicId());
    }

    public void updateSubtask(Subtask subtask) {
        subtasksList.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    public void updateEpicTask(EpicTask epicTask) {
        epicTaskList.put(epicTask.getId(), epicTask);
    }

    public void updateTask(Task task) {
        taskList.put(task.getId(), task);
    }

    public void removeAllTasks() {
        taskList.clear();
        subtasksList.clear();
        epicTaskList.clear();
    }

    public Task getTaskById(Integer id) {
        return taskList.get(id);
    }

    public EpicTask getEpicTaskById(Integer id) {
        return epicTaskList.get(id);
    }

    public List<Subtask> getEpicTaskSubtasksById(Integer id) {
        List<Subtask> subtaskList = new ArrayList<>();
        for (Subtask subtask : subtasksList.values()) {
            if (subtask.getEpicId() == id) {
                subtaskList.add(subtask);
            }
        }
        return subtaskList;
    }

    public void deleteTaskById(Integer id) {
        if (taskList.containsKey(id)) {
            taskList.remove(id);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    public void deleteEpicTaskById(Integer id) {
        if (epicTaskList.containsKey(id)) {
            epicTaskList.remove(id);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    public void deleteSubtaskById(int id) {
        subtasksList.remove(id);
        updateEpicStatus(subtasksList.get(id).getEpicId());
    }


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

    public void updateEpicStatus(int id) {
        List<Subtask> subtasksByEpic = new ArrayList<>();
        for (Subtask subtask : subtasksList.values()) {
            if (subtask.getEpicId() == id) {
                subtasksByEpic.add(subtask);
            }
        }
        for (Subtask subtask : subtasksByEpic) {
            if (subtask.getStatus().equals("IN_PROGRESS")) {
                epicTaskList.get(id).setStatus("IN_PROGRESS");
                return;
            }
        }
        for (Subtask subtask : subtasksByEpic) {
            if (subtask.getStatus().equals("DONE")) {
                for (Subtask doneSubtask : subtasksByEpic) {
                    if (!doneSubtask.getStatus().equals("DONE")) {
                        epicTaskList.get(id).setStatus("IN_PROGRESS");
                        return;
                    }
                }
                epicTaskList.get(id).setStatus("DONE");
            }
        }
    }
}

