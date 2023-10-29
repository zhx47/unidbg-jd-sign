package top.zhx47.jd.unidbg.sign.config.annotation;

import top.zhx47.jd.unidbg.sign.config.validator.JsonStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = JsonStringValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonString {
    String message() default "Invalid JSON string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
