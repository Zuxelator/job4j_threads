package ru.job4j.ref;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), user);
    }

    public User findById(int id) {
        return users.get(id);
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for (User user : users.values()) {
            list.add(User.of(user.getName()));
        }
        return list;
    }
}