package top.zhx47.jd.unidbg.sign.utils;

import top.zhx47.jd.unidbg.sign.config.Constant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.UUID;

public enum JDUtils {

    INSTANCE;

    public String urlEncode(String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, "UTF-8");
    }

    public String generateInstalltionId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String formatString(String format, Object... args) {
        return MessageFormat.format(format, args);
    }

    public String client() {
        return Constant.CLIENT;
    }

    public String clientVersion() {
        return Constant.CLIENT_VERSION;
    }
}
