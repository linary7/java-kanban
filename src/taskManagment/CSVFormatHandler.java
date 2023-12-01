package taskManagment;

import taskStorage.Subtask;
import taskStorage.Task;
import taskStorage.TaskType;

import java.util.ArrayList;
import java.util.List;

public class CSVFormatHandler {
    private static final String HEADER = "id,type,name,status,description,epic";

    public static String toString(Task task){
        String result = new StringBuilder().append(task.getId()).append(", ").append(task.getType()).append(", ").append(task.getTitle()).append(", ").append(task.getStatus()).append(", ").append(task.getText()).toString();
        if (task.getType() == TaskType.SUBTASK){
            return new StringBuilder().append(result).append(", ").append(((Subtask) task).getEpicId()).toString();
        }
        return result;
    }


    public static Task fromString(String line){
        String[] lineContents = line.split(", ");
        int id = Integer.valueOf(lineContents[0]);
        String title = lineContents[2];
        String text = lineContents[4];
        Task task = new Task(title, text);
        task.setId(id);
        return task;
    }

    public static String historyToString(List<Task> history){
        List<String> result = new ArrayList<>();
        for (Task task : history){
            result.add(String.valueOf(task.getId()));
        }

        return String.join(", ", result);
    }

    public static List<Integer> historyFromString(String history){
        List<Integer> historyList = new ArrayList<>();
        String[] historyIds = history.split(", ");
        for (int i = 0; i < historyIds.length; i++){
            historyList.add(Integer.valueOf(historyIds[i]));
        }
      return historyList;
    }

    public static String getHeader(){
        return HEADER;
    }
}
