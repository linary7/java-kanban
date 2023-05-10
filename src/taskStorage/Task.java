package taskStorage;

public class Task {
    protected String title;
    protected String text;

    protected String status = "NEW";
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n" + title + "\n" + text + "\nСтатус задачи: " + status;
    }

    public String getStatus() {
        return status;
    }
}


