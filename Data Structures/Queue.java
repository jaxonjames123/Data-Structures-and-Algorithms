package src;

public class Queue<T> implements Iterable<T> {
    private LinkedList<T> list = new LinkedList<T>();

    public Queue() {}

    public Queue(T firstElem) {
        offer(firstElem);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T peek() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot peek empty queue");
        }
        return list.peekFirst();
    }

    public T poll() {
        if(isEmpty()) {
            throw new RuntimeException("Cannot poll empty queue");
        }
        return list.removeFirst();
    }

    public void offer(T elem) {
        list.addLast(elem);
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
