package sleepyhoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][2];
        // 최대 이익을 저장
        int[] dp = new int[n+2];
        for(int i=1;i<n+1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken()); // 걸리는 시간
            arr[i][1] = Integer.parseInt(st.nextToken()); // 받는 금액
        }

        for(int i=n;i>=1;i--){
            if(i + arr[i][0] - 1 > n) {
                dp[i] = dp[i+1];
                continue;
            }
            dp[i] = Math.max(dp[i+1],arr[i][1] + dp[i + arr[i][0]]);
        }
        System.out.println(dp[1]);
    }
}
