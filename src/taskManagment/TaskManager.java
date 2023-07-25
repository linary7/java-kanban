package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;


import java.util.List;

public interface TaskManager {
    void createEpicTask(EpicTask epicTask);

    void createTask(Task task);

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void updateEpicTask(EpicTask epicTask);

    void updateTask(Task task);

    void removeAllTasks();

    Task getTaskById(Integer id);

    EpicTask getEpicTaskById(Integer id);

    List<Subtask> getEpicTaskSubtasksById(Integer id);

    void deleteTaskById(Integer id);

    void deleteEpicTaskById(Integer id);

    void deleteSubtaskById(int id);


    void deleteSubtasksByEpicId(int id);

    List<Task> getAllTasks();

    void updateEpicStatus(int id);

    List<Task> getHistory();

}
