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
        return em.createQuery("SELECT a FROM Absence a LEFT JOIN FETCH a.employee e LEFT JOIN FETCH e.position p WHERE (:fname is null or LOWER(e.firstName) = :fname) and (:mname is null or LOWER(e.middleName) = :mname) and (:lname is null or LOWER(e.lastName) = :lname) and (:position is null or e.position = :position) and (a.date = :date or :date is null) ORDER BY a.date DESC", Absence.class)
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
        //https://stackoverflow.com/questions/44905452/postgresql-with-hibernate-could-not-determine-localdate-type-in-jpql-query
        return (long) em.createQuery("SELECT COUNT (a.id) FROM Absence a LEFT JOIN a.employee e LEFT JOIN e.position p  WHERE (:fname is null or LOWER(e.firstName) = :fname) and (:mname is null or LOWER(e.middleName) = :mname) and (:lname is null or LOWER(e.lastName) = :lname) and (:position is null or e.position = :position) and (a.date = :date or :date is null)")
                .setParameter("fname", fname)
                .setParameter("mname", mname)
                .setParameter("lname", lname)
                .setParameter("position", position)
                .setParameter("date", date)
                .getSingleResult();
    }
}
