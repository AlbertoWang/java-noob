package cn.albertowang.datastructure.storeqq;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/2/27 17:56
 * @description 设计一个数据结构，存储1亿个QQ号，要求查询时间复杂度为O(1)，尽量占用更少的内存空间
 **/

public class QQSet {
    // 使用位存储，numbers中的每个数字转化为二进制bit流，每一位为1对应QQ号为该位的存在
    private int[] numbers;

    // 根据存储数量的多少，可以对numbers数组进行大小的设置
    public QQSet() {
        numbers = new int[2];
    }

    public void put(int qq) {
        // 将QQ号的长度分别存放，相当于hash操作
        if (qq > Math.pow(2, 16)) {
            qq = qq >> 16;
            numbers[1] += 1 << qq;
        } else
            numbers[0] += 1 << qq;
    }

    public boolean get(int qq) {
        // 根据QQ号的长度找对应的数字，并验证是否存在
        if (qq > Math.pow(2, 16)) {
            qq = qq >> 16;
            return (numbers[1] >> qq & 1) == 1;
        } else
            return (numbers[0] >> qq & 1) == 1;
    }

    public static void main(String[] args) {
        QQSet qqSet = new QQSet();
        qqSet.put(12345);
        qqSet.put(7893);
        qqSet.put(1231232121);
        System.out.println(qqSet.get(1231232121));
    }
}
