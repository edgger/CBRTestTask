package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.dao.AbsenceDAO;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Position;
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
    public Collection<Absence> getPageFiltered(int start, int count, String fname, String mname, String lname, Position position, LocalDate date) {
        return absenceDAO.getPageFiltered(start, count, fname, mname, lname, position, date);
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
        return absenceDAO.getCountFiltered(fname, mname, lname, position, date);
    }
}
