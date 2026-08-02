
class DoubleUp {
    public static void main(String[] args) {
        String doubleValue = doubleUp("Hello, World!");
        System.out.println(doubleValue);
    }

    /**
     * Returns a new string where each character of the given string is repeated
     * twice.
     * Example: doubleUp("hello") -> "hheelllloo"
     */
    public static String doubleUp(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c).append(c);
        }
        return sb.toString();
    }
}
