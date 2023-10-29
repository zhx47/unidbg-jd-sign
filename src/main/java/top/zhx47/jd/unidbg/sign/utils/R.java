package top.zhx47.jd.unidbg.sign.utils;

import org.slf4j.MDC;
import top.zhx47.jd.unidbg.sign.config.Constant;

import java.util.HashMap;

public class R extends HashMap<String, Object> {

    public R() {
        put("code", 0);
        put("msg", "success");
        put("traceId", MDC.get(Constant.TRACE_ID));
    }

    public static R success() {
        return new R();
    }

    public static R fail(Object msg) {
        return fail(-1, msg);
    }

    public static R fail(int code, Object msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
