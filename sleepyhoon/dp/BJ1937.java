package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1937 {
    static int[][] arr, dp;
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dfs(i, j);
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int x, int y) {
        if(dp[x][y] != -1) {
            return dp[x][y];
        } else {
            int max = 0;
            if (dp[x][y] == -1) {
                dp[x][y] = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || arr[x][y] >= arr[nx][ny]) {
                        continue;
                    }
                    max = Math.max(max, dfs(nx, ny));
                }
            }
            return dp[x][y] = max + 1;
        }
    }
}
