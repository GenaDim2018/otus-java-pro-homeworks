public class Task {
    private String name;
    private TaskStatuses status;
    private final String id;

    Task(String id, String name, TaskStatuses status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public TaskStatuses getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(TaskStatuses status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n" + "Task{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", id='" + id + '\'' +
                '}';
    }
}
