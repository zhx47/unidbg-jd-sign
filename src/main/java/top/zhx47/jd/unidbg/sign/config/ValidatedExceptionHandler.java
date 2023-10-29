package top.zhx47.jd.unidbg.sign.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.zhx47.jd.unidbg.sign.utils.R;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RestControllerAdvice
public class ValidatedExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public R bindExceptionHandler(BindException e) {
        log.error("methodArgumentNotValidExceptionHandler", e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        List<String> defaultMessages = new ArrayList<>(fieldErrors.size());
        for (FieldError fieldError : fieldErrors) {
            defaultMessages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        return R.fail(defaultMessages);
    }

    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        log.error("exceptionHandler", e);
        return R.fail(e.getMessage());
    }
}
