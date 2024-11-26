package sleepyhoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865 {

    static class Product {
        int weight;
        int value;

        Product(int w,int v) {
            this.weight= w;
            this.value = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Product[] products = new Product[n+1];
        int[][] dp = new int[k+1][n+1];
        for(int i=1;i<n+1;i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            products[i] = new Product(w,v);
        }

        // 초기화
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                dp[i][j] = 0;
            }
        }

        for(int i=1;i<k+1;i++){
            for(int j=1;j<n+1;j++){
                Product product = products[j];
                if(i < product.weight)
                    dp[i][j]  = dp[i][j-1];
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-product.weight][j-1] + product.value);
            }

        }

        System.out.println(dp[k][n]);

    }
}
