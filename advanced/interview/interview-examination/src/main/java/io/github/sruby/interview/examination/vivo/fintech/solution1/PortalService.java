package io.github.sruby.interview.examination.vivo.fintech.solution1;

import io.github.sruby.interview.examination.vivo.fintech.solution1.impl.APIFourImpl;
import io.github.sruby.interview.examination.vivo.fintech.solution1.impl.APIOneImpl;
import io.github.sruby.interview.examination.vivo.fintech.solution1.impl.APIThreeImpl;
import io.github.sruby.interview.examination.vivo.fintech.solution1.impl.APITwoImpl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 聚合调用
 *
 * @author Sruby
 * @date 7/22/2023 10:33 PM
 */
public class PortalService {
    public void invoke(){
        CountDownLatch countDownLatch = new CountDownLatch(4);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> submit = executorService.submit(new APIOneImpl());
        executorService.submit(new APITwoImpl());
        executorService.submit(new APIThreeImpl());
        executorService.submit(new APIFourImpl());

        countDownLatch.countDown();
    }
}
