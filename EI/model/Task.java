package model;

public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private Priority priority;
    private int startMin;
    private int endMin;
    private boolean completed;

    public Task(String description, String startTime, String endTime, Priority priority, int startMin, int endMin) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.startMin = startMin;
        this.endMin = endMin;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime, int startMin) {
        this.startTime = startTime;
        this.startMin = startMin;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime, int endMin) {
        this.endTime = endTime;
        this.endMin = endMin;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getStartMin() {
        return startMin;
    }

    public int getEndMin() {
        return endMin;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}