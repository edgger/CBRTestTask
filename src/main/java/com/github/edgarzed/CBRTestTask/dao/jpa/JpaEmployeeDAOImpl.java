package com.github.edgarzed.CBRTestTask.dao.jpa;

import com.github.edgarzed.CBRTestTask.dao.EmployeeDAO;
import com.github.edgarzed.CBRTestTask.model.Employee;
import com.github.edgarzed.CBRTestTask.model.Position;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaEmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Employee get(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        if (employee.isNew()) {
            em.persist(employee);
            return employee;
        } else {
            return em.merge(employee);
        }
    }

    @Override
    public Collection<Employee> getFiltered(String fname, String mname, String lname, Position position) {
        return em.createQuery("SELECT e FROM Employee e LEFT JOIN FETCH e.position WHERE (:fname is null or LOWER(e.firstName) = :fname) and (:mname is null or LOWER(e.middleName) = :mname) and (:lname is null or LOWER(e.lastName) = :lname) and (:position is null or e.position = :position)", Employee.class)
                .setParameter("fname", fname)
                .setParameter("mname", mname)
                .setParameter("lname", lname)
                .setParameter("position", position)
                .getResultList();
    }

    @Override
    public Collection<Employee> getAll() {
        return em.createQuery("SELECT e FROM Employee e LEFT JOIN FETCH e.position", Employee.class).getResultList();
    }
}
