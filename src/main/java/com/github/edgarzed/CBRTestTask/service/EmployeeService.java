package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;

import java.util.Collection;

public interface EmployeeService {
    Employee create(String fname, String mname, String lname, Position position);

    Collection<Employee> getFiltered(String fname, String mname, String lname, Position position);
}
