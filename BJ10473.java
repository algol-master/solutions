import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ10473 {
    static Node[] nodes;
    static double[] times;
    static List<List<Edge>> graph = new ArrayList<>();
    static int n;

    static class Node {
        double x;
        double y;
        int index;

        Node(int index, double x, double y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge {
        int target;
        double cost;

        Edge(int target, double cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double startX = Double.parseDouble(st.nextToken());
        double startY = Double.parseDouble(st.nextToken());

        st = new StringTokenizer(br.readLine());
        double endX = Double.parseDouble(st.nextToken());
        double endY = Double.parseDouble(st.nextToken());
        n = Integer.parseInt(br.readLine());
        times = new double[n + 2];
        Arrays.fill(times,Double.MAX_VALUE / 2);
        nodes = new Node[n + 2];
        nodes[0] = new Node(0, startX, startY);
        nodes[n + 1] = new Node(n + 1, endX, endY);

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            double cannonX = Double.parseDouble(st.nextToken());
            double cannonY = Double.parseDouble(st.nextToken());
            nodes[i] = new Node(i, cannonX, cannonY);
        }

        for (int i = 0; i < n + 2; i++) {
            graph.add(new ArrayList<>());

            for (int j = 0; j < n + 2; j++) {
                if (i == j) {
                    continue;
                }
                double dist = getDist(i, j);
                graph.get(i).add(new Edge(j, dist));
            }
        }
        dijkstra();
        System.out.println(times[n + 1]);
    }

    private static double getDist(int x, int y) {
        Node node1 = nodes[x];
        Node node2 = nodes[y];
        return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node1.y - node2.y, 2));
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> (int) (o1.cost - o2.cost));
        pq.offer(new Edge(0,  0));
        times[0] = 0;
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();

            if (times[poll.target] < poll.cost) {
                continue;
            }

            for (int i = 0; i < graph.get(poll.target).size(); i++) {
                Edge edge = graph.get(poll.target).get(i);

                // target 에서 대포를 사용할 수 있음
                if (poll.target > 0 && poll.target < n + 1) {
                    double timeValue;
                    if (edge.cost >= 50) {
                        timeValue = poll.cost + 2 + (edge.cost - 50) / 5;
                    } else {
                        timeValue = poll.cost + 2 + (50 - edge.cost) / 5;
                    }
                    if (times[edge.target] > timeValue) {
                        times[edge.target] = timeValue;
                        pq.offer(new Edge(edge.target, timeValue));
                    }
                }

                double walkTime = poll.cost + edge.cost / 5;
                if (times[edge.target] > walkTime) {
                    times[edge.target] = walkTime;
                    pq.offer(new Edge(edge.target, walkTime));
                }
            }
        }
    }
}
