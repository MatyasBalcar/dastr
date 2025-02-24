import java.util.*;

class BinomialHeap {
    private List<BinomialTree> trees;

    public BinomialHeap() {
        this.trees = new ArrayList<>();
    }

    public void insert(int key) {
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.trees.add(new BinomialTree(key));
        this.merge(tempHeap);
    }

    public Integer min() {
        if (trees.isEmpty()) return null;
        return trees.stream().min(Comparator.comparingInt(t -> t.key)).get().key;
    }

    public Integer extractMin() {
        if (trees.isEmpty()) return null;

        BinomialTree minTree = null;
        int minKey = Integer.MAX_VALUE;
        for (BinomialTree tree : trees) {
            if (tree.key < minKey) {
                minKey = tree.key;
                minTree = tree;
            }
        }

        trees.remove(minTree);
        BinomialHeap tempHeap = new BinomialHeap();

        // Convert child & siblings into a separate heap
        if (minTree.child != null) {
            List<BinomialTree> childTrees = new ArrayList<>();
            BinomialTree current = minTree.child;
            while (current != null) {
                BinomialTree nextSibling = (current.siblings.isEmpty()) ? null : current.siblings.get(0);
                current.parent = null;
                current.siblings.clear(); // Break sibling connection
                childTrees.add(current);
                current = nextSibling;
            }
            Collections.reverse(childTrees); // Reverse to maintain order
            tempHeap.trees.addAll(childTrees);
        }

        this.merge(tempHeap);
        return minKey;
    }

    public void decreaseKey(BinomialTree node, int newKey) {
        if (newKey > node.key) throw new IllegalArgumentException("New key must be smaller");
        node.key = newKey;

        while (node.parent != null && node.key < node.parent.key) {
            int temp = node.key;
            node.key = node.parent.key;
            node.parent.key = temp;
            node = node.parent;
        }
    }

    public void merge(BinomialHeap other) {
        List<BinomialTree> mergedTrees = new ArrayList<>();
        mergedTrees.addAll(this.trees);
        mergedTrees.addAll(other.trees);
        mergedTrees.sort(Comparator.comparingInt(t -> t.order));

        this.trees = new ArrayList<>();
        BinomialTree carry = null;
        for (BinomialTree tree : mergedTrees) {
            if (carry == null) {
                carry = tree;
            } else if (carry.order == tree.order) {
                carry = carry.merge(tree);
            } else {
                this.trees.add(carry);
                carry = tree;
            }
        }
        if (carry != null) this.trees.add(carry);
    }

    public void print() {
        System.out.println("\n".repeat(2));
        System.out.println("***********************************");
        for (BinomialTree tree : trees) {
            tree.print(0);
            System.out.println("-------------------------------");
        }
        System.out.println("***********************************");
        System.out.println("\n".repeat(2));
    }
}
