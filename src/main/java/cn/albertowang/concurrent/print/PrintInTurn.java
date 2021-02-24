package cn.albertowang.concurrent.print;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/23 17:26
 * @description 两个线程轮流打印1A 2B 3C
 **/

public class PrintInTurn {
    // 多线程内存可见的打印标记
    private static volatile boolean printFlag = true;

    // synchronized用来加锁的对象
    private static final Object lock = new Object();

    // synchronize作用在对象上，wait和notifyAll作用于加锁对象
    static class Task1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) { // 加锁
                    while (printFlag) { // printFlag=false才打印，否则等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    char c = (char) ('A' + i);
                    System.out.print(c + " ");
                    printFlag = true; // 打印过更新printFlag，等待其他线程打印
                    lock.notifyAll();
                }
            }
        }
    }

    // synchronize作用在对象上，wait和notifyAll作用于加锁对象
    static class Task2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (lock) { // 加锁
                    while (!printFlag) { // printFlag=true才打印，否则等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i + 1);
                    printFlag = false; // 打印过更新printFlag，等待其他线程打印
                    lock.notifyAll();
                }
            }
        }
    }

    // synchronized作用在方法上，wait和notifyAll作用于方法自身
    public synchronized void printA() {
        for (int i = 0; i < 3; i++) {
            while (printFlag) { // printFlag=false才打印，否则等待
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            char c = (char) ('A' + i);
            System.out.print(c + " ");
            printFlag = true; // 打印过更新printFlag，等待其他线程打印
            this.notifyAll();
        }
    }

    // synchronized作用在方法上，wait和notifyAll作用于方法自身
    public synchronized void printB() {
        for (int i = 0; i < 3; i++) {
            while (!printFlag) { // printFlag=false才打印，否则等待
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int c = 1 + i;
            System.out.print(c);
            printFlag = false; // 打印过更新printFlag，等待其他线程打印
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        PrintInTurn printInTurn = new PrintInTurn();
        // synchronized作用在方法的调用过程（实现过程为类内方法）
        Thread threadA = new Thread(printInTurn::printA);
        Thread threadB = new Thread(printInTurn::printB);
        threadA.start();
        threadB.start();
        // synchronized作用在对象的调用过程（实现过程为类内类）
        threadA = new Thread(new Task1());
        threadB = new Thread(new Task2());
        threadA.start();
        threadB.start();
    }
}
