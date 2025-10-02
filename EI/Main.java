import manager.ScheduleManager;
import model.Priority;
import model.Task;
import observer.ConsoleObserver;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addObserver(new ConsoleObserver());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nAstronaut Daily Schedule Organizer");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Edit Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine().trim();
                    if (desc.isEmpty()) {
                        System.out.println("Description cannot be empty.");
                        break;
                    }
                    System.out.print("Enter start time (HH:mm): ");
                    String start = scanner.nextLine().trim();
                    System.out.print("Enter end time (HH:mm): ");
                    String end = scanner.nextLine().trim();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String prio = scanner.nextLine().trim();
                    manager.addTask(desc, start, end, prio);
                    break;
                case 2:
                    System.out.print("Enter description to remove: ");
                    desc = scanner.nextLine().trim();
                    manager.removeTask(desc);
                    break;
                case 3:
                    List<Task> tasks = manager.getSortedTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks scheduled for the day.");
                    } else {
                        System.out.println("Scheduled Tasks:");
                        for (Task t : tasks) {
                            System.out.println(t.getStartTime() + " - " + t.getEndTime() + ": " + t.getDescription() +
                                    " [" + t.getPriority() + "]" + (t.isCompleted() ? " [Completed]" : ""));
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter current description: ");
                    String oldDesc = scanner.nextLine().trim();
                    System.out.print("Enter new description: ");
                    String newDesc = scanner.nextLine().trim();
                    if (newDesc.isEmpty()) {
                        System.out.println("New description cannot be empty.");
                        break;
                    }
                    System.out.print("Enter new start time (HH:mm): ");
                    String newStart = scanner.nextLine().trim();
                    System.out.print("Enter new end time (HH:mm): ");
                    String newEnd = scanner.nextLine().trim();
                    System.out.print("Enter new priority (High/Medium/Low): ");
                    String newPrio = scanner.nextLine().trim();
                    manager.editTask(oldDesc, newDesc, newStart, newEnd, newPrio);
                    break;
                case 5:
                    System.out.print("Enter description to mark as completed: ");
                    desc = scanner.nextLine().trim();
                    manager.markCompleted(desc);
                    break;
                case 6:
                    System.out.print("Enter priority (High/Medium/Low): ");
                    prio = scanner.nextLine().trim();
                    try {
                        Priority p = Priority.valueOf(prio.toUpperCase());
                        List<Task> pTasks = manager.getTasksByPriority(p);
                        pTasks.sort(Comparator.comparingInt(Task::getStartMin));
                        if (pTasks.isEmpty()) {
                            System.out.println("No tasks for priority " + p + ".");
                        } else {
                            System.out.println("Tasks for priority " + p + ":");
                            for (Task t : pTasks) {
                                System.out.println(t.getStartTime() + " - " + t.getEndTime() + ": " + t.getDescription() +
                                        " [" + t.getPriority() + "]" + (t.isCompleted() ? " [Completed]" : ""));
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid priority level. Use High, Medium, or Low.");
                    }
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}