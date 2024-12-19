import java.util.Scanner;

public class BJ10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[][] dp = new long[n+1][10];

        if(n==1) {
            System.out.println(9);
            return;
        }

        // 초기값
        for(int i=1;i<10;i++){
            dp[1][i] = 1;
            dp[2][i] = 2;
        }
        dp[2][9] = 1;

        for(int i=3;i<n+1;i++){
            for(int j=1;j<10;j++){
                if(j==1)
                    dp[i][1] = (dp[i-2][1] + dp[i-1][2])%1000000000;
                else if(j==9)
                    dp[i][9] = dp[i-1][8];
                else
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%1000000000;
            }
        }
        long sum = 0;
        for(int i=1;i<10;i++){
            sum = (sum + dp[n][i])%1000000000;
        }
        System.out.println(sum);
    }
}
