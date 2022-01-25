public abstract class Nodeable<T> {
    Node<T> p_node;
    public abstract void setNode(Node<T> nd);

    public Node<T> getNode() {
        return p_node;
    }
}
