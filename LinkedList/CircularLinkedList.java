package LinkedList;

public class CircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();

        circularLinkedList.addBoy(1);
        circularLinkedList.print();
    }

    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums 值不正確");
            return;
        }

        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                boy.setNext(first);
                curBoy.setNext(boy);
                curBoy = boy;
            }
        }
    }

    public void print() {
        if (first == null) {
            System.out.println("沒有元素");
            return;
        }

        Boy curBoy = first;

        do {
            System.out.println(curBoy);
            curBoy = curBoy.getNext();
        } while (curBoy != first);
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
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

    @Override
    public String toString() {
        return "Boy {\n  no = " + no + "\n}";
    }
}