package com.github.edgarzed.CBRTestTask.dao;

import com.github.edgarzed.CBRTestTask.model.Position;

import java.util.Collection;

public interface PositionDAO {
    Position get(int id);
    Collection<Position> getAll();
}
