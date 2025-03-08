package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10942 {
    static int[] arr;
    static int[][] dp;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 길이 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 길이 2
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        // 길이 3 이상 (부분 수열의 길이는 2부터 시작)
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i < n - length; i++) {
                int j = i + length;
                if(arr[i] == arr[j] && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                }
            }
        }

        int loop = Integer.parseInt(br.readLine());
        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(dp[from-1][to-1] == 1) {
                result.append(1).append("\n");
            } else {
                result.append(0).append("\n");
            }
        }
        System.out.println(result);
    }
}
