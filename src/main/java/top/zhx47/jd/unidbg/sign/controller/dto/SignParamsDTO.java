package top.zhx47.jd.unidbg.sign.controller.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import top.zhx47.jd.unidbg.sign.config.Constant;
import top.zhx47.jd.unidbg.sign.config.annotation.JsonString;
import top.zhx47.jd.unidbg.sign.utils.JDUtils;

import javax.validation.constraints.NotEmpty;

@Data
@Slf4j
public class SignParamsDTO {

    @NotEmpty(message = "body不能为空!")
    @JsonString(message = "body参数格式不合法，传递字符串的时候需要时需要满足JSON字符串格式！")
    private String body;

    @JSONField(name = "fn")
    @NotEmpty(message = "fn不能为空!")
    private String functionId;

    private String client;

    private String clientVersion;

    private String uuid;

    private Boolean ep;

    public void generateValue() {
        if (StringUtils.isBlank(client)) {
            this.client = JDUtils.INSTANCE.client();
            log.info("获取默认client: {}", Constant.CLIENT);
        }

        if (StringUtils.isBlank(clientVersion)) {
            this.clientVersion = JDUtils.INSTANCE.clientVersion();
            log.info("获取默认clientVersion: {}", Constant.CLIENT_VERSION);
        }

        if (StringUtils.isBlank(uuid)) {
            this.uuid = JDUtils.INSTANCE.generateInstalltionId();
            log.info("生成uuid: {}", uuid);
        }

        if (ep == null) {
            this.ep = false;
            log.info("默认不生成EP");
        }
    }
}
