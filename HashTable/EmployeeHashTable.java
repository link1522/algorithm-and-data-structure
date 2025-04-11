package HashTable;

public class EmployeeHashTable {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        hashTable.add(new Employee(1, "HAHA"));
        hashTable.add(new Employee(7, "789"));
        hashTable.add(new Employee(66, "66 555"));
        hashTable.add(new Employee(28, "HAE"));
        hashTable.list();
        hashTable.find(21);
    }
}

class HashTable {
    private EmployeeLinkedList[] employeeLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];
        for (int i = 0; i < employeeLinkedLists.length; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    public void add(Employee employee) {
        int linkedListIndex = getRemainder(employee.id);
        employeeLinkedLists[linkedListIndex].add(employee);
    }

    public void find(int id) {
        int linkedListIndex = getRemainder(id);
        var employee = employeeLinkedLists[linkedListIndex].find(id);
        if (employee == null) {
            System.out.println("找不到 id = " + id);
            return;
        }
        System.out.println("找到 id: " + employee.id + " name: " + employee.name);
    }

    private int getRemainder(int id) {
        return id % size;
    }

    public void list() {
        for (int i = 0; i < employeeLinkedLists.length; i++) {
            employeeLinkedLists[i].list(i);
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
        if (head == null) {
            head = employee;
            return;
        }

        Employee last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = employee;
    }

    public void list(int listOrder) {
        if (head == null) {
            System.out.println("第 " + listOrder + " 條鍊表為空");
            return;
        }

        Employee cur = head;
        System.out.println("第 " + listOrder + " 條練表有");
        while (cur != null) {
            System.out.println("    id: " + cur.id + ", name: " + cur.name);
            cur = cur.next;
        }
    }

    public Employee find(int id) {
        Employee cur = head;
        while (cur != null) {
            if (cur.id == id) {
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }
}