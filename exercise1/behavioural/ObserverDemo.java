package behavioral;

import java.util.*;

// Observer Pattern - Task Notification System
interface TaskObserver {
    void update(String taskName, String status);
}

class Developer implements TaskObserver {
    private String name;
    
    public Developer(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String taskName, String status) {
        System.out.println("Developer " + name + " notified: Task '" + taskName + "' is " + status);
    }
}

class TaskManager {
    private List<TaskObserver> observers = new ArrayList<>();
    
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }
    
    public void completeTask(String taskName) {
        System.out.println("Task completed: " + taskName);
        notifyObservers(taskName, "COMPLETED");
    }
    
    private void notifyObservers(String taskName, String status) {
        for (TaskObserver observer : observers) {
            observer.update(taskName, status);
        }
    }
}

public class ObserverDemo {
    public static void demonstrate() {
        System.out.println("\n=== OBSERVER PATTERN DEMO ===");
        TaskManager taskManager = new TaskManager();
        taskManager.addObserver(new Developer("Alice"));
        taskManager.addObserver(new Developer("Bob"));
        taskManager.completeTask("Implement Login Feature");
    }
}