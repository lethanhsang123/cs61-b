public interface List61B<Item> {

    public void addFirst(Item item);
    public void addLast(Item item);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();

    public default void print() {
        for (int i = 0; i < size(); i++) {
            IO.println(get(i));
        }
    }
    
}
