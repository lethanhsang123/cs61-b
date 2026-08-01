
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Solutions {
    
    public static void main(String[] args) {
        discussion01A();
        discussion01B();
        discussion01C();
        discussion01D();
        discussion01E();
    }

    public static void discussion01A() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = Discussion01A.evens(numbers);
        System.out.println("Even numbers: " + evenNumbers);
    }

    public static void discussion01B() {
        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
        Map<String, Integer> wordCount = Discussion01B.countWords(words);
        System.out.println("Word count: " + wordCount);
    }

    public static void discussion01C() {
        Discussion01C.Dog dog = new Discussion01C().new Dog("Buddy", 5);
        System.out.println("Before growing: " + dog);
        dog.grow();
        System.out.println("After growing: " + dog);
    }

    public static void discussion01D() {
        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9);
        int diff = Discussion01D.maxMinDiff(numbers);
        System.out.println("Max-Min difference: " + diff);
    }

    public static void discussion01E() {
        List<String> words = List.of("the", "cat", "sat", "on", "the", "mat");
        Map<String, List<String>> followers = Discussion01E.listOfFollowers(words);
        System.out.println("List of followers: " + followers);
    }
    
}

class Discussion01A {
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> result = new ArrayList<>();
        for (Integer x : L) {
            if (x % 2 == 0) {
                result.add(x);
            }
        }
        return result;
    }
}

class Discussion01B {
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> result = new java.util.HashMap<>();
        for (String word : words) {
            if (word.length() > 0) {
                if (result.containsKey(word)) {
                    result.put(word, result.get(word) + 1);
                } else {
                    result.put(word, 1);
                }
            }
        }
        return result;
    }
}

class Discussion01C {
    class Dog {
        private String name;
        private int size;

        public Dog(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public void grow() {
            this.size += 1;
        }

        @Override
        public String toString() {
            return "Dog{name='" + name + "', size=" + size + "}";
        }
    }
}

class Discussion01D {

    public static int maxMinDiff(List<Integer> L) {
        if (L == null || L.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        int max = L.get(0);
        int min = L.get(0);
        for (Integer x : L) {
            if (x > max) {
                max = x;
            }
            if (x < min) {
                min = x;
            }
        }
        return max - min;
    }
}

class Discussion01E {
    public static Map<String, List<String>> listOfFollowers(List<String> x) {
        Map<String, List<String>> result = new java.util.HashMap<>();
        for (int i = 0; i < x.size() - 1; i++) {
            String currentWord = x.get(i);
            String nextWord = x.get(i + 1);
            if (!result.containsKey(currentWord)) {
                result.put(currentWord, new ArrayList<>());
            }
            result.get(currentWord).add(nextWord);
        }
        return result;
    }
}