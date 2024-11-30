package sleepyhoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11404 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][n+1];

        for(int i=1;i<arr.length;i++){
            for(int j=1;j<arr.length;j++) {
                if(i==j) arr[i][j] = 0;
                else arr[i][j] = 10000000;
            }
        }

        int bus = Integer.parseInt(br.readLine());

        for(int i=0;i<bus;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[from][to] = Math.min(arr[from][to],cost);
        }

        for(int k=1;k<arr.length;k++) {
            for(int i=1;i<arr.length;i++){
                for(int j=1;j<arr.length;j++){
                    arr[i][j] = Math.min(arr[i][j],arr[i][k] + arr[k][j]);
                }
            }
        }

        for(int i=1;i<arr.length;i++) {
            for(int j=1;j<arr.length;j++){
                if(arr[i][j] == 10000000) {
                    sb.append(0).append(" ");
                }
                else
                    sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
