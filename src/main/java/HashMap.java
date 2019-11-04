import java.util.Objects;

public class HashMap<K, V> {
    private static final int DEFAULT_CAPASITY = 8;

    private int size;
    private Node<K, V>[] nodes;

    public HashMap() {
        this.nodes = new Node[DEFAULT_CAPASITY];
    }

    private class Node<K, V> {
        private K key;
        private V value;
        private int hash;
        private Node next;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    value.equals(node.value) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, hash, next);
        }
    }

    private int indexFor(int hash) {
        return hash & (DEFAULT_CAPASITY - 1);
    }

    private int hash(Object key) {
        if (key == null) return 0;
        return key.hashCode();
    }

    public V put(K key, V value) {
        if (key == null) return putForNullKey(value);
        int hash = hash(key);
        int index = indexFor(hash);
        if (nodes[index] == null) {
            addNodeToBucket(index, hash, key, value);
            return null;
        }

        Node<K, V> headNode = nodes[index];
        while (headNode != null) {
            if ((headNode.hash == hash) && (key.equals(headNode.key))) {
                V oldV = headNode.value;
                headNode.value = value;
                //this.size++;
                return oldV;
            }
            headNode = headNode.next;
        }
        addNodeToBucket(index, hash, key, value);
        return null;
    }

//    public int getSize() {
//        return size;
//    }

//    public void getNodes() {
//        for (Node<K, V> node : nodes) {
//            if (node != null)
//            System.out.println(node.key + " " + node.value);
//        }
//    }

    private void addNodeToBucket(int index, int hash, K key, V value) {
        Node<K, V> old = nodes[index];
        Node<K, V> newNode = new Node<>(key, value, hash);
        newNode.setNext(old);
        nodes[index] = newNode;
        this.size++;

    }

    private V putForNullKey(V value) {
        Node<K, V> headNode = nodes[0];
        while (headNode != null) {
            if (headNode.key == null) {
                V oldV = headNode.value;
                headNode.value = value;
                this.size++;
                return oldV;
            }
        }
        addNodeToBucket(0, 0, null, value);
        return null;
    }

    public V get(K key) {
        Node<K, V> node = getNode(hash(key), key);
        if (node != null) return node.value;
        return null;
    }

    private Node getNode(int hash, K key) {
        int index = indexFor(hash);
        Node node = nodes[index];
        while (node != null) {
            if ((node.hash == hash && key == node.key) || (key != null && key.equals(node.key))) return node;
            node = node.next;
        }
        return null;
    }

    /*private void remove(int hash, K key) {
        int index = indexFor(hash);
        if (nodes[index] == null) return;
        Node<K, V> headNode = nodes[index];
        Node<K, V> previousNode = null;
        while (headNode != null) {
            if (headNode.hash == hash && headNode.key.equals(key)) {
                if (previousNode != null) {
                    previousNode.next = headNode.next;
                } else {
                    nodes[index] = headNode.next;
                }
            }
            previousNode = headNode;
            headNode = headNode.next;
        }
        this.size--;
    }*/


}
