package com.github.edgarzed.CBRTestTask.dao;

import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;

import java.util.Collection;

public interface EmployeeDAO {
    Employee get(String name);
    Collection<Position> getAll();
}
