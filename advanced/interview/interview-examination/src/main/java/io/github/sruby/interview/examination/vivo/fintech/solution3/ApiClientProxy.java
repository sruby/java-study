package io.github.sruby.interview.examination.vivo.fintech.solution3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * api client proxy
 *
 * @author Sruby
 * @date 7/23/2023 10:08 AM
 */
public class ApiClientProxy implements InvocationHandler {
    private final ApiClient apiClient;

    public ApiClientProxy(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String simpleName = apiClient.getClass().getSimpleName();
        preProcess(simpleName);

        String result = (String) method.invoke(apiClient, args);

        postProcess(simpleName);
        return result;
    }

    private static void postProcess(String result) {
        System.out.println("Postprocessing for " + result);
        // 实际的后处理逻辑
    }

    private static void preProcess(String apiName) {
        System.out.println("Preprocessing for " + apiName);
        // 实际的预处理逻辑
    }
}
