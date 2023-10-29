package top.zhx47.jd.unidbg.sign.service;

import top.zhx47.jd.unidbg.sign.controller.dto.SignParamsDTO;
import top.zhx47.jd.unidbg.sign.logic.Ep;

import java.io.UnsupportedEncodingException;

public interface JdService {

    String getSign(SignParamsDTO signParams);

    Ep getEp(String uuid);

    String getParams(SignParamsDTO signParams, String sign, boolean hasFunctionId) throws UnsupportedEncodingException;
}
