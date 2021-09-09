package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        /* Скачать файл*/
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("file.xml")) {
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
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}