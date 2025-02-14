package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17086 {
    static int[][] arr;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int n, m;

    static class Point {
        int x, y, cost;

        Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxDistance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) { // 상어가 없는 칸에서 BFS 시작
                    maxDistance = Math.max(maxDistance, bfs(i, j));
                }
            }
        }

        System.out.println(maxDistance);
    }

    private static int bfs(int startX, int startY) {
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        q.offer(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    if (arr[nx][ny] == 1) {
                        return p.cost + 1; // 상어를 만나면 즉시 거리 반환
                    }
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny, p.cost + 1));
                }
            }
        }
        return -1; // 이론상 도달 불가능
    }
}