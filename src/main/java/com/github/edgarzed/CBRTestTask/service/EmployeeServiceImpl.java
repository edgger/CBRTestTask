package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.dao.EmployeeDAO;
import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Collection<Employee> getFiltered(String fname, String mname, String lname, Position position) {
        fname = fname == null || fname.length() == 0 ? null : fname;
        mname = mname == null || mname.length() == 0 ? null : mname;
        lname = lname == null || lname.length() == 0 ? null : lname;
        return employeeDAO.getFiltered(fname, mname, lname, position);
    }
}
