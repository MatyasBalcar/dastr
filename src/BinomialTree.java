import java.util.ArrayList;
import java.util.List;

class BinomialTree {
    int key, order;
    BinomialTree parent;
    List<BinomialTree> children;

    public BinomialTree(int key) {
        this.key = key;
        this.order = 0;
        this.children = new ArrayList<>();
    }

    public BinomialTree merge(BinomialTree other) {
        if (this.key > other.key) return other.merge(this);
        other.parent = this;
        this.children.add(other);
        this.order++;
        return this;
    }

    public void print(int depth) {
        System.out.println(" ".repeat(depth * 2) + key);
        for (BinomialTree child : children) {
            child.print(depth + 1);
        }
    }
}