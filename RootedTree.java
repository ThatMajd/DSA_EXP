import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;
    LinkedList<RootedTree> subRooted;

    public RootedTree(){
        root = null;
        subRooted = new LinkedList<>();
    }

    public void setRoot(GraphNode nroot) {
        if (root == null) {
            this.root = nroot;
            root.tree = this;
        }
        // throw error or something idk
    }

    public GraphNode getRoot() {
        return root;
    }


    public void preorderPrint(DataOutputStream out) throws IOException {
        StringBuilder output = new StringBuilder();

        preorderPrintaux(root,output);

        int len = output.length();
        output.deleteCharAt(len-1);
        out.writeBytes(output.toString());
    }

    void preorderPrintaux(GraphNode node, StringBuilder output) throws IOException {
        if (node == null){
            return;
        }
        output.append(node.getKey());
        output.append(",");
        if (node.tree.subRooted.head == null){
            return;
        }
        Node<RootedTree> temp = node.tree.subRooted.peek();
        while (temp != null){
            preorderPrintaux(temp.data.root, output);
            temp = temp.getNext();
        }
    }

    public void printByLayer(DataOutputStream out){
        printLayerAux(root,out);
    }

    public void printLayerAux(GraphNode node, DataOutputStream out){
        LinkedList <GraphNode> queue = new LinkedList<>();
        queue.insert(node);
        while (true){
            int node_count = queue.size;
            if (node_count == 0){
                break;
            }
            while (node_count > 0) {

                GraphNode nd = queue.peek().data;
                System.out.print(nd.getKey());
                if (node_count > 1){
                    System.out.print(",");
                }

                Node<RootedTree> temp = nd.tree.subRooted.head;
                while (temp != null) {
                    queue.insert(temp.data.root);
                    temp = temp.getNext();
                }
                node_count--;
            }
            if(queue.head != null) {
                System.out.println();
            }
        }
    }
//    public void preorderPrint(DataOutputStream out) throws IOException {
//        print_tree(this, out);
//    }
//    private void print_tree(RootedTree T,DataOutputStream out) throws IOException {
//        //out.writeBytes(String.valueOf(T.root.getKey()));
//        out.writeBytes(System.lineSeparator());
//        Node<RootedTree> current = T.subRooted.head;
//        while (current != null){
//            out.writeBytes(String.valueOf(current.data.getRoot().getKey()));
//            out.writeBytes(",");
//            print_tree(current.data,out);
//            current = current.getNext();
//        }
//    }


}
