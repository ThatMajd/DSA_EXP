import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;

public class DynamicGraph {
    final int inf = 2147483647;

    GraphNode nhead;
    GraphNode ntail;

    GraphEdge ehead;
    GraphEdge etail;



    static int time;


    public DynamicGraph(){
        nhead = null;
        ntail = null;
    }

    public GraphNode insertNode(int nodeKey){
        //System.out.println("-Inserting Node" + nodeKey);
        GraphNode node = new GraphNode(nodeKey);
        if (nhead == null){
            nhead = node;
            ntail = nhead;
        }
        else {
            nhead.setPrev(node);
            node.setNext(nhead);
            nhead = node;
        }
        return  node;
    }
    public void deleteNode(GraphNode node){
        // check if anything is null
        // AAAAAAAAAAAAAAAAAAAAAAA
        if(node.parents.head != null || node.children.head != null){
            return;
        }
        //System.out.println("Deleting Node" + node.getKey());

        GraphNode prev = node.getPrev();
        GraphNode next = node.getNext();
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        node.delete();
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        //System.out.println("------ Inserting" + from.getKey() +" to "+ to.getKey());
        GraphEdge edge = new GraphEdge(from,to);
        if (ehead == null){
            ehead = edge;
            etail = ehead;
        }
        else {
            ehead.setPrev(edge);
            edge.setNext(ehead);
            ehead = edge;
        }
        return  edge;
    }
    public void deleteEdge(GraphEdge edge){
        // AAAAAAAAAAAAAAAAAAAAAAA


        edge.delete();
    }

    private LinkedList<GraphNode> flipList(LinkedList<GraphNode> fOrder){
        LinkedList<GraphNode> res = new LinkedList<GraphNode>();
        while (fOrder.head != null){
            res.insert(fOrder.pop().data);
        }
        return res;
    }

    private void changeOrderVerticies(LinkedList<GraphNode> fOrder){

        nhead = fOrder.head.data;
        ntail = fOrder.tail.data;

        Node<GraphNode> temp = fOrder.peek();
        while (temp != null) {
            if(temp.getPrev() == null){
                // first element in fOrder
                temp.data.setPrev(null);
            }
            else{
                GraphNode node_prev = temp.getPrev().data;
                temp.data.setPrev(node_prev);
            }
            if(temp.getNext() == null){
                // last element in fOrder
                temp.data.setNext(null);
            }
            else {
                GraphNode node_next = temp.getNext().data;
                temp.data.setNext(node_next);
            }
            temp = temp.getNext();
        }
    }

    public RootedTree scc(){
        return DFS2(DFS());
    }

    public LinkedList<GraphNode> DFS(){
        time = 0;
        LinkedList<GraphNode> fOrder = new LinkedList<>();
        DFS_init();
        GraphNode temp = nhead;


        while (temp != null){
            if(temp.color == 0){
                DFS_Visit(temp, fOrder);
            }
            temp = temp.getNext();
        }

        return fOrder;
    }
    public RootedTree DFS2(LinkedList<GraphNode> fOrder){
        time = 0;

        DFS_init();

        Node<GraphNode> temp = fOrder.head;

        GraphNode dummy = new GraphNode(0);
        RootedTree T = new RootedTree();
        T.setRoot(dummy);


        while (temp != null){
            if(temp.data.color == 0){
                RootedTree new_ccomp = new RootedTree();
                new_ccomp.setRoot(temp.data);
                T.subRooted.insert(new_ccomp);

                DFS_Visit_transpose(temp.data,fOrder,new_ccomp);

            }
            temp = temp.getNext();
        }

        return T;
    }


    public void DFS_Visit(GraphNode node,LinkedList<GraphNode> fOrder){
        // white=0,grey=1,black=2;
        time++;
        node.d_time = time;
        node.color = 1;
        Node<GraphNode> temp = node.children.head;
        while (temp != null){
            if(temp.data.color == 0){
                temp.data.pi = node;
                DFS_Visit(temp.data,fOrder);
            }
            temp = temp.getNext();
        }
        node.color = 2;
        time++;
        node.f_time = time;
        fOrder.place_first(node);
    }

    private void DFS_init(){
        GraphNode temp = nhead;
        while (temp != null){
            temp.color = 0;
            temp.pi = null;
            temp.d_time = 0;
            temp.f_time = 0;
            temp = temp.getNext();
        }
    }

    private void BFS_init(GraphNode source,LinkedList<GraphNode> queue){
        GraphNode temp = nhead;
        while (temp != null){
            temp.color = 0;
            temp.pi = null;
            temp.distance = inf;
            temp = temp.getNext();
        }
        source.color = 1;
        source.distance = 0;
        source.pi = null;
        queue.insert(source);
    }

    public void DFS_Visit_transpose(GraphNode node,LinkedList<GraphNode> fOrder,RootedTree B){
        // white=0,grey=1,black=2;
        //System.out.println("--" + node.getKey());
        time++;
        node.d_time = time;
        node.color = 1;
        Node<GraphNode> temp = node.parents.head;
        while (temp != null){
            if(temp.data.color == 0){
                RootedTree sT = new RootedTree();
                sT.setRoot(temp.data);
                B.subRooted.insert(sT);

                temp.data.pi = node;
                DFS_Visit_transpose(temp.data,fOrder,sT);
            }
            temp = temp.getNext();
        }
        node.color = 2;
        time++;
        node.f_time = time;

    }

//    public void DFS_Visit_transpose(GraphNode node,LinkedList<GraphNode> fOrder){
//        // white=0,grey=1,black=2;
//        time++;
//        node.d_time = time;
//        node.color = 1;
//        Node<GraphNode> temp = node.parents.head;
//        while (temp != null){
//            if(temp.data.color == 0){
//
//                temp.data.pi = node;
//                DFS_Visit_transpose(temp.data,fOrder);
//            }
//            temp = temp.getNext();
//        }
//        node.color = 2;
//        time++;
//        node.f_time = time;
//        fOrder.insert(node);
//    }

    public RootedTree bfs(GraphNode source){
        LinkedList<GraphNode> queue = new LinkedList<>();
        BFS_init(source,queue);

        RootedTree res = new RootedTree();
        res.setRoot(source);

        while (queue.head != null){
            Node<GraphNode> u = queue.peek();

            Node<GraphNode> v = u.data.children.head;
            while (v != null){
                if(v.data.color == 0){

                    v.data.color = 1;
                    v.data.distance = u.data.distance + 1;
                    v.data.pi = u.data;

                    RootedTree sT = new RootedTree();
                    sT.setRoot(v.data);
                    u.data.tree.subRooted.insert(sT);

                    queue.insert(v.data);
                }
                v = v.getNext();
            }
            u.data.color = 2;
        }
        return res;
    }


    public void printLayerAux(GraphNode node, DataOutputStream out){
        LinkedList <GraphNode> queue = new LinkedList<>();
        queue.insert(node);
        while (true){
            int current_level = queue.size;
            if (current_level == 0){
                break;
            }
            while (current_level > 0) {

                GraphNode nd = queue.peek().data;
                System.out.print(nd.getKey());
                if (current_level > 1){
                    System.out.print(",");
                }

                Node<GraphNode> temp = nd.children.head;
                while (temp != null) {
                    queue.insert(temp.data);
                    temp = temp.getNext();
                }
                current_level--;
            }
            System.out.println();
        }
    }

    public void print(){
        GraphNode temp = nhead;
        while (temp != null){
            System.out.println((temp).getKey() + ", "+(temp).f_time);
            temp = temp.getNext();
        }
    }

}