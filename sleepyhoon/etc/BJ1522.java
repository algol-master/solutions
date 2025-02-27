package sleepyhoon.etc;

import java.util.Scanner;

public class BJ1522 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();

        int count = 0;

        // a의 개수 구하기
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') {
                count++;
            }
        }

        int current = 0;
        for (int i = 0; i < count; i++) {
            if (input.charAt(i) == 'a') {
                current++;
            }
        }

        int max = current;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == 'a') {
                current--;
            }

            int next = (i + count - 1) % input.length();
            if (input.charAt(next) == 'a') {
                current++;
            }
            max = Math.max(max, current);
        }

        System.out.println(count - max);
    }
}
