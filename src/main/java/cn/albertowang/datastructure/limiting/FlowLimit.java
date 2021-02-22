package cn.albertowang.datastructure.limiting;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/22 22:06
 * @description 有一个API网关，出于对API接口的保护，需要建立一个流控功能，
 * 根据API名称，每分钟最多只能请求指定的次数（如5次），超过限制则这分钟内返回错误，但下一分钟又可以正常请求
 **/

public class FlowLimit {
    // 请求调用器
    public static class Invoker {
        private long time; // 等待时间
        private int invokeNum; // 请求数量

        public Invoker(long time, int invokeNum) {
            this.time = time;
            this.invokeNum = invokeNum;
        }

        // 处理请求
        public boolean invoke() {
            this.invokeNum++;
            // 判断是否超过一分钟
            if (System.currentTimeMillis() / 1000 <= time) {
                // 一分钟以内，统计请求数量
                System.out.print(System.currentTimeMillis() / 1000 + " --- ");
                if (this.invokeNum <= 5) {
                    System.out.println("Response Request");
                    return true;
                } else {
                    System.out.println("Request Denied");
                    return false;
                }
            } else {
                // 超过一分钟，重新统计请求数量
                this.invokeNum = 0;
                // 时间重置为当前时间
                this.time = System.currentTimeMillis() / 1000;
                return true;
            }
        }
    }

    // api服务列表，被static修饰，结构为api-name:请求调用器
    private static final Map<String, Invoker> services = new HashMap<>();

    // 限流器处理请求
    public static boolean invoke(String apiName) {
        if (apiName == null)
            return false;
        // 锁住api服务列表，防止多线程出错
        synchronized (services) {
            // 获取请求调用器
            Invoker invoker = services.get(apiName);
            // 新请求，创建调用器并调用
            if (invoker == null) {
                invoker = new Invoker(System.currentTimeMillis() / 1000, 1);
                services.put(apiName, invoker);
                return true;
            }
            // 已存在请求，使用调用器进行调用
            return invoker.invoke();
        }
    }

    public static void main(String[] args) {
        // 使用线程池模拟请求
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 提交任务
            executorService.submit(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    FlowLimit.invoke("name");
                }
            });
        }
        executorService.shutdown();
    }
}
