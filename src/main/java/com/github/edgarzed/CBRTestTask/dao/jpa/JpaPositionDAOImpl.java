package com.github.edgarzed.CBRTestTask.dao.jpa;

import com.github.edgarzed.CBRTestTask.dao.PositionDAO;
import com.github.edgarzed.CBRTestTask.model.Position;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
@Transactional(readOnly = true)
public class JpaPositionDAOImpl implements PositionDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Position get(int id) {
        return em.find(Position.class, id);
    }

    @Override
    public Collection<Position> getAll() {
        return em.createQuery("SELECT p FROM Position p ORDER BY p.name", Position.class).getResultList();
    }
}
