package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.dao.AbsenceDAO;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

import static com.github.edgarzed.CBRTestTask.util.ServiceUtil.paramToLowerCase;

@Service
public class AbsenceServiceImpl implements AbsenceService {
    private final AbsenceDAO absenceDAO;

    @Autowired
    public AbsenceServiceImpl(AbsenceDAO absenceDAO) {
        this.absenceDAO = absenceDAO;
    }

    @Override
    public Absence save(Absence absence) {
        return absenceDAO.save(absence);
    }

    @Override
    public Collection<Absence> getAll() {
        return absenceDAO.getAll();
    }

    @Override
    public Collection<Absence> getPageFiltered(int start, int count, String fname, String mname, String lname, Position position, LocalDate date) {
        Employee employee = null;
        if (fname!=null||mname!=null||lname!=null||position!=null){
            employee = new Employee(paramToLowerCase(fname), paramToLowerCase(mname), paramToLowerCase(lname), position);
        }
        return absenceDAO.getPageFiltered(start, count, employee, date);
    }

    @Override
    public Collection<Absence> getPage(int start, int count) {
        return absenceDAO.getPage(start, count);
    }

    @Override
    public long getCount() {
        return absenceDAO.getCount();
    }

    @Override
    public long getCountFiltered(String fname, String mname, String lname, Position position, LocalDate date) {
        Employee employee = null;
        if (fname!=null||mname!=null||lname!=null||position!=null){
            employee = new Employee(paramToLowerCase(fname), paramToLowerCase(mname), paramToLowerCase(lname), position);
        }
        return absenceDAO.getCountFiltered(employee, date);
    }
}
