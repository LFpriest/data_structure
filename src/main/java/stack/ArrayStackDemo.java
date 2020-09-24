package stack;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @author LingFeng
 * @create 2020-09-23 8:37 下午
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:从栈中取数据");
            System.out.println("请输入选择");

            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("取出的数据：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }

        System.out.println("程序退出");
    }

}

class ArrayStack{

    private int maxSize;//栈大小
    private int[] stack;//用来表示栈的数组
    private int top = -1;//栈顶

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满~~~");
            return;
        }
        top++;
        stack[top] = value;

    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //栈列表
    public void list(){
        if(isEmpty()){
            System.out.println("栈为空~");
            return;
        }
        for (int i = top; i>=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);

        }
    }
}