import java.io.DataOutputStream;
import java.io.IOException;

import static java.lang.System.lineSeparator;
import static java.lang.System.out;

public class main {
    public static void main(String[] args) throws IOException {
        DynamicGraph G = new DynamicGraph();
        GraphNode n1 = G.insertNode(1);
        GraphNode n2 = G.insertNode(2);
        GraphNode n3 = G.insertNode(3);
        GraphNode n4 = G.insertNode(4);
        GraphNode n5 = G.insertNode(5);


        GraphEdge e1 = G.insertEdge(n1,n2);
        GraphEdge e2 = G.insertEdge(n2,n3);
        GraphEdge e3 = G.insertEdge(n2,n4);
        GraphEdge e4 = G.insertEdge(n1,n5);

        RootedTree T1 = new RootedTree();
        RootedTree T2 = new RootedTree();
        RootedTree T3 = new RootedTree();
        RootedTree T4 = new RootedTree();
        RootedTree T5 = new RootedTree();
        T1.setRoot(n1);
        T2.setRoot(n2);
        T3.setRoot(n3);
        T4.setRoot(n4);
        T5.setRoot(n5);

        T1.subRooted.insert(T2);
        T1.subRooted.insert(T5);
        T2.subRooted.insert(T3);
        T2.subRooted.insert(T4);


        DataOutputStream outStream = new DataOutputStream(out);

        LinkedList<Integer> test = new LinkedList<>();
        int iter = 1000000000;
        test.insert(1);
        test.insert(2);
        test.insert(3);
        test.insert(4);
        while (iter != 0) {
            test.peek();
            test.peek();
            test.peek();
            test.peek();
            test.insert(1);
            test.insert(2);
            test.insert(3);
            test.insert(4);
            iter--;
        }
        test.print();
        outStream.close();
    }
}
