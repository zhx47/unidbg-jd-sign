package top.zhx47.jd.unidbg.sign.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.zhx47.jd.unidbg.sign.controller.dto.SignParamsDTO;
import top.zhx47.jd.unidbg.sign.service.JdService;
import top.zhx47.jd.unidbg.sign.utils.R;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class JdController {

    private final JdService jdService;

    @SuppressWarnings("all")
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public R getSign(@Valid @RequestBody SignParamsDTO signParams) throws Exception {
        log.info("开始获取Sign...");
        signParams.generateValue();
        String sign = jdService.getSign(signParams);
        String body = jdService.getParams(signParams, sign, false);
        String params = jdService.getParams(signParams, sign, true);
        log.info("获取Sign结束...");
        return R.success().put("fn", signParams.getFunctionId())
                .put("sign", sign)
                .put("body", body)
                .put("params", params);
    }
}
