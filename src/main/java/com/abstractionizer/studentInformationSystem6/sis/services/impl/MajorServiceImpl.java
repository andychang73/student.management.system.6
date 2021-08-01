package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Major;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.MajorMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.sis.services.MajorService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class MajorServiceImpl implements MajorService {

    private final MajorMapper majorMapper;

    @Override
    public Major create(Major major) {
        if(majorMapper.insert(major) != 1){
            log.error("Create major failed: {}", major);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
        return major;
    }

    @Override
    public boolean isMajorExists(@NonNull final String major) {
        return majorMapper.countByMajorIdOrMajor(null, major) > 0;
    }

    @Override
    public boolean areMajorIdsExist(@NonNull final Set<Integer> majorIds) {
        if(majorIds.isEmpty()){
            return false;
        }
        return majorMapper.countByMajorIdOrMajor(majorIds, null) == majorIds.size();
    }
}
