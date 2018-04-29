package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;

import java.util.Collection;

public interface EmployeeService {
    Employee get(String name);
    Collection<Position> getAll();
}
