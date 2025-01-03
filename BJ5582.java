import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5582 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] dp = new int[a.length+1][b.length+1];

        for(int i=0;i<a.length;i++){
            dp[i][0] = 0;
        }

        for(int i=0;i<b.length;i++){
            dp[0][i] = 0;
        }
        int max = 0;
        for(int i=1;i<a.length+1;i++){
            for(int j=1;j<b.length+1;j++){
                if(a[i-1] == b[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else
                    dp[i][j] = 0;
                max = Math.max(max,dp[i][j]);
            }
        }

        System.out.println(max);
    }
}
