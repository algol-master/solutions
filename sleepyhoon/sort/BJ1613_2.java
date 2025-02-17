package sleepyhoon.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1613_2 {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] arr;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        arr = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            arr[to]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int value = q.poll();
            answer.add(value);
            for (int i = 0; i < graph.get(value).size(); i++) {
                Integer i1 = graph.get(value).get(i);
                arr[i1]--;
                if (arr[i1] == 0) {
                    q.offer(i1);
                }
            }
        }

        // reachable[a][b] = true 이면 a->b 순이라는 의미임.
        boolean[][] reacheable = new boolean[n + 1][n + 1];

        for (int i = answer.size() - 1; i >= 0; i--) {
            Integer i1 = answer.get(i);

            reacheable[i1][i1] = true;

            for (int value : graph.get(i1)) {
                for (int j = 1; j <= n; j++) {
                    if (reacheable[value][j]) {
                        reacheable[i1][j] = true;
                    }
                }
            }
        }

        int loop = Integer.parseInt(br.readLine());
        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (reacheable[from][to]) {
                sb.append(-1).append("\n");
            } else if (reacheable[to][from]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }
}
