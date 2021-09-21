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
        storage.putIfAbsent(user.getId(), user);
        return rsl;
    }

    public synchronized boolean update(User user) {
        boolean rsl;
        if (storage.get(user.getId()) == null) {
            rsl = false;
        } else {
            storage.replace(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        User to = storage.get(toId);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        if (from.getAmount() < amount) {
            throw new IllegalArgumentException("Не достаточно средств для перевода");
        }
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
    }
}
