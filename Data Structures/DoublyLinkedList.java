import javax.management.RuntimeErrorException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        T data;
        Node <T> prev;
        Node <T> next;
        
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> traversal = head;
        while (traversal != null) {
            Node<T> next = traversal.next;
            traversal.prev = traversal.next = null;
            traversal.data = null;
            traversal = next;
        }
        head = tail = traversal = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T data) {
        addLast(data);
    }

    public void addFirst(T data) {
        if(isEmpty()) {
            head = tail = new Node<T>(data, null, null);
        }
        else {
            head.prev = new Node<T>(data, null, head);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T data) {
        if(isEmpty()) {
            head = tail = new Node<T>(data, null, null);
        }
        else {
            tail.next = new Node<T>(data, tail, null);
            tail = tail.next;
        }
        size++;
    }

    //Checks value of the first node
    public T peekFirst() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Peek an Empty List");
        }
        return head.data;
    }

    public T peekLast() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Peek an Empty List");
        }
        return tail.data;
    }

    public T removeFirst() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        T data = head.data;
        head = head.next;
        --size;
        if(isEmpty()) {
            tail = null;
        }
        else {
            head.prev = null;
        }
        return data;
    }

    public T removeLast() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        T data = tail.data;
        tail = tail.prev;
        --size;
        if(isEmpty()) {
            head = null;
        }
        else {
            tail.next = null;
        }
        return data;
    }

    private T remove(Node<T> node) {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        if(node.prev == null) {
            return removeFirst();
        }
        if(node.next == null) {
            return removeLast();
        }
        node.next.prev = node.prev;
        node.prev.next = node.next;
        T data = node.data;
        node.data = null;
        node = node.next = node.prev = null;
        --size;
        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        int i;
        Node<T> traversal;
        if(index < size/2) {
            for(i = 0, traversal = head; i != index; i++) {
                traversal = traversal.next;
            }
        }
        else {
            for(i = size - 1, traversal = tail; i != index; i--;) {
                traversal = traversal.prev;
            }
        }
        return remove(traversal);
    }

    public boolean remove(Object obj) {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        Node<T> traversal;
        if(obj == null) {
            for(traversal = head; traversal != null; traversal = traversal.next) {
                if(traversal.data == null) {
                    remove(traversal);
                    return true;
                }
            }
        }
        else {
            for(traversal = head; traversal != null; traversal = traversal.next) {
                if(obj.equals(traversal.data)) {
                    remove(traversal);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> traversal = head;
        if(obj == null) {
            for(traversal = head; traversal != null; traversal = traversal.next, index++) {
                if(traversal.data == null) {
                    return index;
                }
            }
        }
        else {
            for(traversal = head; traversal != null; traversal = traversal.next, index++) {
                if(obj.equals(traversal.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != 1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> traversal = head;
            
            @Override
            public boolean hasNext() {
                return traversal != null;
            }

            @Override
            public T next() {
                T data = traversal.data;
                traversal = traversal.next;
                return data;
            }
        };
    }

    @Override
    poublic String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        Node <T> traversal = head;
        while(traversal != null) {
            string.append(traversal.data + ", ");
            traversal = traversal.next;
        }
        string.append("]");
        return string.toString();
    }
}
