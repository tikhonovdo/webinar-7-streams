package ru.yandex.streams;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ru.yandex.streams.TaskUtils.getTasksStream;

public class Intermediate {
    public static void main(String[] args) {

        System.out.println("\n=== map example ===");
        getTasksStream()
                .map(t -> t.getId())
                .forEach(System.out::println);


        System.out.println("\n=== filter example ===");
        getTasksStream()
                .filter(t -> t.getId() % 2 == 0)
                .forEach(System.out::println);


        System.out.println("\n=== flatMap example ===");
        getTasksStream()
                .map(t -> t.getSubtasksIds())
                .flatMap(s -> s.stream())
                .forEach(System.out::println);


        System.out.println("\n=== peek, distinct example ===");
        Stream.of(1, 1, 2, 2, 3, 3)
                .peek(i -> System.out.println("peek: " + i))
                .distinct()
                .forEach(System.out::println);


        System.out.println("\n=== limit, sorted example ===");
        new Random().ints()
                .limit(10)
                .peek(i -> System.out.println("peek: " + i))
                .sorted()
                .forEach(System.out::println);


        System.out.println("\n=== skip example ===");
        getTasksStream()
                .skip(5)
                .forEach(System.out::println);


        System.out.println("\n=== takeWhile example ===");
        getTasksStream()
                .takeWhile(t -> t.getId() < 5)
                .forEach(System.out::println);

        System.out.println("\n=== takeWhile2 example ===");
        Stream.of(1,2,3,4,5,1,2,3)
                .takeWhile(t -> t < 5)
                .forEach(System.out::println);


        System.out.println("\n=== dropWhile example ===");
        getTasksStream()
                .dropWhile(t -> t.getId() < 5)
                .forEach(System.out::println);

        System.out.println("\n=== dropWhile2 example ===");
        Stream.of(1,2,3,4,5,1,2,3)
                .dropWhile(t -> t < 4)
                .forEach(System.out::println);


        System.out.println("\n=== boxed example ===");
        IntStream.of(1, 2, 3, 4, 5)
                //.filter(i -> i != null) // error
                .boxed()
                .map(i -> (i % 2 == 0) ? null : i)
                .filter(i -> i != null)
                .forEach(System.out::println);
    }

}

