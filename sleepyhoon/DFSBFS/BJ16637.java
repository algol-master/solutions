package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BJ16637 {
    static int answer = Integer.MIN_VALUE;
    static List<Integer> numbers = new ArrayList<>();
    static List<Character> operations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                numbers.add(Character.getNumericValue(input.charAt(i)));
            } else {
                operations.add(input.charAt(i));
            }
        }

        dfs(numbers.get(0), 0);
        System.out.println(answer);
    }


    private static void dfs(int value, int index) {
        if (index >= operations.size()) {
            answer = Math.max(answer, value);
        } else {
            int tmp = calculate(value, operations.get(index), numbers.get(index + 1));
            dfs(tmp, index + 1);

            if (index + 1 < operations.size()) {
                tmp = calculate(value, operations.get(index),
                        calculate(numbers.get(index + 1), operations.get(index + 1), numbers.get(index + 2)));
                dfs(tmp, index + 2);
            }
        }
    }

    private static int calculate(int first, char instruction, int second) {
        if (instruction == '+') {
            return first + second;
        }
        if (instruction == '-') {
            return first - second;
        } else {
            return first * second;
        }
    }
}
