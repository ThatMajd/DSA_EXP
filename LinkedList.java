public class LinkedList <T> {
    Node<T> head;
    Node<T> tail;
    int size;


    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public Node<T> insert(T obj){
        Node<T> node = new Node<>(obj);
        if(head == null){
            this.head = node;
            this.tail = this.head;
        }
        else {
            this.tail.setNext(node);
            node.setPrev(this.tail);
            this.tail = node;
        }
        size++;
        return node;
    }


    public Node<T> peek(){
        size--;
        Node<T> res;
        if (head.getNext() != null){
            res = head;
            Node<T> next = head.getNext();
            next.setPrev(null);
            head = next;
            return res;
        }
        res = head;
        head = null;
        tail = null;
        return res;
    }

    public void place_first(T obj){
        Node<T> node = new Node<>(obj);
        if (head == null){
            head = node;
            tail = head;
            return;
        }
        head.setPrev(node);
        node.setNext(head);
        head = node;
    }

    public void print(){
        Node<T> temp = head;
        while (temp != null && temp.data instanceof GraphNode ){
            System.out.println(((GraphNode) temp.data).getKey() + ", "+((GraphNode) temp.data).f_time);
            temp = temp.getNext();
        }
//        while (temp != null && temp.data instanceof GraphEdge ){
//            System.out.println(((GraphEdge) temp.data).src.data.getKey() + ", "+((GraphEdge) temp.data).dst.data.getKey());
//            temp = temp.getNext();
//        }
        while (temp != null && temp.data instanceof RootedTree ){
            System.out.println(((RootedTree) temp.data).root);
            temp = temp.getNext();
        }
        while (temp != null && temp.data instanceof Integer ){
            System.out.println((temp.data));
            temp = temp.getNext();
        }
    }
}
