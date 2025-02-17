package sleepyhoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1613 {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            dist[from][to] = 1;
            graph.get(from).add(to);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i<= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int loop = Integer.parseInt(br.readLine());
        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (dist[from][to] != Integer.MAX_VALUE / 2) {
                sb.append(-1).append("\n");
            } else if (dist[from][to] == Integer.MAX_VALUE / 2 && dist[to][from] != Integer.MAX_VALUE / 2) {
                sb.append(1).append("\n");
            } else if (dist[from][to] == Integer.MAX_VALUE / 2 && dist[to][from] == Integer.MAX_VALUE / 2) {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }
}
