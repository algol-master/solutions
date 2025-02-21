package sleepyhoon.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class BJ1339 {
    static class Alphabet {
        char c;
        int w;

        Alphabet(char c, int w) {
            this.c = c;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<String> list = new ArrayList<>();
        List<Alphabet> arr = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> weights = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String input = sc.next();
            for (int j = 0; j < input.length(); j++) {
                char key = input.charAt(j);
                int pow = (int) Math.pow(10, input.length() - 1 - j);
                map.put(key, map.getOrDefault(key, 0) + pow);
            }
            list.add(input);
        }

        for (Entry<Character, Integer> entry : map.entrySet()) {
            arr.add(new Alphabet(entry.getKey(), entry.getValue()));
        }

        Collections.sort(arr, (o1, o2) -> o2.w - o1.w);
        int value = 9;
        for (Alphabet a : arr) {
            weights.put(a.c, value--);
        }

        int sum = 0;

        for (String s : list) {
            for (int i = 0; i < s.length(); i++) {
                sum += weights.get(s.charAt(i)) * (int) Math.pow(10, s.length() - 1 - i);
            }
        }
        System.out.println(sum);
    }
}
