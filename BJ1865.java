import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1865 {
    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int n, m, w;
    static List<Edge> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        // 음수 가중치가 있어서 다익스트라 사용 못함. 벨만포드 알고리즘을 사용해서 음의 사이클이 있는지 확인해야함.
        while (tc-- > 0) {
            graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // 정상 도로 입력
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.add(new Edge(from, to, cost));
                graph.add(new Edge(to, from, cost));
            }

            // 웜홀 입력
            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.add(new Edge(from, to, cost * -1));
            }

            if (BellmanFord()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean BellmanFord() {
        int[] dist = new int[n + 1];
        dist[1] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (Edge edge : graph) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                    if (i == n) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
