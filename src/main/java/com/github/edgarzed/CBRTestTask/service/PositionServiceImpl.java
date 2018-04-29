package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.dao.PositionDAO;
import com.github.edgarzed.CBRTestTask.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionDAO positionDAO;

    @Autowired
    public PositionServiceImpl(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    @Override
    public Position get(int id) {
        return positionDAO.get(id);
    }

    @Override
    public Collection<Position> getAll() {
        return positionDAO.getAll();
    }
}
