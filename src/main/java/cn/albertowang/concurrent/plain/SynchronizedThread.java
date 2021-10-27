package cn.albertowang.concurrent.plain;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午4:34
 * @description 使用synchronized关键字的同步方法
 **/

/*
输出：
flag = true
主线程访问到 flag 变量：true
主线程访问到 flag 变量：true
主线程访问到 flag 变量：true
（后面重复输出）
 */

public class SynchronizedThread extends Thread {
    // 共享变量
    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 子线程对共享变量的修改
        flag = true;
        System.out.println("flag = " + flag);
    }

    public boolean isFlag() {
        return flag;
    }


    public static void main(String[] args) {  // main函数为主线程
        SynchronizedThread thread = new SynchronizedThread();
        // 子线程开始执行：thread.flag在1秒后变为true
        thread.start();
        // 主线程执行
        while (true) {
            // 这里的判断仍是主线程判断，flag是共享变量，由于加了锁，主线程可以检测到子线程对共享变量的修改
            synchronized (thread) {
                if (thread.isFlag()) {
                    System.out.println("主线程访问到 flag 变量：" + thread.flag);
                }
            }
        }
    }
}
