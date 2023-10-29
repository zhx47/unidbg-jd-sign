package top.zhx47.jd.unidbg.sign.logic;

import com.alibaba.fastjson.util.IOUtils;
import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class JDSignEncrypt extends AbstractJni {
    private static final String pkgName = "com.jingdong.app.mall";
    private final AndroidEmulator emulator;
    private final VM vm;
    private final DvmClass BitmapkitUtils;
    private final boolean logging;


    JDSignEncrypt(boolean logging) {
        this.logging = logging;

        emulator = AndroidEmulatorBuilder.for64Bit().setProcessName(pkgName).build();

        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析

        vm = emulator.createDalvikVM(new File("/app/data/jd.apk")); // 创建Android虚拟机
        vm.setJni(new JDJni());
        vm.setVerbose(logging); // 设置是否打印Jni调用细节

        DalvikModule dm = vm.loadLibrary(new File("/app/data/libjdbitmapkit.so"), false); // 加载libttEncrypt.so到unicorn虚拟内存，加载成功以后会默认调用init_array等函数
        dm.callJNI_OnLoad(emulator); // 手动执行JNI_OnLoad函数

        BitmapkitUtils = vm.resolveClass("com/jingdong/common/utils/BitmapkitUtils");
    }

    void destroy() {
        IOUtils.close(emulator);
        if (logging) {
            log.info("destroy");
        }
    }

    public String getSignFromJni(String functionId, String body, String uuid, String client, String clientVersion) {
        StringObject ret = BitmapkitUtils.callStaticJniMethodObject(emulator, "getSignFromJni()(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
                vm.resolveClass("android/content/Context").newObject(null),
                functionId,
                body,
                uuid,
                client,
                clientVersion);
        return ret.getValue();
    }
}
