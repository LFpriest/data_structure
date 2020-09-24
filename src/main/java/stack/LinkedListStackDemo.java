package stack;

import java.util.Scanner;

/**
 * @author LingFeng
 * @create 2020-09-24 5:08 下午
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(4);
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
                    linkedListStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    Element element = new Element(value);
                    linkedListStack.push(element);
                    break;
                case "pop":
                    try {
                        int res = linkedListStack.pop();
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


class LinkedListStack{
    private int maxSize;
    private int count = 0;//链表元素计数；
    private Element top = new Element(0);//表示栈顶

    public LinkedListStack(int maxSize){
        this.maxSize = maxSize;
    }

    //栈满
    public boolean isFull(){
        if(count == maxSize){
            return true;
        }else {
            return false;
        }
    }

    //栈空
    public boolean isEmpty(){
        if(count == 0){
            return true;
        }else {
            return false;
        }
    }

    //入栈
    public void push(Element element){

        if(isFull()){
            System.out.println("栈已满");
            return;
        }

        element.next = top.next;
        top.next = element;
        count++;

    }

    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int res = top.next.value;
        top.next = top.next.next;
        count--;
        return res;
    }

    //栈列表
    public void list(){
        System.out.println("coutn="+count);
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        Element helper = top.next;

        while (true){
            if(helper == null){
                break;
            }
            System.out.printf("链表元素：%d\n",helper.value);
            helper = helper.next;
        }
    }


}

class Element{
    public int value;
    public Element next;

    public Element(int value){
        this.value = value;
    }

}
