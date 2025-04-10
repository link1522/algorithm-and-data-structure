package HashTable;

public class EmployeeHashTable {

}

class HashTable {
    private EmployeeLinkedList[] employeeLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];
    }

    public void add(Employee employee) {
        int linkedListIndex = getRemainder(employee.id);
        employeeLinkedLists[linkedListIndex].add(employee);
    }

    private int getRemainder(int id) {
        return id % size;
    }

    public void list() {
        for (int i = 0; i < employeeLinkedLists.length; i++) {
            employeeLinkedLists[i].list();
        }
    }
}

class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmployeeLinkedList {
    public Employee head;

    public void add(Employee employee) {
        Employee last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = employee;
    }

    public void list() {
        if (head == null) {
            System.out.println("當前鍊表為空");
            return;
        }

        Employee cur = head;
        while (cur != null) {
            System.out.println("id: " + cur.id + ", name: " + cur.name);
            cur = cur.next;
        }
    }
}