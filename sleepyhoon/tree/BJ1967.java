package sleepyhoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1967 {
    static class Node {
        int index;
        int cost;

        Node(int index,int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n==1) {
            System.out.println(0);
            return;
        }

        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for(int i=0;i<n+1;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<n-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to,cost));
            list.get(to).add(new Node(from,cost));
        }
        int[] distance = bfs(list, n, 1);
        int max = 0;
        int maxIndex = 0;
        for(int i=1;i<distance.length;i++){
            if(max < distance[i]) {
                max = distance[i];
                maxIndex = i;
            }
        }
        int[] distance2 = bfs(list, n, maxIndex);
        for(int i=1;i<distance2.length;i++){
            if(max < distance2[i]) {
                max = distance2[i];
            }
        }
        System.out.println(max);
    }
    private static int[] bfs(ArrayList<ArrayList<Node>> tree, int n, int start) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist,-1);

        q.offer(new Node(start,0));
        visited[start] = true;
        dist[start] = 0;

        while(!q.isEmpty()) {
            Node poll = q.poll();

            for(int i=0;i<tree.get(poll.index).size();i++){
                Node node = tree.get(poll.index).get(i);
                if(visited[node.index]) continue;
                visited[node.index] = true;
                dist[node.index] = dist[poll.index] + node.cost;
                q.offer(new Node(node.index,dist[node.index]));
            }
        }

        return dist;
    }
}
