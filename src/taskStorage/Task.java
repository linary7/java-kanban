package taskStorage;

public class Task {
    protected String title;
    protected String text;
    protected int id;
    protected String completeness = "NEW";

    public Task(String title, String text) {
        this.title = title;
        this.text = text;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCompleteness(String completeness) {
        this.completeness = completeness;
    }

    @Override
    public String toString() {
        return "\n" + title + "\n" + text + "\nСтатус задачи: " + completeness;
    }

    public String getCompleteness() {
        return completeness;
    }
}


