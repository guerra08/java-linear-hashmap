public class LinkedListChild {

    public class NodeChild {
        private int data;
        private String name;
        private NodeChild next;

        public int getData() {
            return data;
        }

        public String getName() {
            return name;
        }

        public NodeChild getNext() {
            return next;
        }
    }

    private NodeChild head;
    private NodeChild tail;
    private int count = 0;

    public int getCount() {
        return count;
    }

    public NodeChild getHead() {
        return this.head;
    }

    public void add(int value, String name) {
        NodeChild toAdd = new NodeChild();
        toAdd.data = value;
        toAdd.name = name;
        if (this.count == 0) {
            this.head = toAdd;
            this.tail = toAdd;
            count++;
        } else if (this.count == 1) {
            this.head.next = toAdd;
            this.tail = toAdd;
            count++;
        } else {
            this.tail.next = toAdd;
            this.tail = toAdd;
            count++;
        }
    }

    public void printList() {
        NodeChild n = this.head;
        for (int i = 0; i < this.count; i++) {
            System.out.println(n.data + " - " + n.name);
        }
    }
}