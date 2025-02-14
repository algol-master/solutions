package sleepyhoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2493 {
    static class Tower {
        int index;
        int height;

        Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Tower[] towers = new Tower[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            towers[i] = new Tower(i + 1, height);
        }
        StringBuilder sb = new StringBuilder();
        Stack<Tower> stack = new Stack<>();
        int[] answer = new int[n + 1];
        stack.push(towers[0]);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().height < towers[i].height) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                answer[towers[i].index] = stack.peek().index;
            }
            stack.push(towers[i]);
        }

        for (int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}
