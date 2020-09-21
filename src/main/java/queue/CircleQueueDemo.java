package queue;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @author LingFeng
 * @create 2020-09-11 5:02 下午
 */
public class CircleQueueDemo {

    public static void main(String[] args) {
        //创建一个队列
        CircleQueue que = new CircleQueue(4);
        //接收用户输入；
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        //输出菜单
        while (loop){
            System.out.println("s(show)：显示一个队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列获取数据");
            System.out.println("h(head)：查看队列头数据");
            //接收输入的字符
            key = scanner.next().charAt(0);

            switch (key){
                case 's':
                    que.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    que.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = que.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = que.getHead();
                        System.out.printf("队列头数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");

    }

}

class CircleQueue{
    //队列的最大容量
    private int maxsize;
    //front指向队列的第一个元素，初始值为0
    private int front;
    //rear指向队列的最后一个元素，初始值为0；
    private int rear;
    //存放数据的数组，模拟队列；
    private int[] arrque;

    public  CircleQueue(int arrMaxSize){
        maxsize = arrMaxSize;
        arrque = new int[arrMaxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是否已满；
    public  boolean isFull(){
        return  (rear+1)%maxsize == front;
    }

    //判断队列是否为空
    public  boolean isEmpty(){
        return  rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否已满
        if(isFull()){
            System.out.println("队列已满~~~");
            return;
        }
        arrque[rear] = n;
        rear = (rear+1)%maxsize;
    }

    //获取队列数据，数据移出队列
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空，不能取数据~~~");
        }
        int res = arrque[front];
        front = (front+1)%maxsize;
        return res;
    }

    //当前队列有数据的的个数
    public int size(){
        return (rear+maxsize-front)%maxsize;
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i <front+size(); i++) {
            System.out.printf("arrque[%d]=%d\n",i%maxsize,arrque[i%maxsize]);
        }
    }

    //获取队列头数据
    public int getHead(){
        if(isEmpty()){
            throw new RuntimeException("队列为空~~~");
        }
        return arrque[front];
    }

}
