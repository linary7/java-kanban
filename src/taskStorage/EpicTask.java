package taskStorage;

import java.util.HashMap;

public class EpicTask extends Task {
    int counter = 1;
    protected HashMap<Integer, Subtask> subtasksList = new HashMap<>();

    public EpicTask(String title, String text) {
        super(title, text);
    }


    public void setSubtaskList(Subtask subtask) {
        subtask.setId(counter);
        subtasksList.put(subtask.getId(), subtask);
        counter++;
    }

    public HashMap<Integer, Subtask> getSubtasksList() {
        return subtasksList;
    }

    @Override
    public String toString() {
        String subtasks = "\nСписок подзадач:";
        for (Subtask subtask : subtasksList.values()) {
            subtasks += "\n" + subtask.toString();
        }
        if (!subtasks.equals("\nСписок подзадач:")) {
            return "\n" + title + "\n" + text + "\nСтатус задачи: " + completeness + "\n" + subtasks;
        } else {
            return "\n" + title + "\n" + text + "\nСтатус задачи: " + completeness;
        }
    }
}
