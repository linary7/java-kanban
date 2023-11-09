package taskStorage;

public class Task {
    protected String title;
    protected String text;

    protected TaskStatus status = TaskStatus.NEW;
    protected int id;
    protected TaskType type;

    public Task(String title, String text) {
        this.title = title;
        this.text = text;
        this.type = TaskType.TASK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n" + title + "\n" + text + "\nСтатус задачи: " + status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTitle(){
        return title;
    }
    public String getText(){
        return text;
    }

    public TaskType getType(){
        return type;
    }
}


