package org.basereh.repository;

import java.util.List;

public interface ObjectRepository<T> {
    public List<T> getAll();
}
