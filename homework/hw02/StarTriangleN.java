
class StarTriangleN {

    public static void main(String[] args) {
        printStarTriangle(5);
    }

    public static void printStarTriangle(int n) { 
        for (int i = 1; i <= n; i++) {
            StringBuilder spaces = new StringBuilder();
            for (int j = 1; j <= i; j++) {
                spaces.append("*");
            }
            System.out.println(spaces.toString());
        }
    }

}