package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private Map<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean rsl = user != null;
        if (rsl) {
            storage.put(user.getId(), user);
        }
        return rsl;
    }

    public synchronized boolean update(User user) {
        return add(user);
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        from.setAmount(from.getAmount() - amount);
        User to = storage.get(toId);
        to.setAmount(to.getAmount() + amount);
    }
}
