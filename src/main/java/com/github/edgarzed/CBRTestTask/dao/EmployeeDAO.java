package com.github.edgarzed.CBRTestTask.dao;

import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;

import java.util.Collection;

public interface EmployeeDAO {
    Employee get(int id);
    Employee save(Employee employee);
    Collection<Employee> getAll();
    Collection<Employee> getFiltered(String fname, String mname, String lname, Position position);
}
