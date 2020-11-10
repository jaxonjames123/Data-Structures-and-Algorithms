package src;

public class LinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private class Node<T> {
        T data;
        Node<T> next;    

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "custom LL" + data.toString();
        }
    }

    public void clear() {
        Node<T> trav = head;
        while(trav != null) {
            Node<T> next = trav.next;
            trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = trav = null;
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
            head = tail = new Node<T>(data, null);
        }
        else {
            Node<T> oldHead;
            oldHead = head;
            head = new Node<T>(data, oldHead);
        }
        size++;
    }

    public void addLast(T data) {
        if(isEmpty()) {
            head = tail = new Node<T>(data, null);
        }
        else {
            tail.next = new Node<T>(data, null);
            tail = tail.next;
        }
        size++;
    }

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
        return data;
    }

    public T removeLast() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        Node<T> trav = head;
        while(trav.next != tail) {
            trav = trav.next;
        }
        T data = tail.data;
        tail = trav;
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
        if(node == head) {
            return removeFirst();
        }
        if(node.next == null) {
            return removeLast();
        }
        Node<T> curr = head;
        Node<T> prev = null;
        while(curr != null && curr != node) {
            prev = curr;
            curr = curr.next;
        }
        if(curr == null) {
            throw new RuntimeException("Node does not exist");
        }
        T data = node.data;
        prev.next = curr.next;
        node.data = null;
        node = node.next = null;
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
        Node<T> trav;
        for(i = 0, trav = head; i != index && trav != tail; i++) {
            trav = trav.next;
        }
        return remove(trav);
    }

    public boolean remove(Object obj) {
        if(isEmpty()) {
            throw new RuntimeException("Cannot Remove from Empty List");
        }
        Node<T> trav;
        if(obj == null) {
            for(trav = head; trav != null; trav = trav.next) {
                if(trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        }
        else {
            for(trav = head; trav != null; trav = trav.next) {
                if(obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;
        if(obj == null) {
            for(trav = head; trav != null; trav = trav.next, index++) {
                if(trav.data == null) {
                    return index;
                }
            }
        }
        else {
            for(trav = head; trav != null; trav = trav.next, index++) {
                if(obj.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> trav = head;
            
            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        Node <T> trav = head;
        while(trav != null) {
            string.append(trav.data);
            if(trav.next != null) {    
                string.append(", ");
            }
            trav = trav.next;
        }
        string.append("]");
        return string.toString();
    }
}
