package LinkedList;

public class CircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();

        circularLinkedList.addBoy(5);
        circularLinkedList.countBoy(1, 2, 5);
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

    /**
     * 約瑟夫問題 Josephus problem
     * 
     * @param startNo  表示從哪個小孩開始
     * @param countNum 表示數幾下
     * @param nums     表示幾個小孩圍成一圈
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("參數輸入有問題");
            return;
        }

        // 先將 helper 移動到最後一個元素
        Boy helper = first;
        do {
            helper = helper.getNext();
        } while (helper.getNext() != first);

        // 根據 startNo 進行偏移
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 開始數
        while (true) {
            if (helper == first) {
                break;
            }

            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.println(first);
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.println("最後留在圈中的: " + first);
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
        return "Boy { no = " + no + "}";
    }
}