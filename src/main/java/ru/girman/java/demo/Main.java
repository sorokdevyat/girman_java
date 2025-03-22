package ru.girman.java.demo;

import ru.girman.java.demo.model.Task;
import ru.girman.java.demo.service.TaskService;
import ru.girman.java.demo.util.enums.TaskStatus;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите команду (add, list, edit, delete, filter, sort, exit):");
            String command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.println("Введите название задачи:");
                    String title = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String description = scanner.nextLine();
                    System.out.println("Введите количество часов до выполнения задачи:");
                    int hoursUntilDue = Integer.parseInt(scanner.nextLine());
                    taskService.addTask(title, description, hoursUntilDue);
                    break;

                case "list":
                    List<Task> tasks = taskService.getTasks();
                    tasks.forEach(task -> System.out.println(task));
                    break;

                case "edit":
                    System.out.println("Введите индекс задачи для редактирования:");
                    int index = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите новое название задачи:");
                    String newTitle = scanner.nextLine();
                    System.out.println("Введите новое описание задачи:");
                    String newDescription = scanner.nextLine();
                    System.out.println("Введите новое количество часов до выполнения задачи:");
                    int newHoursUntilDue = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите новый статус задачи (TODO, IN_PROGRESS, DONE):");
                    TaskStatus newStatus = TaskStatus.valueOf(scanner.nextLine());
                    taskService.editTask(index, newTitle, newDescription, newHoursUntilDue, newStatus);
                    break;

                case "delete":
                    System.out.println("Введите индекс задачи для удаления:");
                    int deleteIndex = Integer.parseInt(scanner.nextLine());
                    taskService.removeTask(deleteIndex);
                    break;

                case "filter":
                    System.out.println("Введите статус для фильтрации (TODO, IN_PROGRESS, DONE):");
                    TaskStatus filterStatus = TaskStatus.valueOf(scanner.nextLine());
                    List<Task> filteredTasks = taskService.filterTasksByStatus(filterStatus);
                    filteredTasks.forEach(task -> System.out.println(task));
                    break;

                case "sort":
                    System.out.println("Введите критерий сортировки (dueTime, status):");
                    String sortBy = scanner.nextLine();
                    List<Task> sortedTasks;
                    if (sortBy.equals("dueTime")) {
                        sortedTasks = taskService.sortTasksByDueTime();
                    } else {
                        sortedTasks = taskService.sortTasksByStatus();
                    }
                    sortedTasks.forEach(task -> System.out.println(task));
                    break;

                case "exit":
                    System.out.println("Выход из системы.");
                    return;

                default:
                    System.out.println("Неизвестная команда.");
                    break;
            }
        }
    }
}
