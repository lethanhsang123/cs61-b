public interface DisjointSets<E> {


    /**
     * Conencts two items.
     */
    void connect(E p, E q);

    /**
     *  Check to see if two items are connected.  
     */
    boolean isConnected(E p, E q);

    
}

class DSListOfSet {
    //TODO: Implements 
}

class DSQuickFind implements DisjointSets<Integer> {
    
    private Integer[] ds;

    public DSQuickFind(int n) {
        ds = new Integer[n];
        for (int i = 0; i < n; i++) {
            ds[i] = i;
        }
    }


    public void connect(Integer p, Integer q) {
        if (p < 0 || q < 0 || p >= ds.length || q >= ds.length) {
            // TODO: Throw Illegal...
        }
        int pID = ds[p];
        int qID = ds[q];
        int newID = Math.max(pID, qID);
        for (int i = 0; i < ds.length; i++) {
            if (ds[i] == pID || ds[i] == qID) {
                ds[i] = newID;
            }
        }
    }

    public boolean isConnected(Integer p, Integer q) {
       return (p >= 0 && q >= 0 && p < ds.length && q < ds.length) && (ds[p] == ds[q]);
    }

}

class DSQuickUnion implements DisjointSets<Integer> {

    /**
     * Array store disjoint set; index is value of node, value is root of node. Root node value is -(depth of tree).
     */
    private Integer[] ds;

    public DSQuickUnion(int n) {
        ds = new Integer[n];
        for (int i = 0; i < n; i++) {   
            ds[i] = -1;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= ds.length) {
            // TODO: Throws Illegal exception
        }
        int root = ds[p];
        if (root < 0) return p;
        else return find(root);
    } 

    
    @Override
    public void connect(Integer p, Integer q) {
        if (p < 0 || p >= ds.length || q < 0 || q >= ds.length) {
            // TODO: Throws Illegal Exception
        }

        // Get root node
        int pRoot = find(p);
        int qRoot = find(q);

        // Check same root
        if (pRoot == qRoot) return;
        
        // Compare depth of tree (value is negative: -(depth tree))
        if (ds[pRoot] <= ds[qRoot]) { // tree p size >= tree q size
            ds[pRoot] = ds[pRoot] + ds[qRoot]; // Increase size
            ds[qRoot] = pRoot;  // Attach q to p / q is child of p 
        } else {
            ds[qRoot] = ds[qRoot] + ds[pRoot]; // Increase size
            ds[pRoot] = qRoot;  // Attach q to p / q is child of p 
        }
    }

    @Override 
    public boolean isConnected(Integer p, Integer q) {
        return find(p) == find(q);
    }

}



