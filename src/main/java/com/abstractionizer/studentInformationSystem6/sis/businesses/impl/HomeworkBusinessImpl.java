package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.Question;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.QuestionAndAnswer;
import com.abstractionizer.studentInformationSystem6.models.dto.homework.HomeworkDto;
import com.abstractionizer.studentInformationSystem6.models.vo.homework.HomeworkVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.HomeworkBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class HomeworkBusinessImpl implements HomeworkBusiness {

    private final HomeworkService homeworkService;
    private final ClassService classService;
    private final StudentService studentService;
    private final StudentClassService studentClassService;
    private final DateConfigService dateConfigService;
    private final SemesterService semesterService;
    private final SemesterClassService semesterClassService;

    @Override
    public void create(@NonNull final Integer staffId, @NonNull final CreateHomeworkBo bo) {
        if(!classService.isClassValid(bo.getClassId(),staffId, dateConfigService.getDate())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }

        Integer weekNo = semesterService.getWeekNumber(dateConfigService.getDate());

        SemesterClass semesterClass = studentClassService.getSemesterClass(bo.getClassId(), weekNo).orElseThrow(() -> new CustomExceptions(ErrorCode.CLASS_NON_EXISTS));

        if(!bo.getDeadLine().after(semesterClassService.getClassEndTime(semesterClass.getId()))){
            throw new CustomExceptions(ErrorCode.HOMEWORK_INVALID_DEADLINE);
        }
        if(homeworkService.isHomeworkExists(semesterClass.getId())){
            throw new CustomExceptions(ErrorCode.HOMEWORK_EXISTS);
        }

        Map<String, Question> questions = new HashMap<>();
        Map<String, String> answers = new HashMap<>();
        insertQuestionAndAnswerMaps(bo.getQuestionAndAnswers(), questions, answers);

        Homework homework = new Homework()
                .setSemesterClassId(semesterClass.getId())
                .setQuestions(JSON.toJSONString(questions))
                .setAnswers(JSON.toJSONString(answers))
                .setDeadline(bo.getDeadLine());

        homeworkService.create(homework);
    }

    @SneakyThrows
    @Override
    public List<HomeworkVo> getValidHomeworks(@NonNull final Integer studentId, @NonNull final Integer classId) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        if(!classService.isClassValid(classId, semesterService.getCurrentSemester().getId())){
            throw new CustomExceptions(ErrorCode.CLASS_INVALID);
        }
        if(!studentClassService.isStudentInTheClass(classId, studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NOT_IN_CLASS);
        }

        return convertToHomeworkVo(homeworkService.getValidHomeworks(classId, dateConfigService.getDate()));
    }

    private void insertQuestionAndAnswerMaps(List<QuestionAndAnswer> list, Map<String, Question> questions, Map<String, String> answers){
        for(int i = 0; i < list.size(); i++){
            questions.put(String.valueOf(i+1),getQuestion(list.get(i)));
            answers.put(String.valueOf(i+1), list.get(i).getAnswer());
        }
    }

    private Question getQuestion(QuestionAndAnswer qAndA){
        return new Question()
                .setQuestion(qAndA.getQuestion())
                .setA(qAndA.getA())
                .setB(qAndA.getB())
                .setC(qAndA.getC())
                .setD(qAndA.getD());
    }

    private List<HomeworkVo> convertToHomeworkVo(List<HomeworkDto> list){
        return list.stream()
                .map(l -> new HomeworkVo()
                        .setSemesterClassId(l.getSemesterClassId())
                        .setQuestions(JSONObject.parseObject(l.getQuestions())))
                .collect(Collectors.toList());
    }
}
