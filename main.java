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
        //GraphNode n4 = G.insertNode(4);
        //GraphNode n5 = G.insertNode(5);
        //GraphNode n6 = G.insertNode(6);

        GraphEdge e1 = G.insertEdge(n1,n2);
        GraphEdge e2 = G.insertEdge(n2,n3);
        GraphEdge e3 = G.insertEdge(n3,n1);



        DataOutputStream outStream = new DataOutputStream(out);

        RootedTree T = G.scc();

        outStream.close();
    }
}
