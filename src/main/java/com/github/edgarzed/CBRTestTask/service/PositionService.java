package com.github.edgarzed.CBRTestTask.service;

import com.github.edgarzed.CBRTestTask.model.Position;

import java.util.Collection;

public interface PositionService {
    Position get(int id);

    Collection<Position> getAll();
}
