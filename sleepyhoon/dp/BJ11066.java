package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11066 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            int[] arr = new int[num+1];
            int[][] dp = new int[num+1][num+1];
            int[] sum = new int[num+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= num; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                if (j != 0) {
                    sum[j] = sum[j - 1] + arr[j];
                }
            }

            // 몇장으로 묶을건지
            for (int j = 1; j <= num; j++) {
                // 어디서 시작할건지
                for (int from = 1; from + j <= num; from++) {
                    int to = j + from;
                    // from -> to 로 진행하면서 가능한 가짓수를 셀 것이다.
                    dp[from][to] = Integer.MAX_VALUE;
                    // from -> to 에서 어떤 것을 기준으로 묶을 것인지?
                    for (int l = from; l < to; l++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][l] + dp[l+1][to] + sum[to] - sum[from -1]);
                    }
                }
            }
            sb.append(dp[1][num]).append("\n");
        }
        System.out.println(sb);
    }
}
