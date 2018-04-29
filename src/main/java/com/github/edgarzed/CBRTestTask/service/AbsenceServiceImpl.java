package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.dao.AbsenceDAO;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

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
    public Collection<Absence> getFiltered(Collection<Employee> employee, LocalDate date) {
        return absenceDAO.getFiltered(employee,date);
    }
}
