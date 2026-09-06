import java.lang.Comparable;
import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    
    private String name;
    private int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override 
    public int compareTo(Dog dog) {
        return this.size - dog.size;
    }

    public static class NameComparator implements Comparator<Dog> {

        public int compare(Dog var1, Dog var2) {
            return var1.name.compareTo(var2.name);
        }

    }

}
