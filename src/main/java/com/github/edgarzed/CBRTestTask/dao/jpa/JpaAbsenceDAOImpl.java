package com.github.edgarzed.CBRTestTask.dao.jpa;

import com.github.edgarzed.CBRTestTask.dao.AbsenceDAO;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Position;
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
    @Transactional
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
        return em.createQuery("SELECT a FROM Absence a LEFT JOIN FETCH a.employee e LEFT JOIN FETCH e.position ORDER BY a.date DESC", Absence.class)
                .getResultList();
    }

    @Override
    public Collection<Absence> getPage(int start, int count) {
        return em.createQuery("SELECT a FROM Absence a LEFT JOIN FETCH a.employee e LEFT JOIN FETCH e.position ORDER BY a.date DESC", Absence.class)
                .setMaxResults(count)
                .setFirstResult(start)
                .getResultList();
    }

    @Override
    public Collection<Absence> getPageFiltered(int start, int count, String fname, String mname, String lname, Position position, LocalDate date) {
        return em.createQuery("SELECT a FROM Absence a LEFT JOIN FETCH a.employee e LEFT JOIN FETCH e.position p WHERE (:fname is null or UPPER(e.firstName) = UPPER(:fname)) and (:mname is null or UPPER(e.middleName) = UPPER(:mname)) and (:lname is null or UPPER(e.lastName) = UPPER(:lname)) and (:position is null or e.position = :position) and (:date is null or a.date = :date) ORDER BY a.date DESC", Absence.class)
                .setParameter("fname", fname)
                .setParameter("mname", mname)
                .setParameter("lname", lname)
                .setParameter("position", position)
                .setParameter("date", date)
                .setMaxResults(count)
                .setFirstResult(start)
                .getResultList();
    }

    @Override
    public long getCount() {
        return (long) em.createQuery("SELECT COUNT (a.id) FROM Absence a").getSingleResult();
    }

    @Override
    public long getCountFiltered(String fname, String mname, String lname, Position position, LocalDate date) {
        return (long) em.createQuery("SELECT COUNT (a.id) FROM Absence a LEFT JOIN a.employee e LEFT JOIN e.position p  WHERE (:fname is null or UPPER(e.firstName) = UPPER(:fname)) and (:mname is null or UPPER(e.middleName) = UPPER(:mname)) and (:lname is null or UPPER(e.lastName) = UPPER(:lname)) and (:position is null or e.position = :position) and (:date is null or a.date = :date)")
                .setParameter("fname", fname)
                .setParameter("mname", mname)
                .setParameter("lname", lname)
                .setParameter("position", position)
                .setParameter("date", date)
                .getSingleResult();
    }
}
