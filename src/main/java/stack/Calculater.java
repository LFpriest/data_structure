package stack;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.SplittableRandom;

/**
 * @author LingFeng
 * @create 2020-09-25 6:32 下午
 */
public class Calculater {

    public static void main(String[] args) {
        //中缀表达式计算
        String expression = "7*2*2-5+1-5+3-4";
        //创建两个栈，一个数栈一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int res = 0;

        char ch;
        String keepNum = "";//用于拼接多位数

        //扫描表达式
        while (true) {
            if(index == expression.length() -1){
                break;
            }
            ch = expression.charAt(index);
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                }else {
                    if (operStack.priority(ch) <= operStack.priority((char) operStack.peek())) {
                        //当前操作符优先级小于或等于栈中的操作符，从数栈中pop出两个数，从符号栈中pop出一个符号，进行运算，运算结果push入数栈
                        //之后，当前操作符入符号栈
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        res = numStack.cal(num1,num2, (char) operStack.pop());
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }


            }else {
                numStack.push(ch-48);
            }

            index++;
        }

        //取出数栈中最后的两个数和符号栈中的符号，进行运算，得到的数就是表达式的值
        while (true) {
            if(operStack.isEmpty()){
                break;
            }

            num1 = numStack.pop();
            num2 = numStack.pop();
            res = numStack.cal(num1,num2, (char) operStack.pop());
        }

        System.out.println("计算结果为 = " + res);


    }
}


class ArrayStack2{

    private int maxSize;//栈大小
    private int[] stack;//用来表示栈的数组
    private int top = -1;//栈顶

    public ArrayStack2(int maxSize){
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

    //返回当前栈顶
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级,优先级越高值越大；
    public int priority(char oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char oper){
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    //计算方法
    public int cal(int num1,int num2,char oper){
        int res = 0;

        switch (oper) {
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                break;
        }

        return res;
    }


}
