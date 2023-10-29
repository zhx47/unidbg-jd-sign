package top.zhx47.jd.unidbg.sign.config;

public interface Constant {
    String TRACE_ID = "TRACE_ID";

    String CLIENT = "android";
    String CLIENT_VERSION = "12.2.2";
    String SIGN_EP_FORMATTER = "body={0}&ef=1&ep={1}&client={2}&clientVersion={3}&uuid={4}&{5}";
    String SIGN_FORMATTER = "body={0}&client={1}&clientVersion={2}&uuid={3}&{4}";
    String SIGN_FUNCTION_EP_FORMATTER = "functionId={0}&body={1}&ef=1&ep={2}&client={3}&clientVersion={4}&uuid={5}&{6}";
    String SIGN_FUNCTION_FORMATTER = "functionId={0}&body={1}&client={2}&clientVersion={3}&uuid={4}&{5}";
}
