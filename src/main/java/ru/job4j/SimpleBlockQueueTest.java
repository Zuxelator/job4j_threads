package ru.job4j;

public class SimpleBlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        Thread producer = new Thread(
                () -> {
                    int count = 0;
                    System.out.println(Thread.currentThread().getName() + " started");
                    while (count < 5) {
                        count++;
                        queue.offer(3);
                        System.out.println(queue.getSize() + " " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "producer"
        );
        Thread consumer = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    int count = 0;
                    while (count < 5) {
                        count++;
                        queue.poll();
                        System.out.println(queue.getSize() + " " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "consumer"
        );
        producer.start();
        producer.join();
        consumer.start();
    }
}
