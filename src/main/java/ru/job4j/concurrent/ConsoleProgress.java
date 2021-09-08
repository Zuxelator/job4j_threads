package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    private String[] array = {"\\", "|", "/", "-"};

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(4000);
        progress.interrupt();
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (i == array.length) {
                i = 0;
            }
            System.out.print("\rLoading " + array[i++]);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
