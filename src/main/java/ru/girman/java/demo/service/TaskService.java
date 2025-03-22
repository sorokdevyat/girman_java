package ru.girman.java.demo.service;

import ru.girman.java.demo.model.Task;
import ru.girman.java.demo.util.enums.TaskStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(String title, String description, int hoursUntilDue) {
        tasks.add(new Task(title,description, hoursUntilDue,TaskStatus.TODO));
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void editTask(int index, String title, String description, int hoursUntilDue,TaskStatus status) {
        Task task = tasks.get(index);
        task.setTitle(title);
        task.setDescription(description);
        task.setHoursUntilDue(hoursUntilDue);
        task.setStatus(status);
    }
    public void removeTask(int index) {
        tasks.remove(index);
    }

    public List<Task> filterTasksByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByDueTime() {
        return tasks.stream()
                .sorted(Comparator.comparingInt(Task::getHoursUntilDue))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByStatus() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }
}
