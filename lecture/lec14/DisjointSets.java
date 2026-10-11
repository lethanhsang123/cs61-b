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



