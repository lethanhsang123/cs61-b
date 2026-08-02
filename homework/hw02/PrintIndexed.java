
class PrintIndexed {
    public static void main(String[] args) {
        printIndexed("Hello, World!");
    }

    public static void printIndexed(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append(i);
        }
        System.out.println(sb.toString());
    }
}
