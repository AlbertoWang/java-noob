package cn.albertowang.concurrent.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/4 23:13
 * @description 实现线程池
 **/

public class MyThreadPool {

    // 工作线程
    class MyThread extends Thread {
        private Runnable task;

        public MyThread(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (true) {
                if (task != null) { // 有任务来临时，执行任务
                    task.run();
                    task = null;
                } else { // 无任务，从任务队列取出任务
                    Runnable queueTask = blockingQueue.poll();
                    if (queueTask != null)
                        queueTask.run();
                    else { // 无队列任务，休眠
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private final int maximumPoolSize;
    private int corePoolSize;
    private ArrayList<MyThread> corePool;
    private ArrayBlockingQueue<Runnable> blockingQueue;
    private final ReentrantLock lock = new ReentrantLock();

    public MyThreadPool(int maxPoolSize, int queueSize) {
        maximumPoolSize = maxPoolSize;
        corePool = new ArrayList<>(maxPoolSize);
        blockingQueue = new ArrayBlockingQueue<>(queueSize);
        corePoolSize = 0;
    }

    public void execute(Runnable task) {
        try {
            lock.lock();
            // 核心线程数量小于最大线程池大小
            if (corePoolSize < maximumPoolSize) {
                MyThread thread = new MyThread(task);
                thread.start();
                corePool.add(thread);
                corePoolSize++;
            }
            // 线程池满，任务进入等待队列
            else {
                if (!blockingQueue.offer(task))
                    // 等待队列已满，拒绝任务
                    System.err.println("Task queue is full, task is rejected.");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyThreadPool threadPool = new MyThreadPool(5, 10);
        Runnable task = () -> System.out.println("Thread name: " + Thread.currentThread().getName());
        for (int i = 0; i < 20; i++)
            threadPool.execute(task);
    }
}
