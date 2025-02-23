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
        Collections.reverse(minTree.children);
        for (BinomialTree child : minTree.children) {
            child.parent = null;
            tempHeap.trees.add(child);
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
        for (BinomialTree tree : trees) {
            tree.print(0);
            System.out.println("-------------------------------");
        }
    }
}


