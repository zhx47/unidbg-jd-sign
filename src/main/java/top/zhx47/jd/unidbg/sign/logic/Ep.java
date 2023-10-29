package top.zhx47.jd.unidbg.sign.logic;

import com.jd.phc.d;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Ep {
    private String hdid;
    private Long ts;
    private Integer ridx;
    private CipherDTO cipher = new CipherDTO();
    private Integer ciphertype;
    private String version;
    private String appname;

    public Ep(String uuid) {
        this.hdid = "JM9F1ywUPwflvMIpYPok0tt5k9kW4ArJEU3lfLhxBqw=";
        this.ts = System.currentTimeMillis();
        this.ridx = 1;
        this.cipher.dModel = "UwVubWvBCtLGcw8=";
        this.cipher.wifiBssid = "dW5hbw93bq==";
        this.cipher.osVersion = "CJO=";
        this.cipher.dBrand = "WQvrb21f";
        this.cipher.screen = "CtS5DyenCNqm";
        String id = d.b(uuid.getBytes());
        this.cipher.uuid = id;
        this.cipher.aid = id;
        this.cipher.openudid = id;
        this.ciphertype = 5;
        this.version = "1.2.0";
        this.appname = "com.jingdong.app.mall";
    }

    @NoArgsConstructor
    @Data
    public static class CipherDTO {
        private String dModel;
        private String wifiBssid;
        private String osVersion;
        private String dBrand;
        private String screen;
        private String uuid;
        private String aid;
        private String openudid;
    }
}
