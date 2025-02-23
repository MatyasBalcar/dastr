public class Main {
    public static void main(String[] args) {
        BinomialHeap heap = new BinomialHeap();

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);


        System.out.println("Heap after insertions:");
        heap.print();

    }
}
