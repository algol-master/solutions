package sleepyhoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1987 {
    static char[][] arr;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];

        for(int i=0;i<n;i++){
            char[] inputs = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                arr[i][j] = inputs[j];
            }
        }
        dfs(0,0,new boolean[n][m],new HashSet<>(),1);

        System.out.println(answer+1);
    }

    private static void dfs(int x ,int y,boolean[][] visited, Set<Character> set,int dist) {
        if(visited[x][y] || set.contains(arr[x][y])) return;
        visited[x][y] = true;
        set.add(arr[x][y]);
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0||nx>=n||ny<0||ny>=m||set.contains(arr[nx][ny])) continue;

            dfs(nx,ny,visited,set,dist+1);
            answer = Math.max(answer,dist);
            visited[nx][ny] = false;
            set.remove(arr[nx][ny]);
        }
    }
}
