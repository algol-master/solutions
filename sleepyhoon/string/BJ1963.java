package sleepyhoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1963 {
    static boolean[] prime = new boolean[10000];
    static boolean[] visited = new boolean[10000];

    static class Number {
        String number;
        int count;

        Number(String number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        initPrime();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            Arrays.fill(visited, false);
            int answer = bfs(from, to);
            if (answer == -1) {
                System.out.println("Impossible");
            } else {
                System.out.println(answer);
            }
        }
    }

    private static int bfs(int start, int target) {
        Queue<Number> q = new ArrayDeque<>();
        q.offer(new Number(String.valueOf(start), 0));
        visited[start] = true;
        int result = -1;

        while (!q.isEmpty()) {
            Number poll = q.poll();
            String number = poll.number;

            if (number.equals(String.valueOf(target))) {
                result = poll.count;
                break;
            }

            for (int i = 0; i < number.length(); i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (number.charAt(i) ==  j + '0') {
                        continue;
                    }
                    String string = number.substring(0, i) + j + number.substring(i + 1);
                    if (prime[Integer.parseInt(string)] && !visited[Integer.parseInt(string)]) {
                        q.offer(new Number(string, poll.count + 1));
                        visited[Integer.parseInt(string)] = true;
                    }
                }
            }
        }

        return result;
    }

    private static void initPrime() {
        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i < 100; i++) {
            if (prime[i]) {
                for (int j = i * i; j < 10000; j += i) {
                    prime[j] = false;
                }
            }
        }
    }
}
