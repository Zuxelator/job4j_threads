package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void offer(T value) {
        synchronized (this) {
            if (queue.size() == capacity) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            queue.add(value);
            notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        T rsl;
        synchronized (this) {
            if (queue.size() == 0) {
                wait();
            }
            rsl = queue.poll();
            notifyAll();
        }
        return rsl;
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public synchronized int getSize() {
        return queue.size();
    }
}