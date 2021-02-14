package cn.albertowang.concurrent.plain;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午4:31
 * @description 不包含任何同步方法的线程不安全方式
 **/

/*
输出：
flag = true
（后面死循环无输出）
 */

public class NonSyncThread extends Thread {
    private boolean flag = false;  // 共享变量

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;  // 子线程对共享变量的修改
        System.out.println("flag = " + flag);
    }

    public boolean isFlag() {
        return flag;
    }


    public static void main(String[] args){  // main函数为主线程
        NonSyncThread thread = new NonSyncThread();
        thread.start(); // 子线程开始执行：thread.flag在1秒后变为true
        while (true) {  // 主线程执行
            // 这里的判断仍是主线程判断，flag是共享变量，此时还没更新到主内存
            if (thread.isFlag()) {
                System.out.println("主线程访问到 flag 变量：" + thread.flag);
            }
        }
    }
}
