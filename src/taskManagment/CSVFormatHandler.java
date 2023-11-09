package taskManagment;

import taskStorage.EpicTask;
import taskStorage.Subtask;
import taskStorage.Task;
import taskStorage.TaskType;

import java.util.ArrayList;
import java.util.List;

public class CSVFormatHandler {
    static final String HEADER = "id,type,name,status,description,epic";

    static String toString(Task task){
        String result = task.getId() + ", " + task.getType() +  ", "  + task.getTitle() + ", "
                + task.getStatus() + ", " + task.getText();
        if (task.getType() == TaskType.SUBTASK){
            return result + ", " + ((Subtask) task).getEpicId();
        }
        return result;
    }


    static Task fromString(String line){
        String[] lineContents = line.split(", ");
        int id = Integer.valueOf(lineContents[0]);
        String title = lineContents[2];
        String text = lineContents[4];
        Task task = new Task(title, text);
        task.setId(id);
        return task;
    }

    static String historyToString(HistoryManager manager){
        List<String> result = new ArrayList<>();
        for (Task task : manager.getHistory()){
            result.add(String.valueOf(task.getId()));
        }

        return String.join(", ", result);
    }

    static List<Integer> historyFromString(String history){
        List<Integer> historyList = new ArrayList<>();
        String[] historyIds = history.split(", ");
        for (int i = 0; i < historyIds.length; i++){
            historyList.add(Integer.valueOf(historyIds[i]));
        }
      return historyList;
    }
}
