public class Main {
    public static void main(String[] args) {
        System.out.println("Binomial heap main guide (protoze to fakt nejde jen tak poznat:)\n **** Deli jednotlive printy \n ----- Deli jednotlive stromy v halde");
        System.out.println("Stromy se printujou jakoby otocene na stranu, sloupce jsopu patra a podle vysky clovek pozna poradi, jak jdou prvky za sebou nebo ci jsou to potomci");
        /*
        * Takze prvni strom otoceny *dobre* by vypadal
        * 9
        *   10
        *
        * A druhy pak
        *
        *           1
        *       2   3   5
        *             4   6 7
        *                     8
        * (v konzoli jsou ale otocene na stranu jak rika print)
        * */
        System.out.println("\n".repeat(5));//par novych radku

        BinomialHeap heap = new BinomialHeap();

        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);
        heap.insert(9);
        heap.insert(10);
        System.out.println("Heap after insertions:");
        heap.print();

        Integer extracted = heap.extractMin();
        System.out.println("Heap after extracting min of: " + extracted);
        heap.print();


    }
}
