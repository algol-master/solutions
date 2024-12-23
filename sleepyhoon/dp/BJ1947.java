package sleepyhoon.dp;

import java.util.Scanner;

public class BJ1947 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long [] dp = new long [n+1];
        dp[0] = 0;
        dp[1] = 0;
        if (n == 1) {
            System.out.println(0);
        } else {
            dp[2] = 1;
            for(int i=3;i<n+1;i++){
                dp[i] = ((i-1) * (dp[i-1] + dp[i-2])) % 1000000000;
            }
            System.out.println(dp[n]);
        }
    }
}
