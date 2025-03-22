package ru.girman.java.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.girman.java.demo.util.enums.TaskStatus;

@Data
@AllArgsConstructor
public class Task {
    private String title;
    private String description;
    private int hoursUntilDue;
    private TaskStatus status;
}
