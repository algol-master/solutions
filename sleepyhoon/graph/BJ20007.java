package sleepyhoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ20007 {
    static long[] dist;
    static List<List<Node>> graph = new ArrayList<>();

    static class Node {
        int index;
        long cost;

        Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        dist = new long[n];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[y] = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
            graph.get(to).add(new Node(from, cost));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> Math.toIntExact(o1.cost - o2.cost)));
        pq.offer(new Node(y, 0));
        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (poll.cost > dist[poll.index]) {
                continue;
            }

            for (int i = 0; i < graph.get(poll.index).size(); i++) {
                Node node = graph.get(poll.index).get(i);

                if (dist[node.index] > dist[poll.index] + node.cost) {
                    dist[node.index] = dist[poll.index] + node.cost;
                    pq.offer(new Node(node.index, dist[node.index]));
                }
            }
        }
        Arrays.sort(dist);
        int day = 1;
        long rest = x;
        for(long value : dist) {
            if(value == Integer.MAX_VALUE/2 || 2*value > x){
                System.out.println(-1);
                return;
            }
            if(rest >= 2*value) {
                rest -= 2*value;
            } else {
                rest = x;
                rest -= 2*value;
                day++;
            }
        }
        System.out.println(day);
    }
}
