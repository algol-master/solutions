import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1939 {
    static class Node {
        int index;
        int cost;

        Node(int to, int cost) {
            this.index = to;
            this.cost = cost;
        }
    }

    static List<List<Node>> graph = new ArrayList<>();
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }
        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int low = 1;
        int high = 1000_000_001;

        while (low < high) {
            int mid = (low + high) / 2;
            if (canCross(mid, start, end)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        System.out.println(low-1);
    }

    private static boolean canCross(int mid, int start, int end) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        q.offer(new Node(start, 0));
        visited[start] = true;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.index == end) {
                return true;
            }

            for (int i = 0; i < graph.get(poll.index).size(); i++) {
                Node node = graph.get(poll.index).get(i);
                if (visited[node.index] || node.cost < mid) {
                    continue;
                }
                visited[node.index] = true;
                q.offer(new Node(node.index, mid));
            }
        }

        return false;
    }

}
