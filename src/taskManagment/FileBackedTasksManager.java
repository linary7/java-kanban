package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;
import taskStorage.TaskType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.File;


public class FileBackedTasksManager extends InMemoryTaskManager {

    final private File file;
    public FileBackedTasksManager(File file){
        this.file = file;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        FileBackedTasksManager manager = new FileBackedTasksManager(file);
        Task task = new Task("Task1", "Task1 contents");
        manager.createTask(task);
        manager.getTaskById(task.getId());

        EpicTask epicTask = new EpicTask("EpicTask", "EpicTask contents");
        manager.createEpicTask(epicTask);
        manager.getEpicTaskById(epicTask.getId());
        Subtask subtask = new Subtask(epicTask.getId(), "Subtask", "subtask contents");
        manager.addSubtask(subtask);

        Task task2 = new Task("Task2", "Task2 contents");
        manager.createTask(task2);
        manager.getTaskById(task.getId());

        FileBackedTasksManager.loadFile(file);

    }

    private void save() {
       try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
           bufferedWriter.write(CSVFormatHandler.getHeader());
           bufferedWriter.newLine();

           for (Task task : taskList.values()){
               bufferedWriter.write(CSVFormatHandler.toString(task));
               bufferedWriter.newLine();
           }

           for (EpicTask task : epicTaskList.values()){
               bufferedWriter.write(CSVFormatHandler.toString(task));
               bufferedWriter.newLine();
           }

           for (Subtask task : subtasksList.values()){
               bufferedWriter.write(CSVFormatHandler.toString(task));
               bufferedWriter.newLine();
           }

           bufferedWriter.newLine();
           bufferedWriter.write(CSVFormatHandler.historyToString(historyManager.getHistory()));
       } catch (IOException ioe){
          throw new ManagerSaveException("Возникла проблема обработки файла");
       }
    }

    public static FileBackedTasksManager loadFile(File file){
        FileBackedTasksManager manager = new FileBackedTasksManager(file);

        String[] fileLines = String.valueOf(file).split("/n");
        if (fileLines[0].isBlank()){
            System.out.println("Файл пустой");
            return manager;
        }

        for (int i = 0; i < fileLines.length; i++){
            if (fileLines[i].isBlank()){
                List<Integer> historyIds = CSVFormatHandler.historyFromString(fileLines[i + 1]);
                for (int id : historyIds){
                    if (manager.taskList.containsKey(id)) {
                        manager.historyManager.addTask(manager.taskList.get(id));
                    } else if (manager.epicTaskList.containsKey(id)){
                        manager.historyManager.addTask(manager.epicTaskList.get(id));
                    } else {
                        manager.historyManager.addTask(manager.subtasksList.get(id));
                    }
                }
                return manager;
            } else {
                String[] lineContents = fileLines[i].split(", ");
                if (lineContents[1].equals(String.valueOf(TaskType.SUBTASK))){
                    Subtask subtask = (Subtask) CSVFormatHandler.fromString(fileLines[i]);
                    subtask.setEpicId(Integer.parseInt(lineContents[5]));
                    manager.subtasksList.put(subtask.getId(), subtask);
                } else if (lineContents[1].equals(String.valueOf(TaskType.EPIC))) {
                    EpicTask epicTask = (EpicTask) CSVFormatHandler.fromString(fileLines[i]);
                    manager.epicTaskList.put(epicTask.getId(), epicTask);
                } else {
                    Task task = CSVFormatHandler.fromString(fileLines[i]);
                    manager.taskList.put(task.getId(), task);
                }
            }
        }
        return manager;
    }

    @Override
    public Task getTaskById(Integer id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public EpicTask getEpicTaskById(Integer id) {
        EpicTask task = super.getEpicTaskById(id);
        save();
        return task;
    }

    @Override
    public List<Subtask> getEpicTaskSubtasksById(Integer id) {
        List<Subtask> subtaskList = super.getEpicTaskSubtasksById(id);
        save();
        return subtaskList;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = super.getAllTasks();
        save();
        return tasks;
    }

    @Override
    public void createEpicTask(EpicTask epicTask) {
        super.createEpicTask(epicTask);
        save();
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }
    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void updateEpicTask(EpicTask epicTask){
       super.updateEpicTask(epicTask);
       save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void removeAllTasks() {
        super.removeAllTasks();
        save();
    }

    @Override
    public void deleteEpicTaskById(Integer id) {
        super.deleteEpicTaskById(id);
        save();
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
        save();
    }


    @Override
    public void deleteSubtasksByEpicId(int id) {
        super.deleteSubtasksByEpicId(id);
        save();
    }

    @Override
    public void updateEpicStatus(int id) {
        super.updateEpicStatus(id);
        save();
    }
}
