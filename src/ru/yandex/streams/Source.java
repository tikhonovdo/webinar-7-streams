package ru.yandex.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Source {
    public static void main(String[] args) {

        System.out.println("\n=== collection.stream() example ===");
        Collection<Integer> intCollection = getNumbers();
        intCollection.stream()
                .forEach(System.out::println);


        System.out.println("\n=== Arrays.stream() example ===");
        Integer[] intAray = getNumbersArray();
        Arrays.stream(intAray)
                .forEach(System.out::println);


        Stream<Integer> numbers = Stream.empty();


        numbers = Stream.of(1, 2, 3);
        numbers.forEach(System.out::println);


        System.out.println("=== ofNullable example ===");
        Integer n = null;

        System.out.print("ofNullable: ");
        Stream.ofNullable(n)
                .forEach(System.out::println);
        System.out.println();

        System.out.print("of: ");
        Stream.of(n)
                .forEach(System.out::println);
        System.out.println();


        System.out.println("\n=== generate example ===");
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);


        System.out.println("\n=== iterate (infinite) example ===");
        Stream.iterate(0, v -> v + 1)
                .limit(10)
                .forEach(System.out::println);


        System.out.println("\n=== iterate (with condition) example ===");
        Stream.iterate(0, v -> v < 5, v -> v + 1)
                .forEach(System.out::println);


        System.out.println("\n=== Random.ints() example ===");
        Random rnd = new Random();
        rnd.ints() // doubles(), longs()
                .limit(10)
                .forEach(System.out::println);


        System.out.println("\n=== String.chars() example ===");
        String str = "Hello world!";
        str.chars()
                .forEach(System.out::println);

    }

    private static Collection<Integer> getNumbers() {
        return List.of(-1, -2, 0, 1, 2, 3, 4);
    }

    private static Integer[] getNumbersArray() {
        return new Integer[] {1, 2, 3, 4, 5};
    }

}
