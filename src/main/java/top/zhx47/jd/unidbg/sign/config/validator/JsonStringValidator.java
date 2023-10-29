package top.zhx47.jd.unidbg.sign.config.validator;

import com.alibaba.fastjson.JSONObject;
import top.zhx47.jd.unidbg.sign.config.annotation.JsonString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsonStringValidator implements ConstraintValidator<JsonString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            JSONObject.parseObject(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
