package LinkedList;

public class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, "hi", "hi2"));
        singleLinkedList.add(new HeroNode(3, "Max", "M"));
        singleLinkedList.add(new HeroNode(2, "Terry", "Tr"));
        singleLinkedList.add(new HeroNode(7, "Terry", "Tr"));
        singleLinkedList.add(new HeroNode(5, "Terry", "Tr"));
        singleLinkedList.add(new HeroNode(5, "Terry", "Tr"));
        singleLinkedList.add(new HeroNode(6, "Terry", "Tr"));
        singleLinkedList.print();
    }

    public void add(HeroNode node) {
        HeroNode temp = head;

        while (true) {
            if (temp.next == null) {
                temp.next = node;
                break;
            }

            if (temp.next.no > node.no) {
                node.next = temp.next;
                temp.next = node;
                break;
            }

            if (temp.next.no == node.no) {
                throw new RuntimeException("加入了已存在的編號: " + node.no);
            }

            temp = temp.next;
        }
    }

    public void print() {
        if (head.next == null) {
            System.out.println("鏈表為空");
        }

        HeroNode temp = head.next;
        while (temp.next != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println(temp);
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode {\n  no = " + no + ",\n  name = " + name + ",\n  nickname = " + nickname + "\n}";
    }
}