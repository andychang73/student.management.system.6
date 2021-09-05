package com.abstractionizer.studentInformationSystem6.sis.businesses.impl;

import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.Homework;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.SemesterClass;
import com.abstractionizer.studentInformationSystem6.db.rmdb.entities.StudentHomework;
import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.CreateHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.Question;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.QuestionAndAnswer;
import com.abstractionizer.studentInformationSystem6.models.bo.homework.SubmitHomeworkBo;
import com.abstractionizer.studentInformationSystem6.models.dto.homework.HomeworkDto;
import com.abstractionizer.studentInformationSystem6.models.vo.homework.HomeworkVo;
import com.abstractionizer.studentInformationSystem6.sis.businesses.HomeworkBusiness;
import com.abstractionizer.studentInformationSystem6.sis.services.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class HomeworkBusinessImpl implements HomeworkBusiness {

    private final StudentHomeworkService studentHomeworkService;
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

    @SneakyThrows
    @Override
    public BigDecimal submitHomework(@NonNull final Integer studentId, @NonNull final SubmitHomeworkBo bo) {
        if(!studentService.isStudentIdExists(studentId)){
            throw new CustomExceptions(ErrorCode.STUDENT_NON_EXISTS);
        }
        if(!semesterClassService.isSemesterClassExists(bo.getSemesterClassId())){
            throw new CustomExceptions(ErrorCode.SEMESTER_CLASS_NOT_FOUND);
        }

        Homework homework = homeworkService.getHomework(bo.getSemesterClassId()).orElseThrow(()-> new CustomExceptions(ErrorCode.HOMEWORK_NOT_FOUND));

        if(!dateConfigService.getDate().before(homework.getDeadline())){
            throw new CustomExceptions(ErrorCode.HOMEWORK_DEADLINE);
        }
        if(studentHomeworkService.hasHomeworkBeenSubmitted(homework.getId(), studentId)){
            throw new CustomExceptions(ErrorCode.HOMEWORK_GRADED);
        }

        Map<String, Character> answers = new ObjectMapper().readValue(homework.getAnswers(), new TypeReference<>(){});

        if(answers.size() != bo.getAnswers().size()){
            throw new CustomExceptions(ErrorCode.INVALID_SUBMITTED_HOMEWORK);
        }

        Map<String, Character> submittedAnswers = convertAnswersToMap(bo.getAnswers());
        BigDecimal grade = checkAnswersAndGetGrade(answers, submittedAnswers);

        StudentHomework studentHomework = new StudentHomework()
                .setHomeworkId(homework.getId())
                .setStudentId(studentId)
                .setAnswers(JSON.toJSONString(submittedAnswers))
                .setGrade(grade);

        studentHomeworkService.create(studentHomework);

        return grade;
    }

    private void insertQuestionAndAnswerMaps(List<QuestionAndAnswer> list, Map<String, Question> questions, Map<String, String> answers){
        for(int i = 0; i < list.size(); i++){
            questions.put(String.valueOf(i+1),getQuestion(list.get(i)));
            answers.put(String.valueOf(i+1), list.get(i).getAnswer());
        }
    }

    private Map<String, Character> convertAnswersToMap(List<Character> answers){
        Map<String, Character> answerMap = new HashMap<>();
        for(int i = 0; i < answers.size(); i++){
            answerMap.put(String.valueOf(i+1), answers.get(i));
        }
        return answerMap;
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

    private BigDecimal checkAnswersAndGetGrade(Map<String, Character> answers, Map<String, Character> submittedAnswers){
        BigDecimal count = BigDecimal.ZERO;
        for(Map.Entry<String, Character> submitted : submittedAnswers.entrySet()){
            if(answers.get(submitted.getKey()) == submitted.getValue()){
                count = count.add(BigDecimal.ONE);
            }
        }
        return count.divide(new BigDecimal(answers.size()), 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
    }
}
