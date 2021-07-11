public class LinearHashMap {

    public class Node {
        private String name;
        private String fatherName;
        private int dividedAmount;
        private int totalLand;
        private final LinkedListChild children = new LinkedListChild();

        public void setDividedAmount(int dividedAmount) {
            this.dividedAmount = dividedAmount;
        }

        public int getDividedAmount() {
            return dividedAmount;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setTotalLand(int totalLand) {
            this.totalLand = totalLand;
        }

        public int getTotalLand() {
            return totalLand;
        }

        public LinkedListChild getChildren() {
            return children;
        }
    }

    private final int M;
    private final Node[] data;
    private int count;
    private Node biggest = new Node();

    public Node getBiggest() {
        return biggest;
    }

    public void setBiggest(Node biggest) {
        this.biggest = biggest;
    }

    public LinearHashMap(int size) {
        this.M = size;
        this.data = new Node[this.M];
    }

    public int hashFunc(String key) {
        return (Math.abs(key.hashCode() % this.M));
    }

    public void add(String name, int land) {
        int curPos = hashFunc(name);
        Node toAdd = new Node();
        toAdd.setName(name);
        toAdd.setTotalLand(land);
        this.data[curPos] = toAdd;
        this.count++;
    }

    public void add(String father, String name, int land) {
        int curPos = hashFunc(name);
        Node thisFather = get(father);
        Node toAdd = new Node();
        toAdd.setName(name);
        toAdd.setTotalLand(land);
        toAdd.setFatherName(thisFather.getName());
        for (int i = curPos; i <= this.M - 1; i++) {
            if (i == this.M - 1) {
                if (this.data[i] == null) {
                    this.data[i] = toAdd;
                    thisFather.children.add(i, name);
                    this.count++;
                    return;
                }
                i = 0;
            }
            if (this.data[i] == null) {
                this.data[i] = toAdd;
                thisFather.children.add(i, name);
                this.count++;
                return;
            }
        }
    }

    public Node get(String name) {
        int hash = hashFunc(name);
        for (int i = hash; i <= this.M - 1; i++) {
            if (i == this.M - 1) {
                if (this.data[i].name.equals(name)) {
                    return this.data[i];
                }
                i = 0;
            }
            if (this.data[i].getName().equals(name)) {
                return this.data[i];
            }
        }
        return null;
    }

    public void computeLands(String root) {
        Node n = get(root);
        Node b = new Node();
        computeLandsPrivate(n, b);
        System.out.println("O filho da ultima geracao com a maior quantidade de terras e " + this.getBiggest().getName() + " com " + this.getBiggest().getTotalLand() + " terras.");
    }

    private void computeLandsPrivate(Node n, Node biggest) {
        if (n.getChildren().getCount() > 0) {
            n.setDividedAmount(n.getTotalLand() / n.getChildren().getCount());
            LinkedListChild.NodeChild nc = n.getChildren().getHead();
            Node toProcess = get(nc.getName());
            for (int i = 0; i < n.getChildren().getCount(); i++) {
                toProcess.setTotalLand(toProcess.getTotalLand() + n.getDividedAmount());
                biggest = toProcess;
                computeLandsPrivate(toProcess, biggest);
                if (nc.getNext() != null) {
                    nc = nc.getNext();
                    toProcess = get(nc.getName());
                }
            }
        } else if (n.getChildren().getCount() == 0) {
            if (n.getTotalLand() > getBiggest().getTotalLand()) {
                setBiggest(n);
            }
        }
    }

    public void printHashMap() {
        for (int i = 0; i < this.M; i++) {
            if (this.data[i] != null)
                System.out.println("Pai: " + this.data[i].getFatherName() + " - " + this.data[i].getName() + " - " + this.data[i].getTotalLand());
        }
        System.out.println();
        System.out.println(this.count);
        System.out.println();
    }

    public void printChildren(String father) {
        Node n = get(father);
        LinkedListChild.NodeChild nc = n.getChildren().getHead();
        for (int i = 0; i < n.getChildren().getCount(); i++) {
            System.out.println(get(nc.getName()).getName());
            nc = nc.getNext();
        }
    }

}