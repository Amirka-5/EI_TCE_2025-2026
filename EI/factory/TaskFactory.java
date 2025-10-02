package factory;

import exception.InvalidTimeException;
import model.Priority;
import model.Task;

public class TaskFactory {
    public static Task create(String description, String startTime, String endTime, Priority priority) throws InvalidTimeException {
        int startMin = timeToMinutes(startTime);
        int endMin = timeToMinutes(endTime);
        if (startMin >= endMin) {
            throw new InvalidTimeException("End time must be after start time.");
        }
        return new Task(description, startTime, endTime, priority, startMin, endMin);
    }

    public static int timeToMinutes(String time) throws InvalidTimeException {
        if (!time.matches("\\d{2}:\\d{2}")) {
            throw new InvalidTimeException("Invalid time format. Use HH:mm.");
        }
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new InvalidTimeException("Time out of range. Hours 00-23, minutes 00-59.");
        }
        return hour * 60 + minute;
    }
}