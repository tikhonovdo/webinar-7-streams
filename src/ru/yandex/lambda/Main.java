package ru.yandex.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        // Отдельный класс
        sortWithSeparateClass();

        // Анонимный класс
        sortWithAnonymousClass();

        // Лямбда
        sortWithLambda();

        // Лямбда 2
        sortWithLambda2();

        // Ссылка на метод (method reference)
        sortWithMethodReference();

        // Цепочка из функциональных интерфейсов
        funcInterfaceChain();
    }

    private static List<Task> generateTasks() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tasks.add(new Task(i));
        }
        Collections.shuffle(tasks);
        return tasks;
    }

    private static void sortWithSeparateClass() {
        List<Task> tasks = generateTasks();

        tasks.sort(new TaskComparatorById());

        System.out.println(tasks);
    }

    private static void sortWithAnonymousClass() {
        List<Task> tasks = generateTasks();

        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getId() - o2.getId();
            }
        });

        System.out.println(tasks);
    }

    private static void sortWithLambda() {
        List<Task> tasks = generateTasks();

        tasks.sort((o1, o2) -> o1.getId() - o2.getId());

        System.out.println(tasks);
    }

    private static void sortWithLambda2() {
        List<Task> tasks = generateTasks();

        tasks.sort(Comparator.comparingInt(t -> t.getId()));

        System.out.println(tasks);
    }

    private static void sortWithMethodReference() {
        List<Task> tasks = generateTasks();

        tasks.sort(Comparator.comparingInt(Task::getId));

        System.out.println(tasks);
    }

    private static void funcInterfaceChain() {
        List<Task> tasks = generateTasks();

        Consumer<Task> printId = t -> System.out.println(t.getId());
        Consumer<Task> printToString = t -> System.out.println(t.toString());
        forEach(tasks, printId.andThen(printToString));
    }

    private static void forEach(List<Task> tasks, Consumer<Task> consumer) {
        for (Task t : tasks) {
            consumer.accept(t);
        }
    }
}

class Task {
    private final int id;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task" + id;
    }
}

class Car {
    private final int id;

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return 1;
    }

    @Override
    public String toString() {
        return "Car" + id;
    }
}

class TaskComparatorById implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getId() - o2.getId();
    }
}
