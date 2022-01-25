public class GraphNode extends Nodeable<GraphNode>{
    private final int key;
    public GraphNode next;
    public GraphNode prev;

    // These lists are directional adjacency lists
    LinkedList<GraphNode> parents;
    LinkedList<GraphNode> children;

    RootedTree tree;

    int color;
    GraphNode pi;
    int d_time;
    int f_time;

    int distance;


    public GraphNode(int tKey){
        this.key = tKey;
        // parents = from , children = to
        this.parents = new LinkedList<>();
        this.children = new LinkedList<>();
        tree = null;
    }

    public void setNext(GraphNode next) {
        this.next = next;
    }

    public void setPrev(GraphNode prev) {
        this.prev = prev;
    }

    public GraphNode getNext() {
        return next;
    }

    public GraphNode getPrev() {
        return prev;
    }

    @Override
    public void setNode(Node<GraphNode> nd) {
        p_node = nd;
    }

    public int getKey() {
        return key;
    }

    public void delete(){
        this.next = null;
        this.prev = null;
    }

    public int getInDegree(){
        Node<GraphNode> temp = parents.head;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.getNext();
        }
        return count;
    }
    public int getOutDegree(){
        Node<GraphNode> temp = children.head;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    @Override
    public String toString() {
        return String.valueOf(key) + ' ';
    }
}
