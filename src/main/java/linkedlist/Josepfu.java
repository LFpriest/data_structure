package linkedlist;

import javax.management.loading.PrivateClassLoader;
import javax.sound.midi.Soundbank;

/**
 * @author LingFeng
 * @create 2020-09-23 6:19 下午
 */
public class Josepfu {
    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showList();

        circleSingleLinkedList.countBoy(1,2,5);//2-4-1-5-3

    }
}

//单向环形链表
class CircleSingleLinkedList{
    private Boy first;

    public void addBoy(int nums){
        //nums校验
        if(nums<1){
            System.out.println("输入的数据不合法~");
            return;
        }

        Boy curBoy = null;//辅助指针，帮助建立环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if(i == 1){
                first = boy;
                first.setNext(first);//只有一个，自己的next指向自己；
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);//添加后，需要将添加的boy的next指向first，即末尾boy的next指向first，形成环；
                curBoy = boy;
            }
        }
    }

    public void showList() {
        //判空
        if (first.getNext() == null) {
            System.out.println("链表为空");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.printf("元素编号是：%d \n",curBoy.getNo());
            if (curBoy.getNext() == first) {//链表循环结束
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    /**
     * @param startNo  从哪里开始
     * @param countNum 数几出圈
     * @param nums     标识最初圈中元素数；
     */
    public void countBoy(int startNo,int countNum,int nums){
        //数据校验
        if(startNo<0 || startNo>nums || first == null){
            System.out.println("参数有误~~~");
            return;
        }

        //辅助指针helper,helper指向环形链表最后一个节点；
        Boy helper = first;
        while (true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        //将first指针指向startNo元素，help指向first前一个元素；
/*        while (true){
            if(first.getNo() == startNo){
                break;
            }
            first = first.getNext();
            helper = helper.getNext();
        }*/

        for (int i = 0; i < startNo-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让first和helper指针同时移动countNum-1次；
        while (true){
            if(first.getNext() == first){
                break;
            }
            for (int i = 0; i <countNum-1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("元素%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留下的元素：%d\n",first.getNo());
    }

}

class Boy{
    private int  no;
    private Boy next;

    public Boy(int no){
        this.no = no ;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }


}
