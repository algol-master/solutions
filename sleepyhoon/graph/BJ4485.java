package sleepyhoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ4485 {
    static class Node {
        int x;
        int y;
        int cost;
        Node(int x,int y,int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n==0)
                break;
            int[][] arr = new int[n][n];
            int[][] dist = new int[n][n];
            for(int i=0;i<n;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE / 2;
                }
            }
            Queue<Node> q = new ArrayDeque<>();
            dist[0][0] = arr[0][0];
            q.offer(new Node(0,0,dist[0][0]));
            while(!q.isEmpty()) {
                Node poll = q.poll();
                for(int i=0;i<4;i++){
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];

                    if(nx<0||nx>=n||ny<0||ny>=n) continue;

                    if(dist[nx][ny] > poll.cost + arr[nx][ny]) {
                        dist[nx][ny] = poll.cost + arr[nx][ny];
                        q.offer(new Node(nx,ny,dist[nx][ny]));
                    }
                }
            }
            sb.append("Problem ").append(index++).append(": ").append(dist[n - 1][n - 1]).append("\n");
        }
        System.out.println(sb);
    }
}
