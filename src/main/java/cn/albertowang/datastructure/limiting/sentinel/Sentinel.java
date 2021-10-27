package cn.albertowang.datastructure.limiting.sentinel;

import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author AlbertoWang@FoxMail.com
 * @date 2021/10/4 18:13
 * @description 设计一个限流器，在一定时间范围内，服务接口的调用次数有所限制，超过次数则抛出异常，超过时间后接口调用次数限制清空
 **/

public class Sentinel {


    /**
     * 限流器服务的monitor，key为服务名称，val为限流器invoker
     */
    private static final Map<String, SentinelInvoker> SERVICES_MAP = new ConcurrentHashMap<>();

    /**
     * 创建新的接口限流器
     *
     * @param serviceName 服务名称
     * @param duration    限制时长
     * @param requestNum  限制数量
     */
    public static SentinelInvoker setInvokerInstance(String serviceName, int duration, int requestNum) {
        if (SERVICES_MAP.containsKey(serviceName)) {
            return SERVICES_MAP.get(serviceName);
        }
        SERVICES_MAP.put(serviceName, SentinelInvoker.builder()
                .limitDuration(duration)
                .requestLimitNum(requestNum)
                .currentTime(System.currentTimeMillis() / 1000)
                .processingRequestNum(0)
                .build());
        return SERVICES_MAP.get(serviceName);
    }

    public static void invokeRequest(String serviceName) {
        SentinelInvoker invoker =
                SERVICES_MAP.getOrDefault(serviceName, setInvokerInstance(serviceName, 1, 5));
        if (invoker.invoke()) {
            System.out.println(serviceName + " request success");
            return;
        }
        throw new RuntimeException(MessageFormat.format("限流器超过请求限制，服务【{0}】当前请求数量【{1}】，限制数量为【{2}】",
                serviceName,
                invoker.getProcessingRequestNum(),
                invoker.getRequestLimitNum())
        );
    }

    public static void main(String[] args) {
        // 使用线程池模拟请求
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 提交任务
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    invokeRequest("service-name");
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
            }
        });
        executorService.shutdown();
    }
}
