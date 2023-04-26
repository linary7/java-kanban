package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;

import java.util.HashMap;

public class Manager {
    private int idCount = 1;
    private HashMap<Integer, Task> taskList = new HashMap<>();
    private HashMap<Integer, EpicTask> epicTaskList = new HashMap<>();

    public void createEpicTask(EpicTask epicTask) {
        epicTask.setId(idCount);
        idCount++;
        epicTaskList.put(epicTask.getId(), epicTask);
    }

    public void createTask(Task task) {
        task.setId(idCount);
        idCount++;
        taskList.put(task.getId(), task);
    }

    public void updateEpicTask(EpicTask epicTask, int taskId) {
        if (epicTaskList.containsKey(taskId)) {
            epicTaskList.put(taskId, epicTask);
        } else {
            System.out.println("Такой задачи нет");
            return;
        }
        if (epicTask.getSubtasksList().isEmpty()) {
            return;
        }
        for (Subtask task : epicTask.getSubtasksList().values()) {
            if (!task.getCompleteness().equals("DONE")) {
                for (Subtask notDoneTask : epicTask.getSubtasksList().values()) {
                    if (!notDoneTask.getCompleteness().equals("NEW")) {
                        epicTask.setCompleteness("IN_PROGRESS");
                        return;
                    }
                }
                return; // epicTask.getCompleteness() не меняет дефолтного значения "NEW"
            }
        }
        epicTask.setCompleteness("DONE");
    }

    public void updateTask(Task task, int id) {
        if (taskList.containsKey(id)) {
            taskList.put(id, task);
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    public void removeAllTasks() {
        if (!taskList.isEmpty() && !epicTaskList.isEmpty()) {
            taskList.clear();
            epicTaskList.clear();
            System.out.println("Задачи удалены");
        } else if (!taskList.isEmpty()) {
            taskList.clear();
            System.out.println("Задачи удалены");
        } else if (!epicTaskList.isEmpty()) {
            epicTaskList.clear();
            System.out.println("Задачи удалены");
        } else {
            System.out.println("Список задач уже пуст");
        }
    }

    public String getTaskById(Integer id) {
        if (taskList.containsKey(id)) {
            return taskList.get(id).toString();
        } else {
            return "Такой задачи нет";
        }
    }

    public String getEpicTaskById(Integer id) {
        if (epicTaskList.containsKey(id)) {
            return epicTaskList.get(id).toString();
        } else {
            return "Такой задачи нет";
        }
    }

    public String getEpicTaskSubtasksById(Integer id) {
        if (epicTaskList.containsKey(id)) {
            return epicTaskList.get(id).getSubtasksList().toString();
        } else {
            return "Такой задачи нет";
        }
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

    public String getAllTasks() {
        String tasks = "";
        for (Task task : taskList.values()) {
            tasks += task.toString();
        }
        String epicTasks = "";
        for (EpicTask epicTask : epicTaskList.values()) {
            epicTasks += "\n" + epicTask.toString();
        }

        return tasks + epicTasks;
    }


}
