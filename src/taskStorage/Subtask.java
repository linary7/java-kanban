package taskStorage;

public class Subtask extends Task {
    int epicId;

    public Subtask(int epicId, String title, String text) {
        super(title, text);
        this.epicId = epicId;
    }
}