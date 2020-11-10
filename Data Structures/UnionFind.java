package src;

public class UnionFind {
    // Number of elements in this union find
    private int size;
    // Track sizes of each of the components
    private int[] compSize;
    // id[i] points to the parent of i, if id[i] = i, i is a root node
    private int[] id;
    // Number of components in union find
    private int numComponents;

    public UnionFind(int size) {
        if(size <= 0) {
            throw new IllegalArgumentException("Size less than or equal to 0 is not allowed");
        }
        this.size = numComponents = size;
        compSize = new int[size];
        id = new int[size];
        for(int i = 0; i < size; i++) {
            id[i] = i;
            compSize[i] = 1;
        }
    }

    // Finds the root for a given node
    public int find(int p) {
        int root = p;
        while(root != id[root]) {
            root = id[root];
        }
        while(p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    // Checks if two compenents share the same root
    public boolean connected(int p, int q) {
        return find(p) == 0;
    }

    public int componentSize(int p) {
        return compSize[find(p)];
    }

    public int size() {
        return size;
    }

    public int components() {
        return numComponents;
    }

    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);
        if(root1 == root2) {
            return;
        }
        if(compSize[root1] <compSize[root2]) {
            compSize[root2] += compSize[root1];
            id[root1] = root2;
        }
        else {
            compSize[root1] += compSize[root2];
            id[root2] = root1;
        }
        numComponents--;
    }
}
