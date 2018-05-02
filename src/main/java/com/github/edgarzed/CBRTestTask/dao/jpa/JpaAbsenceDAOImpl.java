package com.github.edgarzed.CBRTestTask.dao.jpa;

import com.github.edgarzed.CBRTestTask.dao.AbsenceDAO;
import com.github.edgarzed.CBRTestTask.model.Absence;
import com.github.edgarzed.CBRTestTask.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        return em.createQuery("SELECT a FROM Absence a INNER JOIN FETCH a.employee e INNER JOIN FETCH e.position ORDER BY a.date DESC", Absence.class)
                .getResultList();
    }

    @Override
    public Collection<Absence> getPage(int start, int count) {
        return em.createQuery("SELECT a FROM Absence a INNER JOIN FETCH a.employee e INNER JOIN FETCH e.position ORDER BY a.date DESC", Absence.class)
                .setMaxResults(count)
                .setFirstResult(start)
                .getResultList();
    }

    @Override
    public Collection<Absence> getPageFiltered(int start, int count, Employee employee, LocalDate date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Absence> absenceQuery = cb.createQuery(Absence.class);
        Root<Absence> root = absenceQuery.from(Absence.class);
        root.fetch("employee").fetch("position");
        List<Predicate> predicates = getPredicates(employee, date, cb, root);

        absenceQuery.select(root);
        absenceQuery.where(predicates.toArray(new Predicate[0]));
        absenceQuery.orderBy(cb.desc(root.get("date")));
        return em.createQuery(absenceQuery).setFirstResult(start).setMaxResults(count).getResultList();
    }

    @Override
    public long getCount() {
        return (long) em.createQuery("SELECT COUNT (a.id) FROM Absence a").getSingleResult();
    }

    @Override
    public long getCountFiltered(Employee employee, LocalDate date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Absence> root = countQuery.from(Absence.class);
        List<Predicate> predicates = getPredicates(employee, date, cb, root);

        countQuery.select(cb.count(root));
        countQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(countQuery).getSingleResult();
    }

    private static List<Predicate> getPredicates(Employee empl, LocalDate date, CriteriaBuilder cb, Root<Absence> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (empl!=null){
            if (empl.getFirstName() != null) {
                predicates.add(cb.equal(cb.lower(root.get("employee").get("firstName")), empl.getFirstName()));
            }
            if (empl.getMiddleName() != null) {
                predicates.add(cb.equal(cb.lower(root.get("employee").get("middleName")), empl.getMiddleName()));
            }
            if (empl.getLastName() != null) {
                predicates.add(cb.equal(cb.lower(root.get("employee").get("lastName")), empl.getLastName()));
            }
            if (empl.getPosition() != null) {
                predicates.add(cb.equal(root.get("employee").get("position"), empl.getPosition()));
            }
        }
        if (date != null) {
            predicates.add(cb.equal(root.get("date"), date));
        }
        return predicates;
    }
}
