package src;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;

public class BinaryHeap<T extends Comparable<T>> {
    // Eelements currently in heap 
    private int heapSize = 0;

    // Internal capacity of heap
    private int heapCapacity = 0;

    // List to track the elements inside the heap
    private List<T> heap = null;

    // Map to keep track of possible indices a node value is found in the heap
    // O(log(n)) removals, O(1) element containment check
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public BinaryHeap() {
        this(1);
    }

    public BinaryHeap(int size) {
        heap = new ArrayList<>(size);
    }
    // Constructt a priority queue using heapify (O(n))
    public BinaryHeap(T[] elems) {
        heapSize = heapCapacity = elems.length;
        heap = new ArrayList<T>(heapCapacity);

        for(int i = 0; i < heapSize; i++) {
            mapAdd(elems[i], i);
            heap.add(elems[i]);
        }
        for(int i = Math.max(0, (heapSize/2) - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    // O(nlog(n))
    public BinaryHeap(Collection <T> elems) {
        this(elems.size());
        for(T elem : elems) {
            add(elem);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for(int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
        }
        heapSize = 0;
        map.clear();
    }

    public int size() {
        return heapSize;
    }

    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem) {
        if(elem == null) {
            return false;
        }
        return map.containsKey(elem);
    }

    public void add(T elem) {
        if(elem == null) {
            throw new IllegalArgumentException();
        }

        if(heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        }
        else {
            heap.add(elem);
            heapCapacity++;
        }
        mapAdd(elem, heapSize);
        bubbleUp(heapSize);
        heapSize++;
    }

    // Test if value of node i <= node j
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    public void bubbleUp(int k) {
        // Index of the next parent node with respect to k
        int parent = (k-1)/2;
        // Keep bubbling up if we havent reached the root, and we are still < parent
        while(k > 0 && less(k, parent)) {
            swap(parent, k);
            k = parent;
            parent = (k-1)/2;
        }
    }

    public void bubbleDown(int k) {
        while(true) {
            int left = 2*k + 1; // left node
            int right = 2 * k + 2;  // right node
            int smallest = left; // set default smallest for equal nodes to left node
            if( right < heapSize && less(right, left)) {
                smallest = right;
            }

            if(left >= heapSize || less(k, smallest)) {
                break;
            }
            swap(smallest, k);
            k = smallest;
        }
    }

    private void swap(int i, int j) {
        T iValue = heap.get(i);
        T jValue = heap.get(j);
        heap.set(i, jValue);
        heap.set(j, iValue);
        mapSwap(iValue, jValue, i, j);
    }

    public boolean remove(T elem) {
        if(element == null) {
            return false;
        }
        Integer index = mapGet(elem);
        if(index != null) {
            removeAt(index);
        }
        return index != null;
    }

    private T removeAt(int i) {
        if(isEmpty()) {
            return null;
        }
        heapSize--;
        T removedData = heap.get(i);
        swap(i, heapSize);
        if(i == heapSize) {
            return removedData;
        }
        T elem = heap.get(i);
        bubbleDown(i);
        if(heap.get(i).equals(elem)) {
            bubbleUp(i);
        }
        return removedData;
    }
    // Test method to check if heap invariant is still being satisfied
    // Call method with k = 0 to start at root
    public boolean isMinHeap(int k) {
        if(k >= heapSize) {
            return true;
        }
        int left = 2*k + 1;
        int right = 2*k + 2;
        if(left < heapSize && !less(k, left)) {
            return false;
        }
        if(right < heapSize && !less(k, right)) {
            return false;
        }

        return isMinHeap(left) && isMinHeap(right);
    }

    private void mapAdd(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        if(set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
        }
        else {
            set.add(index);
        }
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index);
        if(set.size() == 0) {
            map.remove(value);        }
    }

    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if(set != null) {
            return set.last();
        }
        return null;
    }

    private void mapSwap(T val1, val2, int val1Index, int val2Index) {
        Set<Integer> set1 = map.get(val1);
        Set<integer> set2 = map.get(val2);
        set1.remove(val1Index);
        set2.remove(val2Index);
        set1.add(val2Index);
        set2.add(val1Index);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
