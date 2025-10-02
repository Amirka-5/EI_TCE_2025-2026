package manager;

import exception.InvalidTimeException;
import factory.TaskFactory;
import model.Priority;
import model.Task;
import observer.TaskObserver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static final Logger logger = Logger.getLogger(ScheduleManager.class.getName());
    private static ScheduleManager instance;
    private final List<Task> tasks = new ArrayList<>();
    private final List<TaskObserver> observers = new ArrayList<>();

    private ScheduleManager() {}

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void addTask(String description, String startTime, String endTime, String priorityStr) {
        logger.info("Attempting to add task: " + description);
        Priority priority;
        try {
            priority = Priority.valueOf(priorityStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            notifyObservers("Error: Invalid priority level. Use High, Medium, or Low.");
            logger.warning("Invalid priority: " + priorityStr);
            return;
        }
        try {
            Task newTask = TaskFactory.create(description, startTime, endTime, priority);
            for (Task existing : tasks) {
                if (hasOverlap(newTask.getStartMin(), newTask.getEndMin(), existing.getStartMin(), existing.getEndMin())) {
                    notifyObservers("Error: Task conflicts with existing task \"" + existing.getDescription() + "\".");
                    logger.warning("Task conflict for: " + description);
                    return;
                }
            }
            tasks.add(newTask);
            notifyObservers("Task added successfully. No conflicts.");
            logger.info("Task added: " + description);
        } catch (InvalidTimeException e) {
            notifyObservers("Error: " + e.getMessage());
            logger.warning("Invalid time for task: " + description);
        }
    }

    public void removeTask(String description) {
        logger.info("Attempting to remove task: " + description);
        Task task = findTask(description);
        if (task == null) {
            notifyObservers("Error: Task not found.");
            logger.warning("Task not found: " + description);
        } else {
            tasks.remove(task);
            notifyObservers("Task removed successfully.");
            logger.info("Task removed: " + description);
        }
    }

    public void editTask(String oldDescription, String newDescription, String newStartTime, String newEndTime, String newPriorityStr) {
        logger.info("Attempting to edit task: " + oldDescription);
        Task taskToEdit = findTask(oldDescription);
        if (taskToEdit == null) {
            notifyObservers("Error: Task not found.");
            logger.warning("Task not found for edit: " + oldDescription);
            return;
        }
        Priority newPriority;
        try {
            newPriority = Priority.valueOf(newPriorityStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            notifyObservers("Error: Invalid priority level. Use High, Medium, or Low.");
            logger.warning("Invalid priority for edit: " + newPriorityStr);
            return;
        }
        try {
            int newStartMin = TaskFactory.timeToMinutes(newStartTime); // Access via class, since static
            int newEndMin = TaskFactory.timeToMinutes(newEndTime);
            if (newStartMin >= newEndMin) {
                notifyObservers("Error: End time must be after start time.");
                logger.warning("Invalid times for edit: " + oldDescription);
                return;
            }
            for (Task existing : tasks) {
                if (existing != taskToEdit && hasOverlap(newStartMin, newEndMin, existing.getStartMin(), existing.getEndMin())) {
                    notifyObservers("Error: Updated task conflicts with existing task \"" + existing.getDescription() + "\".");
                    logger.warning("Conflict on edit for: " + oldDescription);
                    return;
                }
            }
            taskToEdit.setDescription(newDescription);
            taskToEdit.setStartTime(newStartTime, newStartMin);
            taskToEdit.setEndTime(newEndTime, newEndMin);
            taskToEdit.setPriority(newPriority);
            notifyObservers("Task updated successfully.");
            logger.info("Task updated: " + newDescription);
        } catch (InvalidTimeException e) {
            notifyObservers("Error: " + e.getMessage());
            logger.warning("Invalid time on edit for: " + oldDescription);
        }
    }

    public void markCompleted(String description) {
        logger.info("Attempting to mark completed: " + description);
        Task task = findTask(description);
        if (task == null) {
            notifyObservers("Error: Task not found.");
            logger.warning("Task not found for mark: " + description);
        } else if (task.isCompleted()) {
            notifyObservers("Task is already completed.");
            logger.info("Task already completed: " + description);
        } else {
            task.setCompleted(true);
            notifyObservers("Task marked as completed.");
            logger.info("Task marked completed: " + description);
        }
    }

    public List<Task> getSortedTasks() {
        List<Task> sorted = new ArrayList<>(tasks);
        sorted.sort(Comparator.comparingInt(Task::getStartMin));
        return sorted;
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return tasks.stream()
                .filter(t -> t.getPriority() == priority)
                .collect(Collectors.toList());
    }

    private Task findTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }

    private boolean hasOverlap(int start1, int end1, int start2, int end2) {
        return start1 < end2 && end1 > start2;
    }

    private void notifyObservers(String message) {
        if (message.startsWith("Error:")) {
            logger.warning(message);
        } else {
            logger.info(message);
        }
        for (TaskObserver observer : observers) {
            observer.update(message);
        }
    }
}