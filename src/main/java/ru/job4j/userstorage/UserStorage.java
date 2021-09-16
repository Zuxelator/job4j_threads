package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> storage = new ArrayList<>();

    public synchronized boolean add(User user) {
        return storage.add(user);
    }

    public boolean update(User user) {
        User rsl = findById(user.getId());
        rsl.setAmount(user.getAmount());
        return true;
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user);
    }

    public void transfer(int fromId, int toId, int amount) {
        User from = findById(fromId);
        from.setAmount(from.getAmount() - amount);
        User to = findById(toId);
        to.setAmount(to.getAmount() + amount);
    }

    private synchronized User findById(int id) {
        User rsl = null;
        for (User user : storage) {
            if (user.getId() == id) {
                rsl = user;
            }
        }
        if (rsl == null) {
            throw new IllegalArgumentException("No such element with id: " + id);
        }
        return rsl;
    }
}
