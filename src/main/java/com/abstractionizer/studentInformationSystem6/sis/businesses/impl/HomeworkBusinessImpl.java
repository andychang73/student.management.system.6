package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.QuestionAndAnswer;
import com.abstractionizer.studentInformationSystem6.sis.businesses.HomeworkBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class HomeworkBusinessImpl implements HomeworkBusiness {

    private final HomeworkService homeworkService;
    private final ClassService classService;
    private final StudentClassService studentClassService;
    private final DateConfigService dateConfigService;
    private final SemesterService semesterService;
    private final SemesterClassService semesterClassService;

    @Override
    public void create(@NonNull final Integer staffId, @NonNull final CreateHomeworkBo bo) {
        if(!classService.isClassExists(bo.getClassId())){
            throw new CustomExceptions(ErrorCode.CLASS_NON_EXISTS);
        }
        if(!classService.isClassMine(bo.getClassId(),staffId)){
            throw new CustomExceptions(ErrorCode.CLASS_NOT_FOUND, "This class does not belong to this teacher");
        }

        Integer weekNo = semesterService.getWeekNumber(dateConfigService.getDate());

        SemesterClass semesterClass = studentClassService.getSemesterClass(bo.getClassId(), weekNo).orElseThrow(() -> new CustomExceptions(ErrorCode.CLASS_NON_EXISTS));

        if(!bo.getDeadLine().after(semesterClassService.getClassEndTime(semesterClass.getId()))){
            throw new CustomExceptions(ErrorCode.HOMEWORK_INVALID_DEADLINE);
        }
        if(homeworkService.isHomeworkExists(semesterClass.getId())){
            throw new CustomExceptions(ErrorCode.HOMEWORK_EXISTS);
        }

        Homework homework = new Homework()
                .setSemesterClassId(semesterClass.getId())
                .setHomework(JSONObject.parseObject(JSON.toJSONString(getQAndAMap(bo.getQuestionAndAnswers()))))
                .setDeadline(bo.getDeadLine());

        homeworkService.create(homework);
    }

    private synchronized Map<Integer, QuestionAndAnswer> getQAndAMap(List<QuestionAndAnswer> qAndAs){
        Map<Integer, QuestionAndAnswer> map = new HashMap<>();
        for(int i = 0; i < qAndAs.size(); i++){
            map.put(i+1, qAndAs.get(i));
        }
        return map;
    }
}
