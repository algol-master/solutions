import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1446 {
    static class Road {
        int from;
        int to;
        int cost;

        Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Road[] arr = new Road[n];
        int[] dp = new int[d + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[i] = new Road(from, to, cost);
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= d; i++) {
            for (Road road : arr) {
                if (i == road.to) {
                    dp[i] = Math.min(Math.min(dp[i - 1] + 1, dp[i]), dp[road.from] + road.cost);
                }
            }
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        System.out.println(dp[d]);
    }
}
