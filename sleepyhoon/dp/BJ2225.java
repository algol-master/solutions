package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2225 {
    static int n, k;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        k = Integer.parseInt(split[1]);

        // i개를 사용해서 j를 나타낼 수 있는 경우의 수
        dp = new int[k + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= k; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }
        System.out.println(dp[k][n]);
    }
}
