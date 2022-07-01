package ru.yandex.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {

        // Отдельный класс
        System.out.print("1: ");
        sortWithSeparateClass();

        // Анонимный класс
        System.out.print("2: ");
        sortWithAnonymousClass();

        // Лямбда
        System.out.print("3: ");
        sortWithLambda();

        // Лямбда 2
        System.out.print("4: ");
        sortWithLambda2();

        // Сравнение лямбды и анонимног класса
        anonymousClassAndLambdaDifferences();

        // Пример с effectively final переменной в лямбде
        effectivelyFinal();

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

    private static void anonymousClassAndLambdaDifferences() {
        List<Task> tasks = generateTasks();

        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                Comparator<Task> comparator = this;
                return o1.getId() - o2.getId();
            }
        });
        tasks.sort((o1, o2) -> {
//            Comparator<Task> comparator = this;
            return o1.getId() - o2.getId();
        });

        /* Hey! I'm */ new MrMeeseeks(); // Look at me!
    }

    private static void effectivelyFinal() {
        List<Task> tasks = generateTasks();

        int i = 1;
        tasks.sort((o1, o2) -> {
            // uncomment me and everything will broke!
            //i++;
            return i + o1.getId() - o2.getId();
        });
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

class TaskComparatorById implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        return o1.getId() - o2.getId();
    }
}

class MrMeeseeks {
    public void thisInsideLambdaInsideMrMeeseeks() {
        Comparator<Task> anonymousClassComparator = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                Comparator<Task> comparator = this;
                return o1.getId() - o2.getId();
            }
        };

        Comparator<Task> lambdaComparator = (o1, o2) -> {
            MrMeeseeks mrMeeseeks = this;
            return o1.getId() - o2.getId();
        };
    }
}