import java.util.ArrayList;
import java.util.List;

class BinomialTree {
    int key, order;
    BinomialTree parent, child;
    List<BinomialTree> siblings;

    public BinomialTree(int key) {
        this.key = key;
        this.order = 0;
        this.siblings = new ArrayList<>();
    }

    public BinomialTree merge(BinomialTree other) {
        if (this.key > other.key) return other.merge(this);

        other.parent = this;

        if (this.child == null) {
            this.child = other;
        } else {
            this.child.siblings.add(other);
        }

        this.order++;
        return this;
    }

    public void print(int depth) {
        System.out.println(" ".repeat(depth * 2) + key);

        if (child != null) {
            child.print(depth + 1);
        }

        for (BinomialTree sibling : siblings) {
            sibling.print(depth);
        }
    }
}
