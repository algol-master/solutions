import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ10473 {
    static List<Node> nodes = new ArrayList<>();
    static int n;

    static class Node {
        float x;
        float y;
        float cost;

        Node(float x, float y, float cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        float startX = Float.parseFloat(st.nextToken());
        float startY = Float.parseFloat(st.nextToken());

        st = new StringTokenizer(br.readLine());
        float endX = Float.parseFloat(st.nextToken());
        float endY = Float.parseFloat(st.nextToken());

        n = Integer.parseInt(br.readLine());
        nodes.add(new Node(startX,startY,0));
        nodes[1][0] = endX;
        nodes[1][1] = endY;

        for (int i = 2; i < n + 2; i++) {
            st = new StringTokenizer(br.readLine());
            float cannonX = Float.parseFloat(st.nextToken());
            float cannonY = Float.parseFloat(st.nextToken());
            nodes[i][0] = cannonX;
            nodes[i][1] = cannonY;
        }

        dijkstra();
    }

    private static int dijkstra() {
        float[] dist = new float[n + 2];
        boolean[] visited = new boolean[n + 2];
        Arrays.fill(dist, 100000);
        Arrays.fill(visited, false);
        Queue<float[]> q = new ArrayDeque<>();
        q.offer(nodes[0]);
        dist[0] = 0;
        visited[0] = true;
        while (!q.isEmpty()) {
            float[] poll = q.poll();
            float x = poll[0];
            float y = poll[1];

            for (int i = 0; i < n + 2; i++) {
                if (visited[i]) {
                    continue;
                }

                if (dist[])
            }
        }
    }
}
