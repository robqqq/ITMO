package authManager;

import auth.Auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AuthManagerImpl implements AuthManager{
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Set<Auth> users = new HashSet<>();

    @Override
    public void setUsers(Collection<Auth> users) {
        writeLock.lock();
        try {
            this.users.addAll(users);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean checkAuth(Auth auth) {
        readLock.lock();
        try {
            return users.contains(auth);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void addUser(Auth auth) {
        writeLock.lock();
        try {
            users.add(auth);
        } finally {
            writeLock.unlock();
        }
    }
}
