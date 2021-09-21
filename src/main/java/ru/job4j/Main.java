package ru.job4j;

public class Main {
    public static void main(String[] args) {
        CountBarrier barrier = new CountBarrier(5);
        Thread avaite = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.await();
                }, "avaite"
        );

        Thread count = new Thread(
                () -> {
                    barrier.count();
                    System.out.println(Thread.currentThread().getName() + " started");
                }, "count"
        );
        avaite.start();
        count.start();
    }
}
