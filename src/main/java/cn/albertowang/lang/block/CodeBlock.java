package cn.albertowang.lang.block;

/**
 * @author AlbertoWang
 * @email AlbertoWang@FoxMail.com
 * @date 2021/1/12 下午10:43
 * @description 静态代码块、普通代码块、构造函数与方法内代码块执行顺序
 **/

/*
输出：
Static code block
Non-static code block（此处证明了顺序为：静态代码块 -> 普通代码块）
Constructor
Static method（如果没调用构造函数，顺序为：静态代码块 -> 方法内代码块）
Code block in static method
 */

public class CodeBlock {

    // 构造函数
    public CodeBlock() {
        System.out.println("Constructor");
    }

    // 非静态代码块
    {
        System.out.println("Non-static code block");
    }

    // 静态代码块
    static {
        System.out.println("Static code block");
    }

    // 静态方法
    public static void test() {
        System.out.println("Static method");
        {
            System.out.println("Code block in static method");
        }
    }

    public static void main(String[] args) {
        CodeBlock test = new CodeBlock();
        CodeBlock.test();
    }
}
