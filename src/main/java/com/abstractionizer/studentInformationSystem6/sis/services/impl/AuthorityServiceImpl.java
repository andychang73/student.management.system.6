package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.AuthorityMapper;
import com.abstractionizer.studentInformationSystem6.sis.services.AuthorityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityMapper authorityMapper;

    @Override
    public boolean areAuthorityExist(Set<Integer> ids) {
        return authorityMapper.countByAuthorityIds(ids).equals(ids.size());
    }
}
