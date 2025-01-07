import java.util.Scanner;

public class BJ2240 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int w = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[t + 1];
        int[][] dp = new int[t + 1][w + 1];

        for(int i=1;i<=t;i++){
            arr[i] = sc.nextInt();
        }

        int position = 1;
        int answer = 0;
        for(int i=1;i<=t;i++){
            int currentTree = arr[i];
            for(int j=0;j<=w;j++){
                if(j==0) {
                    position = 1;
                    if (currentTree == position) {
                        dp[i][j] = dp[i - 1][j] + 1;
                    }
                    else
                        dp[i][j] = dp[i-1][j];
                }
                else {
                    if(j%2==0) {
                        position = 1;
                    }
                    else {
                        position = 2;
                    }
                    if(currentTree == position) {
                        dp[i][j] = Math.max(dp[i-1][j]+1,dp[i-1][j-1]);
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1]+1);
                    }
                }
                answer = Math.max(answer,dp[i][j]);
            }
        }
        System.out.println(answer);
    }
}
