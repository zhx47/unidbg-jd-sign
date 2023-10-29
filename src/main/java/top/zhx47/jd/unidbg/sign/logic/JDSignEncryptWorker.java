package top.zhx47.jd.unidbg.sign.logic;

import com.github.unidbg.worker.Worker;
import com.github.unidbg.worker.WorkerPool;
import lombok.extern.slf4j.Slf4j;
import top.zhx47.jd.unidbg.sign.controller.dto.SignParamsDTO;

@Slf4j
public class JDSignEncryptWorker extends Worker {

    private final JDSignEncrypt jdSignEncrypt;

    public JDSignEncryptWorker(WorkerPool pool) {
        super(pool);

        jdSignEncrypt = new JDSignEncrypt(false);
        log.info("Create: {}", jdSignEncrypt);
    }

    @Override
    public void destroy() {
        jdSignEncrypt.destroy();
        log.info("Destroy: {}", jdSignEncrypt);
    }

    public String doWork(SignParamsDTO signParams) {
        return jdSignEncrypt.getSignFromJni(signParams.getFunctionId(), signParams.getBody(), signParams.getUuid(), signParams.getClient(), signParams.getClientVersion());
    }
}
