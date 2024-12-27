package sleepyhoon.dp;

import java.util.Scanner;

public class BJ10978 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i=0;i<n;i++){
            int input = sc.nextInt();
            long [] dp = new long[input+1];

            if (input == 1) {
                System.out.println(0);
            } else if (input == 2) {
                System.out.println(1);
            } else if (input == 3){
                System.out.println(2);
            } else {
                dp[0] = 0;
                dp[1] = 0;
                dp[2] = 1;
                for(int j=3;j<=input;j++){
                    dp[j] = (j-1) * (dp[j-1] + dp[j-2]);
                }
                System.out.println(dp[input]);
            }
        }
    }
}
