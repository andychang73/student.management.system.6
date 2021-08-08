package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.semester.CreateSemesterBo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.SemesterBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.DateConfigService;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Slf4j
@Service
@AllArgsConstructor
public class SemesterBusinessImpl implements SemesterBusiness {

    private final SemesterService semesterService;
    private final DateConfigService dateConfigService;

    @Override
    public void create(@NonNull final String creator, @NonNull final CreateSemesterBo bo) {
        if(!semesterService.isSemesterEnded(dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.SEMESTER_NOT_ENDED);
        }

        Integer year = Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd").format(bo.getStartDate()).split("-")[0]);
        if(semesterService.isSemesterExists(year, bo.getSemester())){
            throw new CustomExceptions(ErrorCode.SEMESTER_EXISTS);
        }

        Semester semester = new Semester()
                .setYear(year)
                .setSemester(bo.getSemester())
                .setStartDate(bo.getStartDate())
                .setEndDate(semesterService.getEndOfSemester(bo.getStartDate(), bo.getNumOfWeeks() * 7))
                .setNumOfWeeks(bo.getNumOfWeeks())
                .setCreator(creator);

        semesterService.create(semester);
    }
}
