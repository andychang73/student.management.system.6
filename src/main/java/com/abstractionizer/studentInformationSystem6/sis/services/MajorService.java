package com.abstractionizer.studentInformationSystem6.sis.services;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Major;

import java.util.Set;

public interface MajorService {

    Major create(Major major);

    boolean isMajorExists(String major);

    boolean areMajorIdsExist(Set<Integer> majorIds);
}
