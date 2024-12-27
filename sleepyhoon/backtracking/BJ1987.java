package sleepyhoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1987 {
    static int[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer;
    static int n,m;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for(int i=0;i<n;i++){
            char[] inputs = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                arr[i][j] = inputs[j] - 'A';
            }
        }
        visited = new boolean[26];
        dfs(0,0,1);
        System.out.println(answer);
    }

    private static void dfs(int x ,int y,int dist) {
        visited[arr[x][y]] = true;
        answer = Math.max(answer,dist);

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[arr[nx][ny]]) {
                    dfs(nx, ny, dist + 1);
                    visited[arr[nx][ny]] = false;
                }
            }
        }
    }
}
