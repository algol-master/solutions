import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2307 {
    static List<List<Node>> graph = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static class Node {
        int index;
        int cost;
        boolean possible;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
            this.possible = true;
        }
    }
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n+1];
        int[] parents = new int[n+1];

        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
            parents[i] = i;
        }
        visited[1] = true;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to,cost));
            graph.get(to).add(new Node(from,cost));
        }
        // 최단 거리를 구했음
        int original = dijkstra(visited, parents);

        // 최단 거리의 경로를 구함
        findPath(n,parents);
        Collections.sort(path);
        int answer = 0;
        for(int i=0;i<path.size()-1;i++){
            int from = path.get(i);
            int to = path.get(i+1);
            for(int j=0;j<graph.get(from).size();j++){
                if(graph.get(from).get(j).index == to) {
                    graph.get(from).get(j).possible = false;
                    break;
                }
            }
            for(int j=0;j<=n;j++){
                visited[j] = false;
                parents[j] = j;
            }
            int after = dijkstra(visited, parents);
            answer = Math.max(answer, after - original);
            for(int j=0;j<graph.get(from).size();j++){
                if(graph.get(from).get(j).index == to) {
                    graph.get(from).get(j).possible = true;
                    break;
                }
            }
        }

        System.out.println(answer);
        // 경찰이 길을 막아야 함. 음.. 도둑이 최단 경로로 지나간 길을 막는게 합리적이지 않을까?
        // 예시에서는 길이 3가지가 존재하는데, 각각 1개씩 막아보면서 다익스트라를 사용해야 할지도?
        // 1 2 3 6 이 저장되니 6,3,2를 각각 possible = false 로 처리해서 다익스트라를 써야되나?
    }

    private static int dijkstra(boolean[] visited, int[] parents) {
        int[] dist = new int[n+1];
        for(int i=0;i<=n;i++){
            dist[i] = Integer.MAX_VALUE / 2;
        }
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);

        pq.offer(new Node(1,0));
        while(!pq.isEmpty()) {
            Node poll = pq.poll();

            if(dist[poll.index] < poll.cost) continue;
            for(int i=0;i<graph.get(poll.index).size();i++){
                Node node = graph.get(poll.index).get(i);
                if(visited[node.index] || !node.possible) continue;
                visited[node.index] = true;
                if(dist[node.index] > dist[poll.index] + node.cost) {
                    dist[node.index] = dist[poll.index] + node.cost;
                    pq.offer(new Node(node.index, dist[node.index]));
                    parents[node.index] = poll.index;
                }
            }
        }
        // 2번째에서 이거 6으로 잘 구하는데 왜 안됨? 시발
        return dist[n-1];
    }

    private static void findPath(int node,int[] parents) {
        path.add(node);
        if(node != 1) {
            findPath(parents[node],parents);
        }
    }
}
