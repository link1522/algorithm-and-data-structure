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
        singleLinkedList.add(new HeroNode(6, "Terry", "Tr"));
        singleLinkedList.update(new HeroNode(5, "Book", "BB"));
        singleLinkedList.reverse();
        singleLinkedList.print();
    }

    public void reverse() {
        // 空的或者只有一個元素就不用處理
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 創建一個反向的 head
        HeroNode reversedHead = new HeroNode(0, null, null);

        HeroNode cur = head.next;
        while (cur != null) {
            // 將原鏈表先跳過當前元素
            head.next = cur.next;
            // 當前元素的 next 指向反向 head 的 next
            cur.next = reversedHead.next;
            // 反向 head 的 next 在指向當前元素，相當於直接放在最前方
            reversedHead.next = cur;
            // 當前元素改為 head 的 next
            cur = head.next;
        }
        head.next = reversedHead.next;
    }

    public HeroNode getFromBack(int count) {
        HeroNode temp = head.next;

        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        if (size - count < 0) {
            throw new RuntimeException("超出鏈表長度");
        }

        temp = head.next;
        for (int i = 0; i < (size - count); i++) {
            temp = temp.next;
        }

        return temp;
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

    public void update(HeroNode node) {
        if (isEmpty()) {
            System.out.println("鏈表為空");
            return;
        }

        HeroNode temp = head.next;
        boolean foundNo = false;

        while (true) {
            if (temp == null) {
                break;
            }

            if (temp.no == node.no) {
                foundNo = true;
                break;
            }

            temp = temp.next;
        }

        if (foundNo) {
            temp.name = node.name;
            temp.nickname = node.nickname;
        } else {
            System.out.printf("沒有找到 %d 節點，不能修改\n", node.no);
        }
    }

    public void delete(int no) {
        if (isEmpty()) {
            System.out.println("鏈表為空");
            return;
        }

        HeroNode temp = head;

        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.no == no) {
                temp.next = temp.next.next;
                break;
            }

            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public void print() {
        if (isEmpty()) {
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