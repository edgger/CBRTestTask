package com.github.edgarzed.CBRTestTask.util;

import com.github.edgarzed.CBRTestTask.dto.AbsenceData;
import com.github.edgarzed.CBRTestTask.model.Absence;

import java.util.Collection;
import java.util.stream.Collectors;

public class AbsenceUtil {
    private AbsenceUtil() {
    }

    public static AbsenceData convertToDTO(Absence absence) {
        return new AbsenceData(absence.getId(),
                absence.getEmployee().getFullName(),
                absence.getEmployee().getPosition().getName(),
                absence.getDate(),
                absence.getTimeMinutes(),
                absence.getReason());
    }

    public static Collection<AbsenceData> convertToDTO(Collection<Absence> absences) {
        return absences.stream()
                .map(AbsenceUtil::convertToDTO)
                .collect(Collectors.toList());
    }
}
