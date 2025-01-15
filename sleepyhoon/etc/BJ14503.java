package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503 {
    static int n, m;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(startX, startY, direction);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int direction) {
        arr[x][y] = 2;
        for (int i = 0; i < 4; i++) {
            direction = changeDirection(direction);
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == 0) {
                answer++;
                dfs(nx, ny, direction);
                return;
            }
        }

        int nx = x - dx[direction];
        int ny = y - dy[direction];

        if (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] != 1) {
            dfs(nx, ny, direction);
        }
    }

    private static int changeDirection(int direction) {
        if (direction == 0) {
            return 3;
        } else {
            return direction - 1;
        }
    }
}
