package com.abstractionizer.studentInformationSystem6.sis.services.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Semester;
import com.abstractionizer.studentInformationSystem6.db.rmdb.mappers.SemesterMapper;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.semesterClass.ClassDateDto;
import com.abstractionizer.studentInformationSystem6.sis.services.SemesterService;
import com.abstractionizer.studentInformationSystem6.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {

    private final SemesterMapper semesterMapper;


    @Override
    public void create(@NonNull final Semester semester) {
        if(semesterMapper.insert(semester) != 1){
            log.error("Failed to create semester: {}", semester);
            throw new CustomExceptions(ErrorCode.DATA_INSERT_FAILED);
        }
    }

    @Override
    public boolean isSemesterExists(@NonNull final Integer year, @NonNull final Integer semester) {
        return semesterMapper.countByYearAndSemester(year, semester) > 0;
    }

    @Override
    public boolean isSemesterEnded(@NonNull final Date now) {
        return now.after(semesterMapper.selectCurrentSemester().getEndDate());
    }

    @Override
    public Semester getCurrentSemester() {
        return semesterMapper.selectCurrentSemester();
    }

    @Override
    public Date getEndOfSemester(@NonNull final Date date, @NonNull final Integer numOfDay) {
        return DateUtil.getFridayOfParticularWeek(date, numOfDay);
    }

    @Override
    public List<ClassDateDto> getSemesterClassDates(@NonNull final Date startDate, @NonNull final Integer weekDay, @NonNull final Integer numOfWeeks) {
        List<ClassDateDto> classDates = new ArrayList<>();
        Date date = null;

        for(int i = 0; i < numOfWeeks; i++){
            if(classDates.isEmpty()){
                date = DateUtil.getDateByWeekDay(startDate, weekDay);
            }else{
                date = DateUtil.addDaysToDate(date, 7);
            }
            classDates.add(new ClassDateDto(i + 1, date));
        }
        return classDates;
    }

    @Override
    public Integer getWeekNumber(@NonNull final Date today) {
        Date startDate = this.getCurrentSemester().getStartDate();
        Date endOfThisWeek = DateUtil.getFridayOfParticularWeek(today,null);

        int weekNo = 0;
        do{
            startDate = DateUtil.adjustDate(startDate, 7);
            weekNo++;
        }while(startDate.before(endOfThisWeek));

        return weekNo;
    }
}
