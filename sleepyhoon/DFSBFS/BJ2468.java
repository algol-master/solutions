package sleepyhoon.DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2468 {
    static int n;
    static int[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        int maxHeight = 0;

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(arr[i][j],maxHeight);
            }
        }

        for(int k=0;k<=maxHeight;k++){
            boolean[][] visited = new boolean[n][n];
            int count = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j] && arr[i][j] > k) {
                        dfs(i,j,k,visited);
                        count++;
                    }
                }
            }
            answer = Math.max(count,answer);
        }
        System.out.println(answer);
    }
    private static void dfs(int x,int y,int limit,boolean[][] visited) {
        if(visited[x][y] || arr[x][y] <= limit) return;
        visited[x][y] = true;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0||nx>=n||ny<0||ny>=n) continue;
            dfs(nx,ny,limit,visited);
        }
    }

}
