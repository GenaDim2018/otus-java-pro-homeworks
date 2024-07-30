

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MainApp {
    private static final Logger logger = LogManager.getLogger(MainApp.class.getName());

    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("1", "Выйти на работу", TaskStatuses.CLOSED),
                new Task("2", "Посетить дэйли", TaskStatuses.CLOSED),
                new Task("3", "Выпить кофе", TaskStatuses.CLOSED),
                new Task("4", "Допилить фичу", TaskStatuses.IN_PROGRESS),
                new Task("5", "Выжить на созвонах", TaskStatuses.IN_PROGRESS),
                new Task("6", "Поставить разумные цели на спринт", TaskStatuses.OPENED),
                new Task("7", "Переговорить с руководством по поводу отпуска", TaskStatuses.OPENED),
                new Task("8", "Обсудить повышение", TaskStatuses.OPENED),
                new Task("9", "По итогу дня оценить свою продуктивность", TaskStatuses.OPENED));
        logger.debug(TaskSorter.getTasksByStatus(tasks, TaskStatuses.OPENED));
        logger.debug("Задача 1 существует? "+TaskSorter.isTaskExistsById(tasks, "1"));
        logger.debug("Задача 10 существует? "+TaskSorter.isTaskExistsById(tasks, "10"));
        logger.debug(TaskSorter.sortTasksByStatus(tasks).toString());
        logger.debug("Задач со статусов OPENED "+TaskSorter.countTasksByStatus(tasks, TaskStatuses.OPENED));
    }
}
