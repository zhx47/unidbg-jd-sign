package top.zhx47.jd.unidbg.sign.config;

import com.github.unidbg.worker.WorkerPool;
import com.github.unidbg.worker.WorkerPoolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zhx47.jd.unidbg.sign.logic.JDSignEncryptWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class JDConfiguration {

    private static final int threads = 10;

    @Bean("workerPool")
    public WorkerPool workerPool() {
        return WorkerPoolFactory.create(JDSignEncryptWorker::new, Runtime.getRuntime().availableProcessors());
    }

    @Bean("executorService")
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(threads);
    }

}
