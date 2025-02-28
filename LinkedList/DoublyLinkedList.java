package LinkedList;

public class DoublyLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.add(new HeroNode2(1, "hi", "hi2"));
        doublyLinkedList.add(new HeroNode2(3, "Max", "M"));
        doublyLinkedList.add(new HeroNode2(2, "Terry", "Tr"));
        doublyLinkedList.add(new HeroNode2(7, "Terry", "Tr"));
        doublyLinkedList.add(new HeroNode2(5, "Terry", "Tr"));
        doublyLinkedList.add(new HeroNode2(6, "Terry", "Tr"));
        doublyLinkedList.delete(6);
        doublyLinkedList.print();
    }

    public void add(HeroNode2 node) {
        HeroNode2 cur = head;

        while (true) {
            if (cur.next == null) {
                cur.next = node;
                node.pre = cur;
                break;
            }

            if (cur.no < node.no && node.no < cur.next.no) {
                node.next = cur.next;
                node.pre = cur;
                node.pre.next = node;
                node.next.pre = node;
                break;
            }

            cur = cur.next;
        }
    }

    public void update(HeroNode2 node) {
        HeroNode2 cur = head.next;

        Boolean found = false;

        while (true) {
            if (cur == null) {
                break;
            }

            if (cur.no == node.no) {
                found = true;
                break;
            }

            cur = cur.next;
        }

        if (found) {
            cur.name = node.name;
            cur.nickname = node.nickname;
        } else {
            throw new RuntimeException("找不到更新元素");
        }
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("鏈表為空");
        }

        HeroNode2 cur = head.next;
        while (cur.next != null) {
            System.out.println(cur);
            cur = cur.next;
        }
        System.out.println(cur);
    }

    public void delete(int no) {
        HeroNode2 temp = head.next;
        Boolean found = false;

        while (true) {
            if (temp == null) {
                break;
            }

            if (temp.no == no) {
                found = true;
                break;
            }

            temp = temp.next;
        }

        if (found) {
            temp.pre.next = temp.next;
            // 如果不是刪除最後一個元素，就要對下一個元素的 pre 進行修改
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode {\n  no = " + no + ",\n  name = " + name + ",\n  nickname = " + nickname + "\n}";
    }
}