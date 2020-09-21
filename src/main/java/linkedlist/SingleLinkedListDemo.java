package linkedlist;

import javax.sound.midi.Soundbank;

/**
 * @author LingFeng
 * @create 2020-09-15 10:00 上午
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        singleLinkedList.list();

        HeroNode newHeroNode = new HeroNode(2,"小卢","麒麟");
        singleLinkedList.update(newHeroNode);
        System.out.println("更改后的链表");
        singleLinkedList.list();

        singleLinkedList.delete(newHeroNode);
        System.out.println("删除后的链表");
        singleLinkedList.list();

        System.out.println("链表节点数="+singleLinkedList.size());

        System.out.println("倒数节点："+singleLinkedList.findLastIndexNode(2));

        System.out.println("反转后的链表");
        singleLinkedList.reverseList();
        singleLinkedList.list();


    }

}


class SingleLinkedList{
    //初始化一个head节点；
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到链表，不考虑顺序；
    public void  add(HeroNode heroNode){
        //因为head节点不能动，我们需要一个辅助遍历temp
        HeroNode temp = head;
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
    }

    //将节点插入指定的位置；
    public void addByOrder(HeroNode heroNode){
        //辅助遍历的temp
        HeroNode temp = head;
        //标志输入的编号是否已经存在；
        boolean flag = false;
        //遍历链表，查找合适的位置
        while (true){
            if (temp.next == null){//链表的末尾
                break;
            }
            if(temp.next.no > heroNode.no ){//找到指定的位置；
                break;
            }else if(temp.next.no == heroNode.no){//添加的节点编号已经存在；

                flag = true;
                break;
            }
            //没有找到就继续遍历
            temp = temp.next;
        }
        if (flag){
            System.out.println("要添加的节点已经存在~~~");
        }else {
            //将节点插入到链表中；
            heroNode.next = temp.next;//将插入的节点的next指向temp指向的下一个节点；
            temp.next = heroNode;//将temp指向的下一个节点变更为要插入的节点；
        }
    }


    //根据no查找节点，更新节点name和nickname；
    public void update(HeroNode heroNode){
        //遍历用temp
        HeroNode temp = head;
        //是否找到指定的节点；
        boolean flag = false;
        while (true){
            if(temp.next == null){
                System.out.println("链表为空~~~");
                break;
            }
            if(temp.next.no == heroNode.no){//找到指定节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.next.name = heroNode.name;
            temp.next.nickName = heroNode.nickName;
        }else {
            System.out.println("未找到指定的节点~~~");
        }
    }

    public void delete(HeroNode heroNode){
        //遍历用temp
        HeroNode temp = head;
        //是否找到指定节点
        boolean flag = false;
        while (true){
            if(temp.next == null){//链表末尾
                break;
            }
            if(temp.next.no == heroNode.no){//找到指定节点
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("未找到指定节点");
        }
    }


    //链表节点数
    public int size(){
        //遍历用temp
        HeroNode temp = head;
        if(temp.next == null){
            return 0;
        }
        int size = 0;
        while (temp.next != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    //查找倒数第n个节点
    public HeroNode findLastIndexNode(int index){
        if(head.next == null){//判空链表
            return  null;
        }
        int size = size();//链表有效节点数
        if(index<=0 || index>size){
            System.out.println("输入位置无效");
            return null;
        }

        //当前节点
        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //遍历链表；
    public void list(){
        if(head.next == null){
            System.out.println("链表为空~~~");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //翻转链表
    public void reverseList(){
        //如果链表为空或者链表只有1个节点，则无需反转，直接返回；
        if(head.next == null || head.next.next == null){
            return;
        }
        HeroNode cur = head.next;//当前节点
        HeroNode next = cur.next;//当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");//临时头节点
        while (cur != null){
            next = cur.next;//先保存当前节点的下一个节点；
            //相当于将抽出来的节点插入临时头结点和临时头节点的下一节点中间；
            cur.next = reverseHead.next;//抽出来的节点的下一个节点指向临时头结点的下一个节点；
            reverseHead.next = cur;//临时头结点的下一个节点指向抽出来的节点；
            cur = next;
        }
        //遍历完成后，将原head的下一个节点指向临时头结点的下一个节点
        //相当于用原head替换调反转的链表的临时头结点；
        head.next = reverseHead.next;
    }




}

class HeroNode{
    public int no;
    public String name;
    public String nickName;
    //指向下一个节点
    public HeroNode next;

    public HeroNode(int no,String name,String nickName){
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