package com.abstractionizer.studentInformationSystem6.sis.businesses;

import com.abstractionizer.studentInformationSystem6.models.bo.major.CreateMajorBo;
import com.abstractionizer.studentInformationSystem6.models.vo.major.MajorVo;

import java.util.List;

public interface MajorBusiness {

    void create(String creator, CreateMajorBo bo);

    List<MajorVo> getAllMajors();
}
