public class Node<T>{
    private Node<T> prev;
    private Node<T> next;
    public T data;


    public Node(T data){
        this.data = data;
        prev = null;
        next = null;
        if (data instanceof GraphNode){
            ((GraphNode)data).setNode((Node<GraphNode>) this);
        }
    }


    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void delete(){
        if(prev == null && next != null) {
            next.setPrev(null);
        }
        else if(prev != null && next == null) {
            prev.setNext(null);
        }
        else if(prev != null && next != null){
            prev.setNext(next);
            next.setPrev(prev);
        }
    }

    @Override
    public String toString(){
        return data.toString();
    }

}
