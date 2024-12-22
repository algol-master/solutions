package sleepyhoon.dp;

import java.util.Scanner;

public class BJ2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        dp[0] = arr[0];
        if(n>1)
            dp[1] = arr[0] + arr[1];

        for(int i=2;i<n;i++){
            if(i==2)
                dp[2] = Math.max(dp[1],Math.max(arr[0]+arr[2],arr[1]+arr[2]));
            else
                dp[i] = Math.max(dp[i-1],Math.max(dp[i-3]+arr[i-1]+arr[i],dp[i-2]+arr[i]));
        }
        System.out.println(dp[n-1]);
    }
}
