package taskStorage;

public class Subtask extends Task {
    private int epicId;

    public Subtask(int epicId, String title, String text) {
        super(title, text);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
