package sleepyhoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753 {
    static class Node {
        int index;
        int cost;

        Node(int index,int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        dist = new int[v+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        dist[start] = 0;

        List<List<Node>> list = new ArrayList<>();
        for(int i=0;i<v+1;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to,cost));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()) {
            Node poll = pq.poll();

            if(dist[poll.index] < poll.cost) continue;

            for(int i=0;i<list.get(poll.index).size();i++){
                Node node = list.get(poll.index).get(i);

                if(dist[node.index] > node.cost + dist[poll.index]) {
                    dist[node.index] = node.cost + dist[poll.index];
                    pq.offer(new Node(node.index,dist[node.index]));
                }
            }
        }
        for(int i=1;i<dist.length;i++){
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }
            else {
                System.out.println(dist[i]);
            }
        }
    }
}
