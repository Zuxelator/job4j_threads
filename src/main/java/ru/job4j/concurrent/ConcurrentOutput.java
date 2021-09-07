package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread second = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        second.setName("second");
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
