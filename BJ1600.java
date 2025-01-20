import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1600 {
    static int[][] arr;
    static boolean[][][] visited;
    static int[][] hdx = {{1, -1}, {2, -2}, {2, -2}, {1, -1}};
    static int[] hdy = {2, 1, -1, -2};

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Point {
        int x;
        int y;
        int count; // 말 이동 횟수
        int moving; // 말 + 일반 이동 횟수

        Point(int x, int y, int count, int moving) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.moving = moving;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 말처럼 이동할 수 있는 횟수
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[m][n];
        visited = new boolean[m][n][k+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0, 0));
        visited[0][0][0] = true;

        int answer = -1;
        while (!q.isEmpty()) {
            Point poll = q.poll();
            if (poll.x == m - 1 && poll.y == n - 1) {
                answer = poll.moving;
                break;
            }
            if (poll.count < k) {
                for (int i = 0; i < 4; i++) {
                    int ny = poll.y + hdy[i];
                    if (ny < 0 || ny >= n) {
                        continue;
                    }
                    for (int j = 0; j < 2; j++) {
                        int nx = poll.x + hdx[i][j];

                        if (nx < 0 || nx >= m || visited[nx][ny][poll.count+1] || arr[nx][ny] == 1) {
                            continue;
                        }
                        visited[nx][ny][poll.count+1] = true;
                        q.offer(new Point(nx, ny, poll.count + 1, poll.moving + 1));
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny][poll.count] || arr[nx][ny] == 1) {
                    continue;
                }
                visited[nx][ny][poll.count] = true;
                q.offer(new Point(nx, ny, poll.count, poll.moving + 1));
            }
        }
        System.out.println(answer);
    }
}
