import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Discussion02 {
    static class Planet {
        private int x;
        private int y;
        private int mass;

        public Planet(int x, int y, int mass) {
            this.x = x;
            this.y = y;
            this.mass = mass;
        }

        public int distanceTo(Planet other) {
            int dx = other.x - this.x;
            int dy = other.y - this.y;
            return (int) Math.sqrt(dx * dx + dy * dy);
        }

        public int totalMass(List<Planet> planets) {
            int total = this.mass;
            for (Planet p : planets) {
                total += p.mass;
            }
            return total;
        }
    }

    static List<String> common(List<String> list1, List<String> list2) {
        List<String> result = new ArrayList<>();
        for (String s : list1) {
            if (list2.contains(s) && !result.contains(s)) {
                result.add(s);
            }
        }
        return result;
    }

    static void capitalize(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (!s.isEmpty()) {
                list.set(i, s.toUpperCase());
            }
        }
    }

    static Map<Integer, List<Integer>> buildLessThanMap(List<Integer> numbers) {
        Map<Integer, List<Integer>> lessThanMap = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            int x = numbers.get(i);
            if (!lessThanMap.containsKey(x)) {
                lessThanMap.put(x, new ArrayList<>());
            }
            List<Integer> lessThanList = lessThanMap.get(x);
            for (int j = 0; j < numbers.size(); j++) {
                if (i != j && numbers.get(j) < x && !lessThanList.contains(numbers.get(j))) {
                    lessThanList.add(numbers.get(j));
                }
            }
        }
        return lessThanMap;
    }

    static int[] filterPositive(List<Integer> numbers) {
        return numbers.stream().filter(n -> n > 0).mapToInt(Integer::intValue).toArray();
    }

    static class Particle {    }

}
