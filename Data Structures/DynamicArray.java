

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
    
    private T[] array;
    private int length = 0; //length user thinks the array is
    private int capacity = 0;   //actual amount of memory being reserved for array

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
          return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if(index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void set(int index, T item) {
        if(index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
    }

    public void clear() {
        for(int i = 0; i < length; i++) {
            array[i] = null;
        }
        length = 0;
    }

    public void add(T item) {
        if(length + 1 >= capacity) {
            if(capacity == 0) {
                capacity = 1;
            }
            else {
                capacity *= 2;
            }
            T[] newArray = (T[]) new Object[capacity];
            for(int i = 0; i < length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[length++] = item;
    }

    public T removeAt(int index) {
        if(index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T item = array[index];
        T[] newArray = (T[]) new Object[length-1];
        for(int i = 0, j = 0; i < length; i++, j++) {
            if(i == index) {
                j--;
            }
            else {
                newArray[j] = array[i];
            }
        }
        array = newArray;
        capacity = --length;
        return item;
    }

    public int indexOf(Object obj) {
        for(int i = 0; i < length; i++) {
            if(obj == null) {
                if(array[i] == null) {
                    return i;
                }
            }
            else {
                if(obj.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if(index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return array[index++];
            }

            //Ensures that code does not allow for concurrent modification of the array
            @Override public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override 
    public String toString() {
        if(length == 0) {
            return "[]";
        }
        else {
            StringBuilder string = new StringBuilder(length).append("[");
            for(int i = 0; i < length-1; i++) {
                string.append(array[i] + ", ");
            }
            return string.append(array[length-1] + "]").toString();
        }
    }

    public static void main(String[] args) {
        DynamicArray<Integer> intArray = new DynamicArray();
        DynamicArray<String> stringArray = new DynamicArray(3);
        intArray.add(1);
        intArray.add(6);
        intArray.add(4);
        stringArray.add("red");
        stringArray.add("yellow");
        stringArray.add("green");
        System.out.println(intArray);
        System.out.println(stringArray);
        intArray.remove(1);
        stringArray.removeAt(2);
        System.out.println(stringArray.contains("green"));
        System.out.println(intArray);
        System.out.println(stringArray);
    }
}


