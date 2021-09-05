package com.abstractionizer.studentInformationSystem6.annotations;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.List;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidAnswers.ValidAnswersValidator.class)
public @interface ValidAnswers {
    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

    class ValidAnswersValidator implements ConstraintValidator<ValidAnswers, List<Character>>{

        @Override
        public boolean isValid(List<Character> characters, ConstraintValidatorContext constraintValidatorContext) {
            for(Character c: characters){
                if(c != 'a'){
                    if(c != 'b'){
                        if(c != 'c'){
                            if(c != 'd'){
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

}
