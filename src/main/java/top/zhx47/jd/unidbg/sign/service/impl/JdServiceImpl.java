package top.zhx47.jd.unidbg.sign.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.unidbg.worker.WorkerPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.zhx47.jd.unidbg.sign.config.Constant;
import top.zhx47.jd.unidbg.sign.controller.dto.SignParamsDTO;
import top.zhx47.jd.unidbg.sign.logic.Ep;
import top.zhx47.jd.unidbg.sign.logic.JDSignEncryptWorker;
import top.zhx47.jd.unidbg.sign.service.JdService;
import top.zhx47.jd.unidbg.sign.utils.JDUtils;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdServiceImpl implements JdService {

    private final ExecutorService executorService;
    private final WorkerPool workerPool;

    @Override
    @SuppressWarnings("BusyWait")
    public String getSign(SignParamsDTO signParams) {
        AtomicReference<String> sign = new AtomicReference<>("");
        executorService.submit(() -> {
            try (JDSignEncryptWorker worker = workerPool.borrow(1, TimeUnit.MINUTES)) {
                sign.set(worker.doWork(signParams));
            } catch (Exception e) {
                log.error("获取Sign出错：", e);
            }
        });

        int count = 0;
        while (StringUtils.isBlank(sign.get())) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error("获取sign异常", e);
            }
            count++;
            if (count > 200) {
                throw new RuntimeException("获取sign超时");
            }
        }
        log.info("获取到Sign：{}", sign.get());
        return sign.get();
    }

    @Override
    public Ep getEp(String uuid) {
        // TODO 目前都是固定值，只有三个ID是生成的，后面再说
        Ep ep = new Ep(uuid);
        log.info("获取EP参数：{}", ep);
        return ep;
    }

    @Override
    public String getParams(SignParamsDTO signParams, String sign, boolean hasFunctionId) throws UnsupportedEncodingException {
        String params;
        if (hasFunctionId && signParams.getEp()) {
            Ep ep = this.getEp(signParams.getUuid());
            params = JDUtils.INSTANCE.formatString(Constant.SIGN_FUNCTION_EP_FORMATTER, signParams.getFunctionId(), JDUtils.INSTANCE.urlEncode(signParams.getBody()), JDUtils.INSTANCE.urlEncode(JSONObject.toJSONString(ep)), signParams.getClient(), signParams.getClientVersion(), signParams.getUuid(), sign);
        } else if (hasFunctionId) {
            params = JDUtils.INSTANCE.formatString(Constant.SIGN_FUNCTION_FORMATTER, signParams.getFunctionId(), JDUtils.INSTANCE.urlEncode(signParams.getBody()), signParams.getClient(), signParams.getClientVersion(), signParams.getUuid(), sign);
        } else if (signParams.getEp()) {
            Ep ep = this.getEp(signParams.getUuid());
            params = JDUtils.INSTANCE.formatString(Constant.SIGN_EP_FORMATTER, JDUtils.INSTANCE.urlEncode(signParams.getBody()), JDUtils.INSTANCE.urlEncode(JSONObject.toJSONString(ep)), signParams.getClient(), signParams.getClientVersion(), signParams.getUuid(), sign);
        } else {
            params = JDUtils.INSTANCE.formatString(Constant.SIGN_FORMATTER, JDUtils.INSTANCE.urlEncode(signParams.getBody()), signParams.getClient(), signParams.getClientVersion(), signParams.getUuid(), sign);
        }
        log.info("组装参数：{}", params);
        return params;
    }
}
