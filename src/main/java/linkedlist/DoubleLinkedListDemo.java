package linkedlist;

/**
 * @author LingFeng
 * @create 2020-09-22 4:53 下午
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        //展示链表元素
        doubleLinkedList.list();

        //修改
        HeroNode2 heroNode2 = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(heroNode2);
        System.out.println("修改后的链表");
        doubleLinkedList.list();

        //删除
        System.out.println("删除后的链表");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();



    }

}

class DoubleLinkedList{

    //初始化头节点
    private  HeroNode2 head = new HeroNode2(0,"","");
    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历链表；
    public void list(){
        if(head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        HeroNode2 temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }


    //添加节点到链表，不考虑顺序；
    public void  add(HeroNode2 heroNode){
        //因为head节点不能动，我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，查找链表末端；
        while (true){
            //判断是否是链表的末尾
            if (temp.next == null){
                break;
            }
            //没有找到链表的末尾，就讲temp后移；
            temp=temp.next;
        }
        //退出循环后，temp就是链表的末尾了；
        //将链表的末尾的next指向将要加入链表的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    public void update(HeroNode2 heroNode2){
        //遍历用temp
        HeroNode2 temp = head;
        //是否找到指定的节点；
        boolean flag = false;
        while (true){
            if(temp.next == null){
                System.out.println("链表为空~~~");
                break;
            }
            if(temp.next.no == heroNode2.no){//找到指定节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.next.name = heroNode2.name;
            temp.next.nickName = heroNode2.nickName;
        }else {
            System.out.println("未找到指定的节点~~~");
        }
    }


    public void delete(int no){
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }

        //遍历用temp
        HeroNode2 temp = head;
        //是否找到指定的节点；
        boolean flag = false;
        while (true){
            if(temp.next == null){
                System.out.println("链表为空~~~");
                break;
            }
            if(temp.next.no == no){//找到指定节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){//找到节点
            temp = temp.next;
            temp.pre.next = temp.next;
            if(temp.next != null){//如果找到的节点是链表的最后一个节点，则只需要将temp前面的节点的next指向temp的下一个节点，即null；
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("删除的节点不存在");
        }
    }


}


class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点；
    public HeroNode2 pre;//指向前一个节点；

    public HeroNode2(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
