public class GraphEdge extends Nodeable<GraphEdge>{
    Node<GraphNode> src;
    Node<GraphNode> dst;

    public GraphEdge next;
    public GraphEdge prev;

    public GraphEdge(GraphNode f, GraphNode t){
        // put t as child of f and return it
        f.children.place_first(t);
        t.parents.place_first(f);
        dst = f.children.head;
        src = t.parents.head;
    }
    public void delete(){

        // src.getPrev() is in dst.data.parents
        // dst is in src.children

        if (src.getPrev() == null && src.getNext() != null){
            // src is the head but there are other nodes in the list
            src.getNext().setPrev(null);
            dst.data.parents.head = src.getNext();
        }
        else if (src.getPrev() != null && src.getNext() == null){
            // src is the tail
            src.getPrev().setNext(null);
            dst.data.parents.tail = src.getPrev();
        }
        else if (src.getPrev() != null && src.getNext() != null){
            src.getPrev().setNext(src.getNext());
            src.getNext().setPrev(src.getPrev());
        }
        else {
            dst.data.parents.head = null;
            dst.data.parents.tail = null;
        }

        if (dst.getPrev() == null && dst.getNext() != null){
            dst.getNext().setPrev(null);
            src.data.children.head = dst.getNext();
        }
        else if (dst.getPrev() != null && dst.getNext() == null){
            dst.getPrev().setNext(null);
            src.data.children.head = dst.getPrev();
        }
        else if (dst.getPrev() != null && dst.getNext() != null){
            dst.getPrev().setNext(dst.getNext());
            dst.getNext().setPrev(dst.getPrev());
        }
        else{
            src.data.children.head = null;
            src.data.children.tail = null;
        }
        src = null;
        dst = null;
        

        if(prev == null && next != null) {
            next.setPrev(null);
        }
        else if(prev != null && next == null) {
            prev.setNext(null);
        }
        else if(prev != null && next != null) {
            next.setPrev(prev);
            prev.setNext(next);
        }
    }

    public void setPrev(GraphEdge prev) {
        this.prev = prev;
    }

    public void setNext(GraphEdge next) {
        this.next = next;
    }

    public GraphEdge getNext() {
        return next;
    }

    public GraphEdge getPrev() {
        return prev;
    }

    @Override
    public void setNode(Node<GraphEdge> nd) {
        p_node = nd;
    }
}
