package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String fileName;

    public Wget(String url, int speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        /* Скачать файл*/
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            bytesRead = in.read(dataBuffer, 0, 1024);
            long finish = System.currentTimeMillis();
            long diff = finish - start;
            int sleep = 0;
            if (diff != 0 && dataBuffer.length / diff < speed) {
                sleep = dataBuffer.length / speed;
            }
            fileOutputStream.write(dataBuffer, 0, bytesRead);
            Thread.sleep(sleep);
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(sleep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }
        if (!args[0].startsWith("http")) {
            throw new IllegalArgumentException("Check url");
        }
        if (Integer.parseInt(args[1]) <= 0) {
            throw new IllegalArgumentException("Speed should be positive");
        }

        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }
}