package com.abstractionizer.studentInformationSystem6.annotations;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidPassword.PasswordValidator.class)
public @interface ValidPassword {

    String message() default "{javax.validation.constraints.Pattern.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String oldPassword();
    String newPassword();
    String confirmPassword();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List{
        ValidPassword[] value();
    }

    class PasswordValidator implements ConstraintValidator<ValidPassword, Object>{
        @SneakyThrows
        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            final String oldPassword = BeanUtils.getProperty(o, "oldPassword");
            final String newPassword = BeanUtils.getProperty(o, "newPassword");
            final String confirmPassword = BeanUtils.getProperty(o, "confirmPassword");
            return !oldPassword.equals(newPassword) && newPassword.equals(confirmPassword);
        }
    }
}
