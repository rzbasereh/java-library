package org.basereh.dao;

import java.util.List;

public interface ObjectDao<T> {
    public List<T> getAll();
}
