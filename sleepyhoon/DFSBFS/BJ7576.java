package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ7576 {
    static int[][] box;
    static int n,m;
    static boolean[][] visited;
    static Queue<Point> q = new ArrayDeque<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer;
    static class Point {
        int x;
        int y;
        int day;

        Point(int x, int y,int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) {
                    q.offer(new Point(i,j,0));
                    visited[i][j] = true;
                }
            }
        }
        bfs();
        if (check()) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }

    }
    private static void bfs() {
        while(!q.isEmpty()) {
            Point poll = q.poll();
            for(int i=0;i<4;i++){
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if(nx<0||nx>=n||ny<0||ny>=m || visited[nx][ny] || box[nx][ny] == -1 ) continue;

                if(box[nx][ny] == 0) {
                    answer = Math.max(answer,poll.day+1);
                    box[nx][ny] = 1;
                    q.offer(new Point(nx,ny,poll.day+1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean check() {
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(box[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
}
