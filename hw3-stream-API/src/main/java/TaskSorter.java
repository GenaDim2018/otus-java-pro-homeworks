import java.util.List;

public class TaskSorter {
    public static List<Task> getTasksByStatus(List<Task> tasks, TaskStatuses status) {
        return tasks.stream().filter(task -> task.getStatus() == status).toList();
    }

    public static boolean isTaskExistsById(List<Task> tasks, String id) {
        return tasks.stream().anyMatch(task -> task.getId().equals(id));
    }

    public static List<Task> sortTasksByStatus(List<Task> tasks) {
        return tasks.stream().sorted((o1, o2) -> {
            if (o1.getStatus() == o2.getStatus()) {
                return 0;
            }
            if (o1.getStatus() == TaskStatuses.OPENED) {
                return -1;
            }
            if (o1.getStatus() == TaskStatuses.CLOSED) {
                return 1;
            }
            if (o2.getStatus() == TaskStatuses.OPENED) {
                return 1;
            }
            return -1;
        }).toList();
    }

    public static int countTasksByStatus(List<Task> tasks, TaskStatuses status) {
        return (int) tasks.stream().filter(task -> task.getStatus() == status).count();
    }
}
