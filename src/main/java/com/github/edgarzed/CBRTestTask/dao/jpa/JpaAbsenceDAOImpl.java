package com.github.edgarzed.CBRTestTask.dao.jpa;

import com.github.edgarzed.CBRTestTask.dao.AbsenceDAO;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaAbsenceDAOImpl implements AbsenceDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Absence save(Absence absence) {
        if (absence.isNew()) {
            em.persist(absence);
            return absence;
        } else {
            return em.merge(absence);
        }
    }

    @Override
    public Collection<Absence> getAll() {
        return em.createQuery("SELECT a FROM Absence a ORDER BY a.date DESC", Absence.class).getResultList();
    }

    @Override
    public Collection<Absence> getFiltered(Collection<Employee> employees, LocalDate date) {
        return em.createQuery("SELECT a FROM Absence a WHERE (:employees is null or a.employee IN :employees) and (:date is null or a.date = :date)", Absence.class)
                .setParameter("employees", employees)
                .setParameter("date", date)
                .getResultList();
    }
}
