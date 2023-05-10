package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {

    private int idCount = 1;
    private Map<Integer, Task> taskList = new HashMap<>();
    private Map<Integer, EpicTask> epicTaskList = new HashMap<>();
    private Map<Integer, List<Subtask>> subtasksByEpicId = new HashMap<>();

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

    public void createSubtaskList(int id, Subtask subtask) {
        List<Subtask> subtasksList = new ArrayList<>();
        subtasksList.add(subtask);
        subtasksByEpicId.put(id, subtasksList);
    }

    public void updateSubtasks(int epicId, Subtask subtask) {
        subtasksByEpicId.get(epicId).add(subtask);
    }

    public void updateEpicTask(EpicTask epicTask, int taskId) {
        if (epicTaskList.containsKey(taskId)) {
            epicTask.setId(taskId);
            epicTaskList.put(epicTask.getId(), epicTask);
            updateEpicStatus(epicTask.getId());

        } else {
            System.out.println("Такой задачи нет");
        }
    }

    public void updateTask(Task task, int id) {
        if (taskList.containsKey(id)) {
            taskList.put(id, task);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    public void removeAllTasks() {
        taskList.clear();
        subtasksByEpicId.clear();
        epicTaskList.clear();
    }

    public Task getTaskById(Integer id) {
        return taskList.get(id);
    }

    public EpicTask getEpicTaskById(Integer id) {
        return epicTaskList.get(id);
    }

    public List<Subtask> getEpicTaskSubtasksById(Integer id) {
        return subtasksByEpicId.get(id);
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

    public void deleteSubtasksByEpicId(int id) {
        subtasksByEpicId.remove(id);
    }

    public List<Object> getAllTasks() {
        List<Object> tasks = new ArrayList<>();
        for (Task task : taskList.values()) {
            tasks.add(task);
        }
        for (EpicTask epicTask : epicTaskList.values()) {
            tasks.add(epicTask);
            for (int epicId : subtasksByEpicId.keySet()) {
                if (epicId == epicTask.getId()) {
                    for (Subtask subtask : subtasksByEpicId.get(epicId)) {
                        tasks.add(subtask);
                    }
                }
            }
        }
        return tasks;
    }

    public void updateEpicStatus(int id) {
        if (subtasksByEpicId.isEmpty() || subtasksByEpicId.get(id).isEmpty()) {
            return;
        }
        for (Subtask task : subtasksByEpicId.get(id)) {
            if (!task.getStatus().equals("DONE")) {
                for (Subtask notDoneTask : subtasksByEpicId.get(id)) {
                    if (!notDoneTask.getStatus().equals("NEW")) {
                        epicTaskList.get(id).setStatus("IN_PROGRESS");
                        return;
                    }
                }
                return; // epicTask.getCompleteness() не меняет дефолтного значения "NEW"
            }
        }
        epicTaskList.get(id).setStatus("DONE");
    }
}
