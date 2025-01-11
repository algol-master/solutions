package sleepyhoon.dp;

public class PG43105 {
        public int solution(int[][] triangle) {

            int height = triangle.length;
            int[][] dp = new int[height][height];

            dp[0][0] = triangle[0][0];

            if(height==1) {
                return dp[0][0];
            }

            // 기본값 초기화
            for(int i=1;i<height;i++){
                dp[i][0] = dp[i-1][0] + triangle[i][0];
                dp[i][i] = dp[i-1][i-1] + triangle[i][i];
            }

            int max = 0;
            for(int i=1;i<height;i++){
                for(int j=1;j<=i;j++){
                    if(i==j) continue;
                    dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j]) + triangle[i][j];
                }
            }

            for(int i=0;i<height;i++){
                max = Math.max(dp[height-1][i],max);
            }

            return max;
        }
}
