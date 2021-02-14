package cn.albertowang.datastructure.threadpool;

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
                    Runnable queueTask = taskQueue.poll();
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

    private final int threadNum;
    private int workingThreadNum;
    private ArrayList<MyThread> threads;
    private ArrayBlockingQueue<Runnable> taskQueue;
    private final ReentrantLock lock = new ReentrantLock();

    public MyThreadPool(int poolSize, int taskQueueSize) {
        threadNum = poolSize;
        threads = new ArrayList<>(poolSize);
        taskQueue = new ArrayBlockingQueue<>(taskQueueSize);
        workingThreadNum = 0;
    }

    public void execute(Runnable task) {
        try {
            lock.lock();
            // 线程数量小于线程池大小
            if (workingThreadNum < threadNum) {
                MyThread thread = new MyThread(task);
                thread.start();
                threads.add(thread);
                workingThreadNum++;
            }
            // 线程池满，任务进入等待队列
            else {
                if (!taskQueue.offer(task))
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
