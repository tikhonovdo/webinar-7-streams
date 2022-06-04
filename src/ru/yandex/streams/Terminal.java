package ru.yandex.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.yandex.streams.TaskUtils.getTasksStream;

public class Terminal {
    public static void main(String[] args) {

        System.out.println("\n=== count example ===");
        long count = getTasksStream()
                .count();
        System.out.println(count);


        System.out.println("\n=== findFirst example ===");
        Optional<Task> firstTask = getTasksStream()
                .findFirst();
        System.out.println(firstTask.orElse(null));

        firstTask = Stream.<Task>empty().findFirst();
        System.out.println(firstTask.orElse(null));


        System.out.println("\n=== findAny example ===");
        Optional<Task> anyTask = getTasksStream()
                .findAny();
        System.out.println(anyTask.orElse(null));


        System.out.println("\n=== anyMatch, allMatch, noneMatch example ===");
        boolean anyWithSubtasks = getTasksStream()
                .anyMatch(t -> t.getSubtasksIds() != null && !t.getSubtasksIds().isEmpty());
        System.out.println("anyWithSubtasks: " + anyWithSubtasks);

        boolean allWithSubtasks = getTasksStream()
                .allMatch(t -> t.getSubtasksIds() != null && !t.getSubtasksIds().isEmpty());
        System.out.println("allWithSubtasks: " + allWithSubtasks);

        boolean allPositive = Stream.of(1,3,-5,4,5)
                .allMatch(i -> i > 0);
        System.out.println("allPositive: " + allPositive);

        boolean noneWithSubtasks = getTasksStream()
                .noneMatch(t -> t.getSubtasksIds() != null && !t.getSubtasksIds().isEmpty());
        System.out.println("noneWithSubtasks: " + noneWithSubtasks);

        boolean noneMatchPositive = Stream.of(1,3,-5,4,5)
                .noneMatch(i -> i > 0);
        System.out.println("noneMatchPositive1: " + noneMatchPositive);

        boolean noneMatchNegative = Stream.of(1,3,6,4,5)
                .noneMatch(i -> i < 0);
        System.out.println("noneMatchPositive2: " + noneMatchNegative);


        System.out.println("\n=== min, max example ===");
        Optional<Task> minTask = getTasksStream()
                .min(Comparator.comparingInt(Task::getId));
        System.out.println(minTask.orElse(null));

        Optional<Task> maxTask = getTasksStream()
                .max(Comparator.comparingInt(Task::getId));
        System.out.println(maxTask.orElse(null));


        System.out.println("\n=== reduce example ===");
        int reduceResult = Stream.iterate(1, i -> i <= 5, i -> i + 1)
                .reduce(1, (acum, cur) -> acum * cur);
        System.out.println(reduceResult);

        String concat = Stream.of("a","b","c","d")
                        .reduce("", (accum, cur) -> accum + cur);
        System.out.println(concat);

        System.out.println("\n=== collect example ===");
        List<Integer> collectedList = Stream.of(1, 2, 3, 1, 2, 3)
                .collect(Collectors.toList());
        System.out.println(collectedList);

        Set<Integer> collectedSet = Stream.of(1, 2, 3, 1, 2, 3)
                .collect(Collectors.toSet());
        System.out.println(collectedSet);

        Task task = Stream.of(1, 2, 3)
                .collect(() -> new Task(4), (t, i) -> t.getSubtasksIds().add(i), (t1, t2) -> {});
        System.out.println(task);


        System.out.println("\n=== toArray example ===");
        Task[] tasks = getTasksStream()
                .toArray(Task[]::new);
        System.out.println(Arrays.toString(tasks));


        System.out.println("\n=== sum example ===");
        int sum = Stream.of(1, 2, 3, 1, 2, 3)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);


        System.out.println("\n=== average example ===");
        OptionalDouble avg = Stream.of(1, 2, 3, 1, 2, 3)
                .mapToInt(Integer::intValue)
                .average();
        System.out.println(avg.orElse(0));


        System.out.println("\n=== summaryStatistics example ===");
        IntSummaryStatistics stat = Stream.of(1, 2, 3, 1, 2, 3)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        System.out.println(stat);

    }
}
