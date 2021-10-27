package cn.albertowang.datastructure.limiting.sentinel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author AlbertoWang@FoxMail.com
 * @date 2021/10/4 18:12
 * @description sentinel模拟器的invoker
 **/

@Data
@Builder
@AllArgsConstructor
public class SentinelInvoker {
    /**
     * 限流允许时间，单位秒
     */
    private int limitDuration;
    /**
     * 限流请求数量
     */
    private int requestLimitNum;
    /**
     * 限流器当前请求数
     */
    private int processingRequestNum;
    /**
     * 限流器当前时间
     */
    private long currentTime;

    /**
     * 处理请求
     *
     * @return 限流下invoke是否允许
     */
    public boolean invoke() {
        this.processingRequestNum++;
        // 判断是否超过限流时间
        // 限制时间以内，统计请求数量
        if (System.currentTimeMillis() / 1000 <= currentTime + limitDuration) {
            return this.processingRequestNum <= requestLimitNum;
        }
        // 超过限制时长，重新统计请求数量
        else {
            this.processingRequestNum = 0;
            // 时间重置为当前时间
            this.currentTime = System.currentTimeMillis() / 1000;
            return true;
        }
    }
}

