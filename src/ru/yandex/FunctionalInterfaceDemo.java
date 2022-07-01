package ru.yandex;

public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        StringFunction f1 = new StringFunction() {
            @Override
            public String transform(String arg) {
                return arg.toUpperCase();
            }
        };
    }

    @FunctionalInterface
    interface StringFunction {
        String transform(String arg);

        default void foo() {};
    }
}