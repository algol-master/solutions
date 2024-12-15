package sleepyhoon.dp;

import java.io.*;

public class BJ9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int[][] dp = new int[a.length()+1][b.length()+1];

        for(int i=0;i<a.length();i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<b.length();i++){
            dp[0][i] = 0;
        }

        for(int i=1;i<=a.length();i++){
            for(int j=1;j<=b.length();j++){
                if(a.charAt(i-1) == b.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        System.out.println(dp[a.length()][b.length()]);
    }
}
